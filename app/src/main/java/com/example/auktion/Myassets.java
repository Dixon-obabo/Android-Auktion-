package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import ui.asset;
import ui.myadatpter;

public class Myassets extends AppCompatActivity {
    String userid;
    FirebaseRecyclerOptions<asset> opt;
    myadatpter firstadap;
    RecyclerView myrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myassets);
        myrecycler=findViewById(R.id.marecycler);
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
        get_data();
    }

    public  void get_data(){
        opt=new FirebaseRecyclerOptions.Builder<asset>().setQuery(FirebaseDatabase.getInstance().getReference("OldPosts/"+userid),asset.class).build();
        firstadap=new myadatpter(opt);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));
        myrecycler.setAdapter(firstadap);

    }

    @Override
    protected void onStart() {
        super.onStart();
        firstadap.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firstadap.stopListening();
    }
}