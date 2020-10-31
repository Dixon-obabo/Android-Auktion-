package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mauth;
    FirebaseDatabase database;
    DatabaseReference dbref;
    FirebaseUser currentuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    currentuser=mauth.getCurrentUser();
    dbref=database.getReference("posts");


    }

    public void Login_status(){

        Intent intent= new Intent(getApplicationContext(),login.class);
        startActivity(intent);
    }
}