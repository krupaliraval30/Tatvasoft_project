package com.example.e_book;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageView ANDROID, OS, CN, JAVA, PHP, HTML, PYTHON, OOP, CP, IOT, MPI, ASP, MATLAB;
    TextView search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        ANDROID = view.findViewById(R.id.i1);
        OS = view.findViewById(R.id.i2);
        CN = view.findViewById(R.id.i3);
        JAVA = view.findViewById(R.id.i4);
        PHP = view.findViewById(R.id.i5);
        HTML = view.findViewById(R.id.i6);
        PYTHON = view.findViewById(R.id.i7);
        OOP = view.findViewById(R.id.i8);
        CP = view.findViewById(R.id.i9);
        IOT = view.findViewById(R.id.i10);
        MPI = view.findViewById(R.id.i11);
        ASP = view.findViewById(R.id.i12);
        MATLAB = view.findViewById(R.id.i13);
        search=view.findViewById(R.id.searchView);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),global_search.class));
            }
        });

        ANDROID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ANDROID = new Intent(getActivity(), product_item.class);
                ANDROID.putExtra("book", "Android Programming");
                ANDROID.putExtra("img" , R.drawable.android);
                startActivity(ANDROID);
            }
        });

        OS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OS = new Intent(getActivity(), product_item.class);
                OS.putExtra("book", "Operating System");
                OS.putExtra("img" , R.drawable.os);
                startActivity(OS);
            }
        });

        CN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CN = new Intent(getActivity(), product_item.class);
                CN.putExtra("book", "Computer Network");
                CN.putExtra("img", R.drawable.c16);
                startActivity(CN);
            }
        });

        JAVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent JAVA = new Intent(getActivity(), product_item.class);
                JAVA.putExtra("book", "JAVA");
                JAVA.putExtra("img", R.drawable.java);
                startActivity(JAVA);
            }
        });

        PHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PHP = new Intent(getActivity(), product_item.class);
                PHP.putExtra("book", "PHP");
                PHP.putExtra("img", R.drawable.c7);
                startActivity(PHP);
            }
        });

        HTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HTML = new Intent(getActivity(), product_item.class);
                HTML.putExtra("book", "HTML & CSS");
                HTML.putExtra("img", R.drawable.c5);
                startActivity(HTML);
            }
        });

        PYTHON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PYTHON = new Intent(getActivity(), product_item.class);
                PYTHON.putExtra("book", "PYTHON");
                PYTHON.putExtra("img", R.drawable.c8);
                startActivity(PYTHON);
            }
        });

        OOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OOP = new Intent(getActivity(), product_item.class);
                OOP.putExtra("book", "C++");
                OOP.putExtra("img", R.drawable.c1);
                startActivity(OOP);
            }
        });

        CP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CP = new Intent(getActivity(), product_item.class);
                CP.putExtra("book", "CP");
                CP.putExtra("img", R.drawable.c4);
                startActivity(CP);
            }
        });

        IOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IOT = new Intent(getActivity(), product_item.class);
                IOT.putExtra("book", "IOT");
                IOT.putExtra("img", R.drawable.c6);
                startActivity(IOT);
            }
        });

        MPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MPI = new Intent(getActivity(), product_item.class);
                MPI.putExtra("book", "MPI");
                MPI.putExtra("img", R.drawable.c3);
                startActivity(MPI);
            }
        });

        ASP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ASP = new Intent(getActivity(), product_item.class);
                ASP.putExtra("book", "ASP.N");
                ASP.putExtra("img", R.drawable.c9);
                startActivity(ASP);
            }
        });

        MATLAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MATLAB = new Intent(getActivity(), product_item.class);
                MATLAB.putExtra("book", "MATLAB");
                MATLAB.putExtra("img", R.drawable.c16);
                startActivity(MATLAB);
            }
        });
        return view;
    }
}
