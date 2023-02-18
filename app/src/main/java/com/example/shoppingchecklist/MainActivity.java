package com.example.shoppingchecklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addBtn;
    List<Product> list;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Чек-лист покупок");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0BB7CD")));

        flag = 0;
        new Thread(){
            @Override
            public void run() {
                list = ProductManager
                        .getInstance(getApplicationContext())
                        .getProductDao()
                        .getAll();
                flag = 1;
            }
        }.start();
        final RecyclerView recyclerTrips = findViewById(R.id.recycler);
        while (flag==0) {}
        recyclerTrips.setAdapter(ProductManager.getAdapter(list));

        recyclerTrips.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addBtn = findViewById(R.id.add);
        addBtn.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), NewProductActivity.class);
            startActivity(i);
        });
        System.out.println("createeeeee");
    }

    @Override
    protected void onRestart() {
        final RecyclerView recyclerTrips = findViewById(R.id.recycler);
        System.out.println("restaaaaaaaaaarrt");
        recyclerTrips.setAdapter(ProductManager.getAdapter(list));
        super.onRestart();
    }

    @Override
    protected void onStart() {
        final RecyclerView recyclerTrips = findViewById(R.id.recycler);
        System.out.println("staaaaaarrt");
        recyclerTrips.setAdapter(ProductManager.getAdapter(list));
        super.onStart();
    }
}