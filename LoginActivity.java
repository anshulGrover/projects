package com.example.anshulgrover.dabbawala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText username,lpassword;
    Button Login;
    TextView registerActivity;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.etrUsername);
        lpassword = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.bLogin);
        registerActivity = (TextView) findViewById(R.id.tvRegister);


        registerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iregister = new Intent();
                iregister.setClass(LoginActivity.this, Register.class);
                startActivity(iregister);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = username.getText().toString();
                password = lpassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "please enter username or password both", Toast.LENGTH_SHORT).show();
                } else {

                    String method = "login";
                    BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
                    backgroundTask.execute(method, email, password);
                }

            }
        });

    }
}
