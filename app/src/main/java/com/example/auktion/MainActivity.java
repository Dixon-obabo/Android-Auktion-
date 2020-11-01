package com.example.auktion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;

import ui.asset;
import ui.myadatpter;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mauth;
    FirebaseDatabase database;
    DatabaseReference dbref;

    //dbref=database.getReference("posts") FirebaseUser currentuser;
    private FirebaseStorage storage;
    RecyclerView recyclerView;
    myadatpter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
    //currentuser=mauth.getCurrentUser();
    //dbref=database.getReference("posts");
//    Login_status();
//
        getdata();
    }

    public void Login_status(){
//        if(currentuser==null){
//            Intent intent= new Intent(getApplicationContext(),login.class);
//            startActivity(intent);
//        }else {
//            Toast.makeText(this, "Hello:)", Toast.LENGTH_SHORT).show();
//        }
    }



//    ChildEventListener childEventListener= new ChildEventListener() {
//    @Override
//    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//    }
//
//    @Override
//    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//    }
//
//    @Override
//    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//    }
//
//    @Override
//    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError error) {
//
//    }
//};


    public void getdate(){
//        dbref.addChildEventListener(childEventListener);

    }
public void getdata(){
    Query query=FirebaseDatabase.getInstance().getReference("posts");
    FirebaseRecyclerOptions<asset> options= new FirebaseRecyclerOptions.Builder<asset>().setQuery(FirebaseDatabase.getInstance().getReference("posts"),asset.class).build();

adapter= new myadatpter(options);
    //
//    adapter= new FirebaseRecyclerAdapter<asset,assetviewholder>(options) {
//        @NonNull
//        @Override
//        public assetviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.asset_card,parent,false);
//            return new assetviewholder(view);
//        }
//
//        @Override
//        protected void onBindViewHolder(@NonNull assetviewholder holder, int position, @NonNull asset model) {
//            holder.desc.setText(model.getDescription());
//            holder.name.setText(model.getName());
//            holder.price.setText(model.getPrice());
//        }
//    };

    //recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
    }

    public void getsome(View view) {
if (FirebaseDatabase.getInstance().getReference("posts")!=null){
    Toast.makeText(this, "the path isnt empty", Toast.LENGTH_SHORT).show();
}
else {
    Toast.makeText(this, "it is empty", Toast.LENGTH_SHORT).show();
}
    }


    private class assetviewholder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView desc;
        public assetviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.asset_name);
            price=itemView.findViewById(R.id.asset_price);
            desc=itemView.findViewById(R.id.asset_desciption);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}