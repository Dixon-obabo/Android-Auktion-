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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

        Toast.makeText(this, nbid.getText().toString()+price.getText().toString(), Toast.LENGTH_SHORT).show();

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
                Glide.with(getApplicationContext()).load(uri).into(asset_image);
            }
        });

    }
}