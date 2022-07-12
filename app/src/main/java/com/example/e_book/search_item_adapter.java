package com.example.e_book;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class search_item_adapter extends FirebaseRecyclerAdapter<search_item_model,search_item_adapter.my_view_holder>
{
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();

    public search_item_adapter(FirebaseRecyclerOptions<search_item_model> options)
    {
        super(options);
    }
    protected void onBindViewHolder(my_view_holder holder,int position, search_item_model model)
    {
        holder.book_nm.setText(model.getname());
        holder.book_mrp.setText(model.getmrp());
        Glide.with(holder.book_img.getContext())
                .load(model.getpic())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.book_img);
        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> cart = new HashMap<>();
                cart.put("name", model.getname());
                cart.put("mrp", model.getmrp());
                cart.put("pic",model.getpic());

                fstore.collection("users").document(Objects.requireNonNull(mauth.getCurrentUser()).getUid()).collection("cart").add(cart).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(v.getContext(), "added to cart", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    @NonNull
    @Override
    public search_item_adapter.my_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new my_view_holder(view);
    }
    static class my_view_holder extends RecyclerView.ViewHolder
    {
        ImageView book_img;
        TextView book_nm,book_mrp;
        Button add_cart;
        public my_view_holder(View itemview)
        {
            super(itemview);

            book_img=itemview.findViewById(R.id.img);
            book_nm=itemview.findViewById(R.id.name);
            book_mrp=itemview.findViewById(R.id.price);
            add_cart=itemview.findViewById(R.id.cart_btn);
        }
    }
}

