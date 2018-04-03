package com.example.phuth.manage_book_app.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.phuth.manage_book_app.AssetDatabaseOpenHelper;
import com.example.phuth.manage_book_app.Model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by phuth on 4/2/2018.
 */

public class BookDao implements Serializable{
    private static final String TABLE_BOOK = "tblBook";
    private static final String ID = "id";
    private static final String TITLE = "name";
    private static final String AUTHOR = "author";
    private static final String PUBLISHER = "publisher";
    private static final String CATEGORY_ID = "idCategory";
    SQLiteDatabase database;
    AssetDatabaseOpenHelper assetDatabaseOpenHelper;

    public BookDao(Context context){
        assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(context);
        database = assetDatabaseOpenHelper.StoreDatabase();
    }

    public List<Book> getAllBook(String s) {
        List<Book> books = new ArrayList<>();
        String query = "select * from "+TABLE_BOOK + " where " + TITLE + " like '%" + s + "%'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                books.add(new Book(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());

        }
        return books;
    }

    public boolean addBook(Book book, int categoryID) {
        ContentValues values = new ContentValues();
  //      values.put(ID, book.getId());
        values.put(TITLE, book.getTitle());
        values.put(AUTHOR, book.getAuthor());
        values.put(PUBLISHER, book.getPublisher());
        values.put(CATEGORY_ID, categoryID+1);
        long kt = database.insert(TABLE_BOOK, null, values);
        if(kt != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateBook(Book book, int categoryID) {
        ContentValues values = new ContentValues();
        values.put(TITLE, book.getTitle());
        values.put(AUTHOR, book.getAuthor());
        values.put(PUBLISHER, book.getPublisher());
        values.put(CATEGORY_ID, categoryID+1);
        int kt = database.update(TABLE_BOOK, values, ID +"=" +book.getId(), null);
        if(kt != 0){
            return true;
        }else{
            return false;
        }
    }


    public List<Book> getAllBook(int categoryID) {
        List<Book> lsBooks = new ArrayList<>();
        String query = "select * from " + TABLE_BOOK + " where idCategory = "+ categoryID;

        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                lsBooks.add(new Book(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return lsBooks;
    }


    public boolean deleteBook(Book book){
        int kt = database.delete(TABLE_BOOK,ID + " = " + book.getId(),null);
        if(kt != 0){
            return true;
        }else{
            return false;
        }
    }

    public int getIdCategory(Book book) {
        String query = "select idCategory from " + TABLE_BOOK +" where id = " + book.getId();

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int t =  cursor.getInt(0);
            return t;
        }
        return -1;
    }


}
