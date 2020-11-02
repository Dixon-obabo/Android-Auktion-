package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bidasset extends AppCompatActivity {

    String asset_name;
    String asset_price;
    String asset_key;
    String asset_desc;
    String date;
    String mail;
    TextView name,price,desc,email,det;
    EditText nbid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidasset);
        Intent intent=getIntent();
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
    }

    public void postbid(View view) {

    }
}