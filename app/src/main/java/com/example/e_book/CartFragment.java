package com.example.e_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment{
    RecyclerView cart_recycle;
    cart_adapter ca;
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();
    ArrayList<cart_item_model> datalist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);

        ca = new cart_adapter(datalist);
        cart_recycle= view.findViewById(R.id.cart_rv);
        cart_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        cart_recycle.setAdapter(ca);
        fstore.collection("users").document(Objects.requireNonNull(mauth.getCurrentUser()).getUid()).collection("cart").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            String docid = d.getId();
                            cart_item_model cim = d.toObject((cart_item_model.class));
                            cim.setDocid(docid);
                            datalist.add(cim);
                        }
                        ca.notifyDataSetChanged();
                    }
                });
        return view;
    }
}
