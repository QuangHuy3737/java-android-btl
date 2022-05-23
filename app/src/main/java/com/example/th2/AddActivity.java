package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener  {
    public Spinner spTacgia,spNxb,spFav;
    private EditText eSach ,eTomtat;
    private Button btAdd,btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btAdd.setOnClickListener(this);

    }

    private void initView() {
        eTomtat=findViewById(R.id.tvTomtat);
        eSach=findViewById(R.id.tvSach);


        spTacgia=findViewById(R.id.spTacGia);
        spNxb=findViewById(R.id.spNxb);
        spFav=findViewById(R.id.spFavourite);
        btAdd=findViewById(R.id.btAdd);
        btCancel=findViewById(R.id.btCancel);
        spTacgia.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia) ));
        spNxb.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.nxb) ));
        spFav.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.favorite) ));
    }

    @Override
    public void onClick(View view) {

        if(view==btCancel){
            finish();
        }
        if(view==btAdd){
            String sach=eSach.getText().toString();
            String tomtat=eTomtat.getText().toString();
            String tacgia=spTacgia.getSelectedItem().toString();
            String nxb=spNxb.getSelectedItem().toString();
            String favourite=spFav.getSelectedItem().toString();
            if(!sach.isEmpty()&&!tomtat.isEmpty()){
                Item i=new Item(sach,tomtat,tacgia,nxb,favourite);
                SQLiteHelper db=new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }

    }
}