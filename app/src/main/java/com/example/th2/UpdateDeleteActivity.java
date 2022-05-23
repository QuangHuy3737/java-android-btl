package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.th2.adapter.ViewPagerAdapter;
import com.example.th2.dal.SQLiteHelper;

import com.example.th2.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner spTacGia,spNxb,spFav;
    private EditText eSach,eTomtat;
    private Button btUpdate,btBack,btRemove;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        eTomtat.setOnClickListener(this);

        Intent intent=getIntent();
        item=(Item) intent.getSerializableExtra("item");
        eSach.setText(item.getSach());
        eTomtat.setText(item.getTomtat());

        int p=0;
        for(int i=0;i<spTacGia.getCount();i++){
            if(spTacGia.getItemAtPosition(i).toString().equalsIgnoreCase(item.getTacgia())){
                p=i;
                break;


            }
        }
        spTacGia.setSelection(p);
        int q=0;
        for(int i=0;i<spNxb.getCount();i++){
            if(spNxb.getItemAtPosition(i).toString().equalsIgnoreCase(item.getNxb())){
                q=i;
                break;


            }
        }
        spNxb.setSelection(q);
        int t=0;
        for(int i=0;i<spFav.getCount();i++){
            if(spFav.getItemAtPosition(i).toString().equalsIgnoreCase(item.getFavourite())){
                t=i;
                break;


            }
        }
        spFav.setSelection(t);
    }

    private void initView() {
        eTomtat=findViewById(R.id.tvTomtat);
        eSach=findViewById(R.id.tvSach);



        spTacGia=findViewById(R.id.spTacGia);
        spNxb=findViewById(R.id.spNxb);
        spFav=findViewById(R.id.spFavourite);
        btUpdate=findViewById(R.id.btUpdate);
        btRemove=findViewById(R.id.btRemove);
        btBack=findViewById(R.id.btBack);
        spTacGia.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia) ));
        spNxb.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.nxb) ));
        spFav.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.favorite) ));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db=new SQLiteHelper(this);

        if(view==btBack){
            finish();
        }
        if(view==btUpdate){
            String sach=eSach.getText().toString();
            String tomtat=eTomtat.getText().toString();
            String tacgia=spTacGia.getSelectedItem().toString();
            String nxb=spNxb.getSelectedItem().toString();
            String favourite=spFav.getSelectedItem().toString();
            if(!sach.isEmpty()&&!tomtat.isEmpty()){
                int id=item.getId();
                Item i=new Item(id,sach,tomtat,tacgia,nxb,favourite);
                db=new SQLiteHelper(this);
                db.update(i);
                finish();
            }
        }
        if(view==btRemove){
            int id=item.getId();
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa"+item.getSach()+"khong?");
            builder.setIcon(R.drawable.download);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper bb=new SQLiteHelper(getApplicationContext());
                    bb.delete(id);
                    finish();

                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }


    }
}