package com.example.th2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.dal.SQLiteHelper;
import com.example.th2.fragment.FragmentInfor;
import com.example.th2.model.Item;
import com.example.th2.model.User;

public class UpdateNopTien extends AppCompatActivity implements View.OnClickListener {
    private Button btUpdate,btBack,btRemove;
    private Item item;
    private TextView tvName,tvBienSo,tvPhat;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private EditText etPhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nop_tien);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        Intent intent=getIntent();
        item=(Item) intent.getSerializableExtra("item");
        db=new SQLiteHelper(this);
        getData();

    }

    private void initView() {
        tvName = findViewById(R.id.tvName);
        tvBienSo = findViewById(R.id.tvBienSo);
        tvPhat = findViewById(R.id.tvPhat);
        etPhat = findViewById(R.id.etPhat);
        btUpdate=findViewById(R.id.btUpdate);
        btBack=findViewById(R.id.btBack);
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db=new SQLiteHelper(this);

        if(view==btBack){
            finish();
        }
        if(view==btUpdate){
            int id = item.getId();
            String name = item.getSach();
            String bienSo = item.getTomtat();
            String gia = etPhat.getText().toString();

            String phat = item.getTacgia();
            String viTri = item.getFavourite();
            String tenLoi = item.getFavourite();
            if(!gia.isEmpty()){
                if(gia.equals(phat)){
                    tenLoi="Đã đóng phạt";
                    gia= "0 VND";
                    Item i=new Item(id,name,bienSo,gia,tenLoi,viTri);
                    db=new SQLiteHelper(this);
                    db.update(i);
                    finish();
                    Toast.makeText(getApplicationContext(),"Đóng tiền phạt thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdateNopTien.this, MainActivity.class);
                    intent.putExtra("name",name);

                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Hãy đóng đủ số tiền phạt",Toast.LENGTH_LONG).show();
                }
            }

            else{
                Toast.makeText(getApplicationContext(),"Nhap so tien bi phat",Toast.LENGTH_LONG).show();

            }




        }
    }
    public  void getData (){
        tvName.setText(item.getSach());
        tvBienSo.setText(item.getTomtat());
        tvPhat.setText(item.getTacgia());
    }
}