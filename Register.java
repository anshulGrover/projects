package com.example.anshulgrover.dabbawala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText ename ,eusername,epassword,ePaddress,eDaddress,ephone,eDtime;
    String name,email,password,mobile,pickup_add,drop_add,drop_time;
    Button Register;
    TextView loginActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ename=(EditText)findViewById(R.id.etName);
        eusername=(EditText)findViewById(R.id.etrUsername);
        epassword=(EditText)findViewById(R.id.etrPassWord);
        ePaddress=(EditText)findViewById(R.id.etPAddress);
        eDaddress=(EditText)findViewById(R.id.etDAddress);
        ephone=(EditText)findViewById(R.id.etPhone);
        eDtime=(EditText)findViewById(R.id.DTime);
        Register=(Button)findViewById(R.id.bRegister);
        loginActivity=(TextView)findViewById(R.id.tvLogin);
        loginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilogin=new Intent();
                ilogin.setClass(com.example.anshulgrover.dabbawala.Register.this,MainActivity.class);
                startActivity(ilogin);
            }
        });


    }
    public void uRegister(View view) {
        name = ename.getText().toString();
        email = eusername.getText().toString();
        password = epassword.getText().toString();
        mobile = ephone.getText().toString();
        pickup_add = ePaddress.getText().toString();
        drop_add = eDaddress.getText().toString();
        drop_time = eDtime.getText().toString();
        if(name.isEmpty()||email.isEmpty()||password.isEmpty()||mobile.isEmpty()||pickup_add.isEmpty()||drop_add.isEmpty()||drop_time.isEmpty()){
            Toast.makeText(this, "Please fill all the fields..", Toast.LENGTH_SHORT).show();
        }
        else {
            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask(Register.this);
            backgroundTask.execute(method, name, email, password, mobile, pickup_add, drop_add, drop_time);

        }
    }

    }

