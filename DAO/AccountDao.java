package com.example.phuth.manage_book_app.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phuth.manage_book_app.Model.Account;
import com.example.phuth.manage_book_app.AssetDatabaseOpenHelper;

/**
 * Created by phuth on 4/1/2018.
 */

public class AccountDao{
    SQLiteDatabase database;
    AssetDatabaseOpenHelper assetDatabaseOpenHelper;

    public AccountDao(Context context){
        assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(context);
        database = assetDatabaseOpenHelper.StoreDatabase();
    }


    public boolean checkAccount(Account account){
        String cautruyvan = "select * from tblAccount where username = ? and password = ?";
        Cursor cursor = database.rawQuery(cautruyvan,new String[]{account.getUsername(), account.getPassword()});
        return cursor.moveToFirst();
    }

    public boolean addAccount(Account account){
        ContentValues contentValues = new ContentValues();
//        contentValues.put(TaoDatabase.ID_TB,""); bởi vì tự động tăng
        contentValues.put("username",account.getUsername().toString());
        contentValues.put("password",account.getPassword().toString());

        long id_ = database.insert("tblAccount",null,contentValues);

        if(id_ != 0){
            return true;
        }
        return false;
    }

}
