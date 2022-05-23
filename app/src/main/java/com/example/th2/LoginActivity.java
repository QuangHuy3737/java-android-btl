package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText etTen, etPassword, etDiaChi, etBienSo;
    private Button btRegister,btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etTen=findViewById(R.id.etTen);
        etPassword=findViewById(R.id.etPassword);
        btRegister= findViewById(R.id.btRegister);
        btLogin = findViewById(R.id.btLogin);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etTen.getText().toString();
                String password = etPassword.getText().toString();
                if(!name.isEmpty()&&!password.isEmpty()){
                    User user = new User(name,password);
                    SQLiteHelper db=new SQLiteHelper(getApplicationContext());
                    boolean res= db.login(user);
                    if(res){
                        finish();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Dang nhap thanh cong",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Sai ten hoac password",Toast.LENGTH_LONG).show();

                    }


                }
                else{
                    Toast.makeText(getApplicationContext(),"Nhap Day Du thong tin",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}