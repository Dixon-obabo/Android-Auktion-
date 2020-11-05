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

public class login extends AppCompatActivity {

    EditText name;
    EditText phone;
    EditText email;
    EditText password;
    TextView sigin;
    FirebaseAuth auth;
    Button logn;
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
        logn.setText("LOGIN");
    }

    public void show(View view) {
        if(sigin.getText().toString()=="signUp"){

            phone.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            logn.setText("SignUp");
            sigin.setText("logIn");

        }
        if(sigin.getText().toString()=="logIn"){
            phone.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            logn.setText("LOGIN");
            sigin.setText("signUp");
        }
    }

    public void loginuser(View view) {
        if(logn.getText().toString()=="LOGIN"){
            auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(login.this, "hello", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(e -> Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        }
        if(logn.getText().toString()=="SignUp"){
            auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    currentuser= auth.getCurrentUser();
                    String key= currentuser.getUid();
                    Toast.makeText(login.this, "key", Toast.LENGTH_SHORT).show();

                    FirebaseDatabase.getInstance().getReference("userdata").setValue(key);

                }
            }).addOnFailureListener(e -> Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        }


    }
}