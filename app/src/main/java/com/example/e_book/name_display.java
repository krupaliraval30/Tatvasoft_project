package com.example.e_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class name_display extends AppCompatActivity {
    String input;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_display);
        display=findViewById(R.id.textView2);
        Intent i = getIntent();
        input=i.getStringExtra("name");
        display.setText(input);
    }
}