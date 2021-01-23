package ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.auktion.Bidasset;
import com.example.auktion.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

public class storeadapter extends FirestoreRecyclerAdapter<asset,storeadapter.myviewholder> {


    public storeadapter(@NonNull FirestoreRecyclerOptions<asset> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull asset model) {

        holder.desc.setText(model.getDescription());
        holder.price.setText(model.getPrice()+" Ksh");
        holder.name.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(holder.itemView.getContext(), Bidasset.class);
                intent.putExtra("asset_name",model.getName());
                intent.putExtra("asset_price",model.getPrice());
                intent.putExtra("asset_desc",model.getDescription());
                intent.putExtra("key",model.getKey());
                intent.putExtra("date",model.getDate());
                holder.itemView.getContext().startActivity(intent);

            }
        });
        FirebaseStorage.getInstance().getReference("cool").child(model.getKey()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.imageView.getContext()).load(uri).into(holder.imageView);
            }

        });


    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_card,parent,false);
        return new myviewholder(view);
    }



    class myviewholder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView desc;
        ImageView imageView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.asset_name);
            price=itemView.findViewById(R.id.asset_price);
            desc=itemView.findViewById(R.id.asset_desciption);
            imageView=itemView.findViewById(R.id.asset_image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

    }

}
