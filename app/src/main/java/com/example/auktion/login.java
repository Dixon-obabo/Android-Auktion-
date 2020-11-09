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
            //logn.setText("SignUp");
            //sigin.setText("logIn");
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