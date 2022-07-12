package com.example.e_book;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Edit_Profile extends AppCompatActivity {

    TextInputEditText fullName, email, phoneNo, password;
    TextView username;
    FirebaseAuth mauth;
    FirebaseFirestore fstore;
    String userid,n,e,p,pw;
    Button update;
    ImageView profile_image;
    DocumentReference reference;
    StorageReference storageReference,profile_img;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        username=findViewById(R.id.user_name);
        update=findViewById(R.id.Update);
        profile_image=findViewById(R.id.Profile_Image);

        mauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        profile_img=storageReference.child("users/"+ Objects.requireNonNull(mauth.getCurrentUser()).getUid()+"/profile.jpg");
        profile_img.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                Picasso.get().load(uri).into(profile_image);
            }
        });

        userid= Objects.requireNonNull(mauth.getCurrentUser()).getUid();
        showuserdata();

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent open_gallary = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(open_gallary,1000);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                update_profile();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                Uri img_uri = data.getData();
                profile_image.setImageURI(img_uri);
                upload_img_to_firebase(img_uri);
            }
        }
    }

    private void upload_img_to_firebase(Uri img_uri)
    {
        StorageReference fileref = storageReference.child("users/"+ Objects.requireNonNull(mauth.getCurrentUser()).getUid()+"/profile.jpg");
        fileref.putFile(img_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Edit_Profile.this, "profile pic updated", Toast.LENGTH_SHORT).show();
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri)
                    {
                        Picasso.get().load(uri).into(profile_image);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Edit_Profile.this, "Failed to update", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showuserdata()
    {
        reference=fstore.collection("users").document(userid);
        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                fullName.setText(value.getString("fname"));
                email.setText(value.getString("email"));
                phoneNo.setText(value.getString("phone_no"));
                username.setText(value.getString("username"));
                password.setText(value.getString("password"));
                n= Objects.requireNonNull(fullName.getText()).toString();
                e= Objects.requireNonNull(email.getText()).toString();
                p= Objects.requireNonNull(phoneNo.getText()).toString();
                pw= Objects.requireNonNull(password.getText()).toString();
            }
        });
    }
    private void update_profile()
    {
        if(!n.equals(Objects.requireNonNull(fullName.getText()).toString()) ||
                e.equals(Objects.requireNonNull(email.getText()).toString()) ||
                p.equals(Objects.requireNonNull(phoneNo.getText()).toString()) ||
                pw.equals(Objects.requireNonNull(password.getText()).toString()))
        {
            reference=fstore.collection("users").document(userid);
            reference.update("fname",fullName.getText().toString());
            reference.update("email", Objects.requireNonNull(email.getText()).toString());
            reference.update("phone_no", Objects.requireNonNull(phoneNo.getText()).toString());
            reference.update("password", Objects.requireNonNull(password.getText()).toString());
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }
}