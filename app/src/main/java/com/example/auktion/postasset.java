package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ui.asset;

public class postasset extends AppCompatActivity {

    Uri uri;
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

        String dat=picker.getDayOfMonth()+"/"+String.valueOf(picker.getMonth()+1)+"/"+picker.getYear();
        String key= FirebaseDatabase.getInstance().getReference().push().getKey();
        asset mine= new asset(pstname.getText().toString(),pstprice.getText().toString(),key,dat,pstdesc.getText().toString());
        Toast.makeText(this, mine.getDate(), Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference("posts").child(key).setValue(mine).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(postasset.this, "Asset posted ", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(postasset.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseStorage.getInstance().getReference("cool").child(key).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(postasset.this, "image uploaded ", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(postasset.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }



    public void pickphoto(View view) {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==1 && resultCode==RESULT_OK){
            img.setImageURI(data.getData());
            uri=data.getData();

        }

    }





}