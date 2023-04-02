package com.example.logincomsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logincomsqlite.appdatabase.DbHelper;

public class LoginActivity extends AppCompatActivity {
    EditText userName, password;
    Button btnLogin;
    DbHelper dbHelper;
    boolean testaUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.user_nameLogin);
        password = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btn_sign_upLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Por Favor Preencha todos os Campos", Toast.LENGTH_SHORT).show();
                }else{
                    boolean testaUsuario = dbHelper.checkUserPassword(user, pass);

                    if(testaUsuario){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login ou Senha Invalidos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}