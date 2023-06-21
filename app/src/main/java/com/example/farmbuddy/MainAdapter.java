package com.example.farmbuddy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
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

        Glide.with(holder.img.getContext())
                .load(model.getCurl())
                .placeholder(R.drawable.chip)
                .circleCrop()
                .error(R.drawable.chip)
                .into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                View v = dialogPlus.getHolderView();
                TextInputLayout c_name = v.findViewById(R.id.c_name);
                TextInputLayout u_email = v.findViewById(R.id.u_email);
                TextInputLayout u_contact = v.findViewById(R.id.u_contact);
                TextInputLayout u_location = v.findViewById(R.id.u_location);
                TextInputLayout u_product = v.findViewById(R.id.u_product);
                TextInputLayout u_price = v.findViewById(R.id.u_price);
                TextInputLayout u_curl = v.findViewById(R.id.curl);
                Button btnUpdate = v.findViewById(R.id.btnUpdate);

                c_name.getEditText().setText(model.getCompany_Name());
                u_email.getEditText().setText(model.getEmail());
                u_contact.getEditText().setText(model.getContact());
                u_location.getEditText().setText(model.getLocation());
                u_product.getEditText().setText(model.getProduct());
                u_price.getEditText().setText(model.getPrice());
                u_curl.getEditText().setText(model.getCurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Company_Name",c_name.getEditText().getText().toString());
                        map.put("Email",u_email.getEditText().getText().toString());
                        map.put("Contact",u_contact.getEditText().getText().toString());
                        map.put("Location",u_location.getEditText().getText().toString());
                        map.put("Product",u_product.getEditText().getText().toString());
                        map.put("price",u_price.getEditText().getText().toString());
                        map.put("curl",u_curl.getEditText().getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Vendor")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Company_Name.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.Company_Name.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Company_Name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Vendor")
                                        .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.Company_Name.getContext(), "Data deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.Company_Name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView Company_Name,Email,Contact,Location,Product,Price;
        Button btnEdit,btnDelete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            Company_Name = itemView.findViewById(R.id.cnametext);
            Email = itemView.findViewById(R.id.email);
            Contact = itemView.findViewById(R.id.contact);
            Location = itemView.findViewById(R.id.location);
            Product = itemView.findViewById(R.id.product);
            Price = itemView.findViewById(R.id.price);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
