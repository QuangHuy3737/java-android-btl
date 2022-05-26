package com.example.th2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2.model.Item;
import com.example.th2.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="giaoThong.db";
    private static int DATABASE_VERSION=5;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE data(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,bienSo TEXT,gia TEXT,viTri TEXT,tenLoi TEXT)";
        String sql1="CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,email TEXT, password TEXT,diaChi TEXT,bienSo TEXT)";
        db.execSQL(sql);
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        onCreate((sqLiteDatabase));

    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Item> getAll(){
        List<Item> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order ="name DESC";
        Cursor rs=st.query("data",null,null,
                null,null,null,order);
        while((rs!=null) && (rs.moveToNext())){
            int id=rs.getInt(0);
            String sach=rs.getString(1);
            String t=rs.getString(2);
            String tacgia=rs.getString(3);
            String nxb=rs.getString(4);
            String favourite=rs.getString(5);
            list.add(new Item(id,sach,t,tacgia,nxb,favourite));
        }


        return list;

    }
    //add
    public long addItem(Item i){
        ContentValues values=new ContentValues();
        values.put("name",i.getSach());
        values.put("bienSo",i.getTomtat());
        values.put("gia",i.getTacgia());
        values.put("viTri",i.getNxb());
        values.put("tenLoi",i.getFavourite());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.insert("data",null,values);

    }
    //add user
    public long addUser(User i){
        ContentValues values=new ContentValues();
        values.put("name",i.getName());
        values.put("email","huyquangtran37@gmail.com");

        values.put("password",i.getPassword());
        values.put("diaChi",i.getDiaChi());
        values.put("bienSo",i.getBienSo());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.insert("users",null,values);

    }
    //get user
    public List<Item>  getUser(String name){
            List<Item> list=new ArrayList<>();
            String whereClause="name like?";
            String[] whereArgs={"%"+name+"%"};
            SQLiteDatabase st=getReadableDatabase();
            Cursor rs=st.query("data",null,whereClause,whereArgs,null,
                    null,null);

            while(rs!=null && rs.moveToNext()){
                int id=rs.getInt(0);
                String sach=rs.getString(1);
                String tomtat=rs.getString(2);
                String tacgia=rs.getString(3);
                String nxb=rs.getString(4);
                String favourite=rs.getString(5);
                list.add(new Item(id,sach,tomtat,tacgia,nxb,favourite));
            }
            return list;
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    //Lấy một SP biết ID
    public User getUserByName(String name) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  name, bienSo, diaChi from users where name = ?",
                new String[]{name + ""});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String ten = cursor.getString(0);
            String bienSo = cursor.getString(1);
            String dc = cursor.getString(2);

            User user = new User(ten, bienSo,dc);
            cursor.close();
            return user;
        }
        else{
            return new User("none","none","none");
        }

    }

    //login
    public boolean login(User i){
        ContentValues values=new ContentValues();
        List<User> list=new ArrayList<>();
        String name = i.getName().toString();
        String password = i.getPassword().toString();
        String whereClause="name = ? and password = ?";
        String[] whereArgs={name,password};
        SQLiteDatabase st=getReadableDatabase();
//
        Cursor rs=st.query("users",null,whereClause,whereArgs,null,
                null,null);
//        Cursor cursor=st.rawQuery("Select * from users where name = ? and password = ?",new String[]{name,password});
        if(rs.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    //update password
    public int updateNewPassword(User i){
        ContentValues values=new ContentValues();
        values.put("password",i.getPassword());

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="name=?";
        String[] whereArgs={i.getName()};

        return sqLiteDatabase.update("users",values,whereClause,whereArgs);

    }
    //update
    public int update(Item i){
        ContentValues values=new ContentValues();
        values.put("name",i.getSach());
        values.put("bienSo",i.getTomtat());
        values.put("gia",i.getTacgia());
        values.put("viTri",i.getNxb());
        values.put("tenLoi",i.getFavourite());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="id=?";
        String[] whereArgs={Integer.toString(i.getId())};

        return sqLiteDatabase.update("data",values,whereClause,whereArgs);

    }
    //delete
    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.delete("data",whereClause,whereArgs);

    }
    //lay item theo title
    public List<Item> searchBySach(String key){
        List<Item> list=new ArrayList<>();
        String whereClause="name like?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("data",null,whereClause,whereArgs,null,
                null,null);
        while(rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String sach=rs.getString(1);
            String tomtat=rs.getString(2);
            String tacgia=rs.getString(3);
            String nxb=rs.getString(4);
            String favourite=rs.getString(5);
            list.add(new Item(id,sach,tomtat,tacgia,nxb,favourite));

        }
        return list;
    }
    public List<Item> SearchByTacGia(String tacgia){
        List<Item> list=new ArrayList<>();
        String whereClause="gia like?";
        String[] whereArgs={tacgia};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("data",null,whereClause,whereArgs,null,
                null,null);
        while(rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String sach=rs.getString(1);
            String tomtat=rs.getString(2);
            String tgia=rs.getString(3);
            String nxb=rs.getString(4);
            String favourite=rs.getString(5);
            list.add(new Item(id,sach,tomtat,tgia,nxb,favourite));
        }
        return list;
    }
    public List<Item> SearchByNXB(String nxb){
        List<Item> list=new ArrayList<>();
        String whereClause="tenLoi like?";
        String[] whereArgs={nxb};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("data",null,whereClause,whereArgs,null,
                null,null);
        while(rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String sach=rs.getString(1);
            String tomtat=rs.getString(2);
            String tacgia=rs.getString(3);
            String n=rs.getString(4);
            String favourite=rs.getString(5);
            list.add(new Item(id,sach,tomtat,tacgia,n,favourite));
        }
        return list;
    }
}
