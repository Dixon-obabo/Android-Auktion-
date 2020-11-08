package com.example.auktion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class postasset extends AppCompatActivity {

    DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postasset);
        picker=findViewById(R.id.datepicker);
    }

    public void trial(View view) {
        Toast.makeText(this, String.valueOf(picker.getDayOfMonth()), Toast.LENGTH_SHORT).show();
    }

    public void postasset(View view) {



    }
}