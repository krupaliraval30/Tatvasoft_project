package com.example.e_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class global_search extends AppCompatActivity
{
    RecyclerView search_recycle;
    SearchView sview;
    search_item_adapter sia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_search);
        search_recycle=findViewById(R.id.recyclerView);
        search_recycle.setLayoutManager(new LinearLayoutManager(this));

        sview=findViewById(R.id.searchView);
        sview.setIconified(false);
        sview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });
    }
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
    protected void processsearch(String s)
    {
        FirebaseRecyclerOptions<search_item_model> options =
                new FirebaseRecyclerOptions.Builder<search_item_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("book").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), search_item_model.class)
                        .build();
        sia = new search_item_adapter(options);
        sia.startListening();
        search_recycle.setAdapter(sia);

    }


}