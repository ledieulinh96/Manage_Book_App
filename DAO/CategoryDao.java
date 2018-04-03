package com.example.phuth.manage_book_app.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phuth.manage_book_app.AssetDatabaseOpenHelper;
import com.example.phuth.manage_book_app.Model.Account;
import com.example.phuth.manage_book_app.Model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuth on 4/2/2018.
 */

public class CategoryDao {
    SQLiteDatabase database;
    AssetDatabaseOpenHelper assetDatabaseOpenHelper;

    private static final String TABLE_CATEGORY = "tblCategory";

    public CategoryDao(Context context){
        assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(context);
        database = assetDatabaseOpenHelper.StoreDatabase();
    }

    public List<Category> getAllCategory(){
        List<Category> lsCategory = new ArrayList<>();
        String query = "select * from "+ TABLE_CATEGORY;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lsCategory.add(new Category(cursor.getInt(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return lsCategory;
    }
}

