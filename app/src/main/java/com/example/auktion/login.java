package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {

    EditText name;
    EditText phone;
    EditText email;
    EditText password;
    TextView sigin;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        sigin=findViewById(R.id.signin);
    }

    public void show(View view) {

        phone.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);

    }

    public void login(View view) {


    }
}