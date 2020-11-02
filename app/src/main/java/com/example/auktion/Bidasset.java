package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Bidasset extends AppCompatActivity {

    String asset_name;
    String asset_price;
    String asset_key;
    String asset_desc;
    String date;
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
    }
}