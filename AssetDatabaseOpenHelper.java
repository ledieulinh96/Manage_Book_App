package com.example.phuth.manage_book_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by phuth on 4/2/2018.
 */

public class AssetDatabaseOpenHelper {
    private static final String DB_NAME = "ManageBook.sqlite";
    private Context context;

    public AssetDatabaseOpenHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase StoreDatabase(){
        //data/data/{package cua ban}/databases
        String path = "/data/data/com.example.phuth.manage_book_app/databases";
        File pathDb = new File(path);
        try {
            if(!pathDb.exists()){
                pathDb.mkdir();

            }
            // ktra file do chua ton tai thi cop vao
            if(!new File(path + "/" + DB_NAME).exists()){
                copy(path);
            }
        } catch (IOException e){
            Log.d("IOException", e.getMessage());
        }
        Log.i("DB", context.getDatabasePath(DB_NAME).getPath());
        return SQLiteDatabase.openDatabase(context.getDatabasePath(DB_NAME).getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void copy(String path) throws IOException{
        // mo file trong thu muc assets
        InputStream is = context.getAssets().open(DB_NAME);
        FileOutputStream fos = new FileOutputStream(path + "/" + DB_NAME);
        byte buffer[] = new byte[1024];
        int length;

        // doc va ghi vao thu muc databases
        while((length = is.read(buffer))> 0){
            fos.write(buffer, 0, length);
        }
        is.close();
        fos.flush();
        fos.close();
    }

}
