package com.example.logincomsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logincomsqlite.appdatabase.DbHelper;

public class MainActivity extends AppCompatActivity {
    EditText userName, password, rePassword;
    Button signUp, signIn;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName   = findViewById(R.id.user_name);
        password   = findViewById(R.id.password);
        rePassword = findViewById(R.id.re_password);
        signIn     = findViewById(R.id.btn_sign_in);
        signUp     = findViewById(R.id.btn_sign_up);

        helper = new DbHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String repass = rePassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(MainActivity.this, "Por favor Preencha todos os Campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isCheckUser = helper.checkUserName(user);
                    if (pass.equals(repass)) {
                        if (isCheckUser == false) {
                            boolean isInsert = helper.inserirUsuario(user, pass);
                            if (isInsert) {
                                Toast.makeText(MainActivity.this, "Usuario Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            } else {
                                Toast.makeText(MainActivity.this, "Falha ao Cadastrar Usuario", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario Já Existe. Vá para o Login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Senhas Nao Conferem", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}