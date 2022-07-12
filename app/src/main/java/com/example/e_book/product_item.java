package com.example.e_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class product_item extends AppCompatActivity {
    ImageView book;
    TextView name;
    int img_value;
    String book_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item);
        book=findViewById(R.id.book_img);
        name=findViewById(R.id.book_name);

        Bundle receiver = getIntent().getExtras();
        book_name=receiver.getString("book");
        name.setText(book_name);
        img_value=receiver.getInt("img");
        book.setImageResource(img_value);
    }
}

