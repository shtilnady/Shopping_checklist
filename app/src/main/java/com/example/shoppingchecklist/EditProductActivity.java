package com.example.shoppingchecklist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class EditProductActivity extends AppCompatActivity {

    Button saveBtn;
    Button removeBtn;
    EditText productName;
    List<Product> list;
    int flag;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        getSupportActionBar().setTitle("Редактировать");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0BB7CD")));

    }

    @Override
    protected void onStart() {
        productId = getIntent().getExtras().getInt("id");

        saveBtn = findViewById(R.id.save);
        removeBtn = findViewById(R.id.remove);
        productName = findViewById(R.id.editProductName);
        productName.setText(getIntent().getExtras().getString("name"));
        saveBtn.setOnClickListener(view -> {
            flag = 0;
            new Thread(){
                @Override
                public void run() {
                    ProductManager.getInstance(getApplicationContext())
                            .getProductDao().
                            setName(productName.getText().toString(), productId);
                    flag = 1;
                    while (flag == 0) {}
                    list = ProductManager.getInstance(getApplicationContext())
                            .getProductDao().getAll();
                    flag = 2;
                }
            }.start();
            while (flag<2) {}
            ProductManager.getAdapter(list).setList(list);
            finish();
        });
        removeBtn.setOnClickListener(view -> {
            flag = 0;
            new Thread(){
                @Override
                public void run() {
                    Product product = ProductManager.getInstance(getApplicationContext())
                            .getProductDao().
                                    getProduct(productId);
                    ProductManager.getInstance(getApplicationContext())
                            .getProductDao().
                            deleteProduct(product);
                    flag = 1;
                    while (flag == 0) {}
                    list = ProductManager.getInstance(getApplicationContext())
                            .getProductDao().getAll();
                    flag = 2;
                }
            }.start();
            while (flag<2) {}
            ProductManager.getAdapter(list).setList(list);
            finish();
        });
        super.onStart();
    }
}