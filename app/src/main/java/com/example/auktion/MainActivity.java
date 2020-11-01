package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {

//    FirebaseAuth mauth;
//    FirebaseDatabase database;
//    DatabaseReference dbref;
//    FirebaseUser currentuser;
//    private FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//    currentuser=mauth.getCurrentUser();
//    dbref=database.getReference("posts");
//    Login_status();
//
    }

    public void Login_status(){
//        if(currentuser==null){
//            Intent intent= new Intent(getApplicationContext(),login.class);
//            startActivity(intent);
//        }else {
//            Toast.makeText(this, "Hello:)", Toast.LENGTH_SHORT).show();
//        }
    }



//    ChildEventListener childEventListener= new ChildEventListener() {
//    @Override
//    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//    }
//
//    @Override
//    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//    }
//
//    @Override
//    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//    }
//
//    @Override
//    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError error) {
//
//    }
//};


    public void getdate(){
//        dbref.addChildEventListener(childEventListener);

    }



}