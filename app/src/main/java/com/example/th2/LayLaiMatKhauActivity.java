package com.example.th2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.User;

public class LayLaiMatKhauActivity extends AppCompatActivity {
    private EditText etTen, etPassword, etPasswordAgain;
    private Button btSave;
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_lai_mat_khau);
        etTen=findViewById(R.id.etTen);
        etPassword=findViewById(R.id.etPassword);
        etPasswordAgain=findViewById(R.id.etConfirmPassword);

        btSave=findViewById(R.id.btSave);
        back = findViewById(R.id.tvBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LayLaiMatKhauActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etTen.getText().toString();
                String password = etPassword.getText().toString();
                String passwordAgain = etPasswordAgain.getText().toString();
                if(!name.isEmpty()&&!password.isEmpty() && !passwordAgain.isEmpty()) {
                    if (password.equals(passwordAgain)) {
                        User user = new User(name, password);
                        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                        boolean check = db.checkusername(name);
                        if (!check) {
                            Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_LONG).show();
                        } else {
                            db.updateNewPassword(user);
                            Intent intent = new Intent(LayLaiMatKhauActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành cong", Toast.LENGTH_LONG).show();
                            finish();

                        }
                    }
                    else {
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