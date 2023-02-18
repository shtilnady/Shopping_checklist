package com.example.shoppingchecklist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertProduct(Product product);
    @Delete
    void deleteProduct(Product product);
    @Query("SELECT * FROM products")
    List<Product> getAll();
    @Query("SELECT * FROM products WHERE product_id = :id")
    Product getProduct(int id);
    @Query("UPDATE products SET product_name = :newName WHERE product_id = :id")
    void setName(String newName, int id);
}
