package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import ui.user;

public class login extends AppCompatActivity {

    EditText name;
    EditText phone;
    EditText email;
    EditText password;
    TextView sigin,sup;
    FirebaseAuth auth;
    Button logn,signin;
    FirebaseUser currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.name);
        auth=FirebaseAuth.getInstance();
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        logn=findViewById(R.id.login);
        sigin=findViewById(R.id.signin);
        signin=findViewById(R.id.btnsign);
        sup=findViewById(R.id.signup);
    }

    public void show(View view) {
            phone.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            logn.setVisibility(View.GONE);
            signin.setVisibility(View.VISIBLE);
            sigin.setVisibility(View.VISIBLE);
            sup.setVisibility(View.GONE);

    }

    public void loginuser(View view) {

            auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(login.this, "hello", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(e -> Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show());

    }

    public void signup(View view) {

        if(phone.getText().toString()==null){
            Toast.makeText(this, "Please provide a phone number", Toast.LENGTH_SHORT).show();
        }else if(name.getText().toString()==null){
            Toast.makeText(this, "Please provide a name", Toast.LENGTH_SHORT).show();
        }else  if(password.getText().toString()==null){
            Toast.makeText(this, "Please provide a password", Toast.LENGTH_SHORT).show();
        }else if(email.getText().toString()==null){
            Toast.makeText(this, "Please provide an email address", Toast.LENGTH_SHORT).show();
        }else {

            auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    String uid=authResult.getUser().getUid();

                    user me= new user(name.getText().toString(),uid,phone.getText().toString(),email.getText().toString());

                    FirebaseDatabase.getInstance().getReference("biders").child(uid).setValue(me).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(login.this, "new user created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }


    public void hide(View view) {
        phone.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        logn.setVisibility(View.GONE);
        signin.setVisibility(View.GONE);
        sigin.setVisibility(View.GONE);
        logn.setVisibility(View.VISIBLE);
        sup.setVisibility(View.VISIBLE);
    }
}