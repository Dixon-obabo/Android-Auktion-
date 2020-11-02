package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import ui.asset;
import ui.myadatpter;

public class MainActivity extends AppCompatActivity {

   private FirebaseAuth mauth;
    FirebaseUser currentuser;
    RecyclerView recyclerView;
    myadatpter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        mauth=FirebaseAuth.getInstance();
        currentuser=mauth.getCurrentUser();
        Login_status();
        getdata();
    }

    public void Login_status(){

        if(currentuser==null){
            Toast.makeText(this, "Please sign In", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(getApplicationContext(),login.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Hello:)", Toast.LENGTH_SHORT).show();
        }
    }



    public void getdata(){
        Query query=FirebaseDatabase.getInstance().getReference("posts");
        FirebaseRecyclerOptions<asset> options= new FirebaseRecyclerOptions.Builder<asset>().setQuery(FirebaseDatabase.getInstance().getReference("posts"),asset.class).build();
        adapter= new myadatpter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void getsome(View view) {
        if (FirebaseDatabase.getInstance().getReference("posts")!=null){
            Toast.makeText(this, "the path isnt empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "it is empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}