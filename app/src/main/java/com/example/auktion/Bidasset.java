package com.example.auktion;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        asset_desc=intent.getStringExtra("asset_description");
        date=intent.getStringExtra("date");
        mail=intent.getStringExtra("email");
        name=findViewById(R.id.name);
        price=findViewById(R.id.oldbid);
        desc=findViewById(R.id.description);
        email=findViewById(R.id.email);
        det=findViewById(R.id.date);
        nbid=findViewById(R.id.newbid);
        Toast.makeText(this, asset_desc+asset_name, Toast.LENGTH_SHORT).show();
        putdata();

    }

    public void postbid(View view) {
        //currentuser=auth.getCurrentUser();
        String nbb=price.getText().toString();
        int n=Integer.parseInt(nbb);
        Toast.makeText(this, String.valueOf(n), Toast.LENGTH_SHORT).show();
//        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                //Toast.makeText(Bidasset.this, s, Toast.LENGTH_SHORT).show();
//               // Toast.makeText(Bidasset.this, n, Toast.LENGTH_SHORT).show();
//                //bid newbid=new bid(String.valueOf(currentuser.getEmail()),String.valueOf(Integer.parseInt(price.getText().toString().trim()+Integer.parseInt(nbid.getText().toString().trim()))),asset_name,s);
//                //Toast.makeText(Bidasset.this, String.valueOf(newbid), Toast.LENGTH_SHORT).show();
//               // Toast.makeText(Bidasset.this, Integer.parseInt(price.getText().toString().trim())+Integer.parseInt(nbid.getText().toString().trim()), Toast.LENGTH_LONG).show();
//
//            }
//        });

        //FirebaseDatabase.getInstance().getReference("bids").child(asset_key).child(FirebaseDatabase.getInstance().getReference().push().getKey()).setValue("yyooh");
        //Toast.makeText(this, Integer.parseInt(nbid.getText().toString()) + Integer.parseInt(price.getText().toString()), Toast.LENGTH_SHORT).show();

    }

    public  void putdata(){

        desc.setText(asset_desc);
        det.setText(date);
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


}