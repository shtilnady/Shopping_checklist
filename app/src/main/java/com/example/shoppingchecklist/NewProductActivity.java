package com.example.shoppingchecklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewProductActivity extends AppCompatActivity {

    Button saveBtn;
    EditText productName;
    List<Product> list;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Добавить");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0BB7CD")));

        saveBtn = findViewById(R.id.save);
        productName = findViewById(R.id.editProductName);
        saveBtn.setOnClickListener(view -> {
            flag = 0;
            Product newProduct = new Product(productName.getText().toString());
            new Thread(){
                @Override
                public void run() {
                    ProductManager.getInstance(getApplicationContext())
                            .getProductDao().
                            insertProduct(newProduct);
                    list = ProductManager.getInstance(getApplicationContext())
                            .getProductDao().getAll();
                    flag = 1;
                }
            }.start();
            while (flag==0) {}
            ProductManager.getAdapter(list).addProduct(newProduct);
            System.out.println("yweeeeeeeeee11111111");
            finish();
        });
    }
}