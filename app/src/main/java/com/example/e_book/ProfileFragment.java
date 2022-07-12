package com.example.e_book;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment
{
    @Nullable
    FirebaseAuth mauth;
    FirebaseFirestore fstore;
    StorageReference storageReference;
    StorageReference profile_img;
    TextView uname,uemail;
    String userid;
    ImageView e_p;
    Button order,payment,settings;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment, container,false);
        mauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userid=mauth.getCurrentUser().getUid();
        storageReference= FirebaseStorage.getInstance().getReference();
        profile_img=storageReference.child("users/"+mauth.getCurrentUser().getUid()+"/profile.jpg");
        profile_img.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                Picasso.get().load(uri).into(e_p);
            }
        });

        uname=view.findViewById(R.id.u_name);
        uemail=view.findViewById(R.id.u_email);
        e_p=view.findViewById(R.id.edit_profile);
        order=view.findViewById(R.id.my_orders);
        payment=view.findViewById(R.id.my_payments);
        settings=view.findViewById(R.id.settings);

        DocumentReference documentReference=fstore.collection("users").document(userid);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
               if(value != null && value.exists())
               {
                   uname.setText(value.getString("fname"));
                   uemail.setText(value.getString("email"));
               }
            }
        });


        Button logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), login.class));
                requireActivity().finish();
            }
        });



        e_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), Edit_Profile.class));
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),name_display.class);
                i.putExtra("name","your orders will be located here");
                startActivity(i);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),name_display.class);
                i.putExtra("name","your payments will be located here");
                startActivity(i);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),name_display.class);
                i.putExtra("name","App settings will be located here");
                startActivity(i);
            }
        });

        return view;
    }
}
