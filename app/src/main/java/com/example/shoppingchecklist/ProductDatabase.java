package com.example.shoppingchecklist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Product.class }, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao getProductDao();
}