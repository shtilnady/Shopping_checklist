package com.example.shoppingchecklist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey (autoGenerate = true)
    private int product_id;
    @ColumnInfo @NotNull
    private String product_name;

    public Product(String product_name){
        this.product_name = product_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
