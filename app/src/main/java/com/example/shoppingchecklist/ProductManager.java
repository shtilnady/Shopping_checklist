package com.example.shoppingchecklist;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class ProductManager {
    private static ProductDatabase productDatabase;
    private static ProductAdapter productAdapter;

    public static void setProductAdapter(ProductAdapter productAdapter) {
        ProductManager.productAdapter = productAdapter;
    }

    private ProductManager(){

    }

    public static ProductDatabase getInstance(Context context){
        synchronized (ProductManager.class){
            if (productDatabase == null){
                productDatabase = Room
                        .databaseBuilder(context, ProductDatabase.class, "products.db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return productDatabase;
        }
    }
    public static ProductAdapter getAdapter(List<Product> list){
        synchronized (ProductAdapter.class){
            if (productAdapter == null){
                productAdapter = new ProductAdapter(list);
            }
            return productAdapter;
        }
    }
}
