package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;

import ui.asset;
import ui.myadatpter;

public class MainActivity extends AppCompatActivity {

   private FirebaseAuth mauth;
    FirebaseUser currentuser;
    RecyclerView recyclerView;
    myadatpter adapter;
    ImageView hotpic, userdp;
    Button hotbutton, lgout, myacc;
    TextView uname, umail, uphone;
    String username, useremail, userphone;
    ProgressBar pbar;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        hotpic=findViewById(R.id.hotpic);
        hotbutton=findViewById(R.id.hotname);
        pbar=findViewById(R.id.progress);
        dialog= new Dialog(this);
        mauth=FirebaseAuth.getInstance();
        currentuser=mauth.getCurrentUser();
        Login_status();
        getdata();
        gethot();
        getuserdata();
    }

    public void Login_status(){

        if(currentuser==null){
            Toast.makeText(this, "Please sign In", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(getApplicationContext(),login.class);
            startActivity(intent);
        }else {

        }
    }



    public void getdata(){

        FirebaseRecyclerOptions<asset> options= new FirebaseRecyclerOptions.Builder<asset>().setQuery(FirebaseDatabase.getInstance().getReference("posts"),asset.class).build();
        adapter= new myadatpter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        pbar.setVisibility(View.GONE);
    }



    public  void gethot(){
        FirebaseDatabase.getInstance().getReference("hot").addChildEventListener(hotchild);
    }

    ChildEventListener hotchild=new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            String m=snapshot.getValue().toString();
            String []data=m.split(",");

            FirebaseStorage.getInstance().getReference("cool").child(snapshot.getKey()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    Glide.with(getApplicationContext()).load(uri).into(hotpic).getView().setScaleType(ImageView.ScaleType.CENTER_CROP);

                }
            });

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


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

    public void opendialog(View view) {


        dialog.setContentView(R.layout.userdialog);
        lgout=dialog.findViewById(R.id.logout);
        userdp=dialog.findViewById(R.id.userdp);
        umail=dialog.findViewById(R.id.uemail);
        uname=dialog.findViewById(R.id.uname);
        uphone=dialog.findViewById(R.id.uphone);
        uphone.setText(userphone);
        uname.setText(username);
        umail.setText(useremail);

        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signOut();
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });


        dialog.show();


    }

    public  void getuserdata(){
        FirebaseDatabase.getInstance().getReference("biders/"+currentuser.getPhoneNumber()).addChildEventListener(getuser);
    }

    ChildEventListener getuser= new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            String []data=snapshot.getValue().toString().split(",");
            userphone=data[0].replace("{phone=","");
            username=data[1].replace("name=","");
            useremail=data[2].replace("email=","");

            hotbutton.setText(username);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    public void logout(View view) {
        mauth.signOut();
    }

    public void openbidasset(View view) {
        Intent intent= new Intent(getApplicationContext(),postasset.class);
        startActivity(intent);
    }
}