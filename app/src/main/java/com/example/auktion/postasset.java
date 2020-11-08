package com.example.auktion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class postasset extends AppCompatActivity {

    DatePicker picker;
    ImageButton img;
    EditText pstname,pstprice,pstdesc;
    int PICK_PHOTO=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postasset);
        picker=findViewById(R.id.datepicker);
        pstname=findViewById(R.id.postname);
        pstprice=findViewById(R.id.postprice);
        pstdesc=findViewById(R.id.postdesc);
        img=findViewById(R.id.postimage);
    }

    public void trial(View view) {
        Toast.makeText(this, String.valueOf(picker.getDayOfMonth()), Toast.LENGTH_SHORT).show();
    }

    public void postasset(View view) {
        String dat=String.valueOf(picker.getDayOfMonth())+"/"+String.valueOf(picker.getMonth()+1)+"/"+String.valueOf(picker.getYear());
        //Toast.makeText(this, String.valueOf(picker.getMonth()+1), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, dat, Toast.LENGTH_SHORT).show();

    }

    public void pickphoto(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_PHOTO && resultCode==RESULT_OK){
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                img.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }



}