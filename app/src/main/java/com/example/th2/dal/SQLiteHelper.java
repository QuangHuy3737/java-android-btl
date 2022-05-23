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
    private static final String DATABASE_NAME="Sach.db";
    private static int DATABASE_VERSION=5;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "sach TEXT,tomtat TEXT,tacgia TEXT,nxb TEXT,favourite TEXT)";
        String sql1="CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,password TEXT,diaChi TEXT,bienSo TEXT)";
        db.execSQL(sql);
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Item> getAll(){
        List<Item> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order ="tomtat DESC";
        Cursor rs=st.query("items",null,null,
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
        values.put("sach",i.getSach());
        values.put("tomtat",i.getTomtat());
        values.put("tacgia",i.getTacgia());
        values.put("nxb",i.getNxb());
        values.put("favourite",i.getFavourite());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.insert("items",null,values);

    }
    //add user
    public long addUser(User i){
        ContentValues values=new ContentValues();
        values.put("name",i.getName());
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
            Cursor rs=st.query("items",null,whereClause,whereArgs,null,
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
    //login
    public boolean login(User i){
        ContentValues values=new ContentValues();
        List<User> list=new ArrayList<>();
        String name = i.getName().toString();
        String password = i.getPassword().toString();
        String whereClause="sach like?";
        String[] whereArgs={"%"+name+"%"+password};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,
                null,null);
        if(rs!=null && rs.moveToNext()){
            return true;
        }
        else{
            return true;
        }
    }

    //update
    public int update(Item i){
        ContentValues values=new ContentValues();
        values.put("sach",i.getSach());
        values.put("tomtat",i.getTomtat());
        values.put("tacgia",i.getTacgia());
        values.put("nxb",i.getNxb());
        values.put("favourite",i.getFavourite());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="id=?";
        String[] whereArgs={Integer.toString(i.getId())};

        return sqLiteDatabase.update("items",values,whereClause,whereArgs);

    }
    //delete
    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.delete("items",whereClause,whereArgs);

    }
    //lay item theo title
    public List<Item> searchBySach(String key){
        List<Item> list=new ArrayList<>();
        String whereClause="sach like?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,
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
        String whereClause="tacgia like?";
        String[] whereArgs={tacgia};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,
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
        String whereClause="nxb like?";
        String[] whereArgs={nxb};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,
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
