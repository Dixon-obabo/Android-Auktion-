package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ui.bid;

public class Bidasset extends AppCompatActivity {

    String asset_name;
    String asset_price;
    String asset_key;
    String asset_desc;
    String date;
    String mail;
    TextView name,price,desc,email,det;
    EditText nbid;
    ImageView asset_image;
   private  FirebaseStorage storage;
   StorageReference store;
   FirebaseDatabase database;
   DatabaseReference dbref;
   FirebaseAuth auth;
   FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidasset);
        Intent intent=getIntent();
        auth=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        asset_image=findViewById(R.id.asset_pic);
        asset_name=intent.getStringExtra("asset_name");
        asset_price=intent.getStringExtra("asset_price");
        asset_key=intent.getStringExtra("key");
        asset_desc=intent.getStringExtra("asset_desc");
        date=intent.getStringExtra("date");
        mail=intent.getStringExtra("email");
        name=findViewById(R.id.name);
        price=findViewById(R.id.oldbid);
        desc=findViewById(R.id.description);
        email=findViewById(R.id.email);
        det=findViewById(R.id.date);
        nbid=findViewById(R.id.newbid);
        currentuser=auth.getCurrentUser();

        putdata();
        getnewbid();
    }

    public void postbid(View view) {

        if(nbid.getText()==null){
            Toast.makeText(this, "Please place a bid", Toast.LENGTH_SHORT).show();
        }else {
            String nbb=price.getText().toString().replace("Ksh","").trim();
            String mbb=nbid.getText().toString();
            int m=Integer.parseInt(mbb);
            int n=Integer.parseInt(nbb);

            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {

                bid newbid=new bid(String.valueOf(currentuser.getEmail()),m+n,asset_name,s);

                FirebaseDatabase.getInstance().getReference("bids").child(asset_key).child(FirebaseDatabase.getInstance().getReference().push().getKey()).setValue(newbid).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Bidasset.this, "Bid posted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Bidasset.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        }


    }

    public  void putdata(){

        desc.setText(asset_desc);
        det.setText("Ends on:"+date);
        email.setText(mail);
        name.setText(asset_name);
        price.setText(asset_price);
        FirebaseStorage.getInstance().getReference("cool").child(asset_key).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(asset_image).getView().setScaleType(ImageView.ScaleType.CENTER_CROP);

            }
        });
    }


    public void getnewbid(){
        FirebaseDatabase.getInstance().getReference("bids").child(asset_key).addChildEventListener(newbid);
    }

    ChildEventListener newbid=new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            String []data=snapshot.getValue().toString().split(",");

            price.setText(data[2].replace("bid=","")+" Ksh");
            String []name=data[3].replace("email=","").replace("}","").split("@");
            email.setText(name[0]);
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

}