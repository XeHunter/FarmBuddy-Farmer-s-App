package com.example.farmbuddy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter2 extends FirebaseRecyclerAdapter<MainModel,MainAdapter2.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter2(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull MainModel model) {

        holder.Company_Name.setText(model.getCompany_Name());
        holder.Email.setText(model.getEmail());
        holder.Contact.setText(model.getContact());
        holder.Location.setText(model.getLocation());
        holder.Product.setText(model.getProduct());
        holder.Price.setText(model.getPrice());
        String email = holder.Email.getText().toString();

        Glide.with(holder.img.getContext())
                .load(model.getCurl())
                .placeholder(R.drawable.chip)
                .circleCrop()
                .error(R.drawable.chip)
                .into(holder.img);


        holder.btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", email , null));
                view.getContext().startActivity(i);
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item2,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView Company_Name,Email,Contact,Location,Product,Price;
        Button btnContact,btnSave;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            Company_Name = itemView.findViewById(R.id.cnametext);
            Email = itemView.findViewById(R.id.email);
            Contact = itemView.findViewById(R.id.contact);
            Location = itemView.findViewById(R.id.location);
            Product = itemView.findViewById(R.id.product);
            Price = itemView.findViewById(R.id.price);
            btnContact = itemView.findViewById(R.id.btnContact);
            btnSave = itemView.findViewById(R.id.btnSave);
        }
    }
}
