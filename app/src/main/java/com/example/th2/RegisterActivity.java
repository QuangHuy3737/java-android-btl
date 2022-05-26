package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Item;
import com.example.th2.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText etTen, etPassword, etDiaChi, etBienSo,etPasswordAgain;
    private Button btRegister,btLogin;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etTen=findViewById(R.id.etTen);
        etPassword=findViewById(R.id.etPassword);
        etPasswordAgain=findViewById(R.id.etPasswordAgain);

        etDiaChi=findViewById(R.id.etDiaChi);
        etBienSo=findViewById(R.id.etBienSo);
        btRegister= findViewById(R.id.btRegister);
        login = findViewById(R.id.btLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etTen.getText().toString();
                String password = etPassword.getText().toString();
                String passwordAgain = etPasswordAgain.getText().toString();

                String dc = etDiaChi.getText().toString();
                String bs = etBienSo.getText().toString();
                if(!name.isEmpty()&&!password.isEmpty() && !passwordAgain.isEmpty()&&!dc.isEmpty()&& !bs.isEmpty()) {
                    if (password.equals(passwordAgain)) {
                        User user = new User(name, password, dc, bs);
                        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                        boolean check = db.checkusername(name);
                        if (check) {
                            Toast.makeText(getApplicationContext(), "Trùng tên", Toast.LENGTH_LONG).show();

                        } else {
                            db.addUser(user);
                            finish();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Dang Ky thanh cong", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Password không trùng", Toast.LENGTH_LONG).show();

                    }
                }
                else{
                        Toast.makeText(getApplicationContext(),"Nhap Day Du thong tin",Toast.LENGTH_LONG).show();
                    }
            }

        });


    }
}