package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ui.asset;
import ui.myadatpter;
import ui.storeadapter;

public class MainActivity extends AppCompatActivity {

   private FirebaseAuth mauth;
    FirebaseUser currentuser;
    RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    myadatpter firstadap;
    storeadapter secondadap;
    ImageView hotpic, userdp;
    Button hotbutton, lgout, myacc;
    TextView uname, umail, uphone;
    String username, useremail, userphone;
    ProgressBar pbar;
    Dialog dialog;
    FirebaseRecyclerOptions<asset> opt;
    FirestoreRecyclerOptions<asset> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        hotpic=findViewById(R.id.hotpic);
        hotbutton=findViewById(R.id.hotname);
        pbar=findViewById(R.id.progress);
        dialog= new Dialog(this);
        mauth=FirebaseAuth.getInstance();
        currentuser=mauth.getCurrentUser();
        firestore=FirebaseFirestore.getInstance();
        Login_status();
        getdata();
        gethot();
        getuserdata();



    }

    public void Login_status(){

        if(currentuser==null){
            Toast.makeText(this, "Please sign In", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(getApplicationContext(),login.class);
            startActivity(intent);
        }else {

        }
    }



    public void getdata(){
        opt=new FirebaseRecyclerOptions.Builder<asset>().setQuery(FirebaseDatabase.getInstance().getReference("posts"),asset.class).build();

        firstadap= new myadatpter(opt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(firstadap);
        pbar.setVisibility(View.GONE);


//        CollectionReference reference=firestore.collection("OldPosts");
//
//        Query query1= reference.whereEqualTo("owner",currentuser.getUid());
//        options=new FirestoreRecyclerOptions.Builder<asset>().setQuery(query1,asset.class).build();
//        secondadap= new storeadapter(options);


    }



    public  void gethot(){
        FirebaseDatabase.getInstance().getReference("hot").addChildEventListener(hotchild);
    }

    ChildEventListener hotchild=new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            String m=snapshot.getValue().toString();
            String []data=m.split(",");

            FirebaseStorage.getInstance().getReference("cool").child(snapshot.getKey()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    Glide.with(getApplicationContext()).load(uri).into(hotpic).getView().setScaleType(ImageView.ScaleType.CENTER_CROP);

                }
            });

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        firstadap.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        firstadap.stopListening();

    }

    public void opendialog(View view) {


        dialog.setContentView(R.layout.userdialog);
        lgout=dialog.findViewById(R.id.logout);
        myacc=dialog.findViewById(R.id.userassets);
        userdp=dialog.findViewById(R.id.userdp);
        umail=dialog.findViewById(R.id.uemail);
        uname=dialog.findViewById(R.id.uname);
        uphone=dialog.findViewById(R.id.uphone);
        uphone.setText(userphone);
        uname.setText(username);
        umail.setText(useremail);

        myacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Myassets.class);
                intent.putExtra("userid",currentuser.getUid());
                startActivity(intent);
            }
        });


        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signOut();
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });


        dialog.show();


    }

    public  void getuserdata(){

        FirebaseDatabase.getInstance().getReference("biders/"+currentuser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String []nm=snapshot.getValue().toString().split(",");
                username=nm[1].replace("name=","");
                userphone=nm[0].replace("{phone=","");
                useremail=nm[2].replace("email=","");
                hotbutton.setText(username);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    public void logout(View view) {
        mauth.signOut();
    }

    public void openbidasset(View view) {
        Intent intent= new Intent(getApplicationContext(),postasset.class);
        startActivity(intent);
    }

    public void showmine(View view) {

        recyclerView.swapAdapter(secondadap,true);

    }
}