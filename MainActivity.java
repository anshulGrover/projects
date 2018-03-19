package com.example.anshulgrover.dabbawala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

TextView tv;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView3);


        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            email=extra.getString("e");
            password=extra.getString("p");
        }
        tv.setText("email--->"+email+"\npassword--->"+password);

    }
}
