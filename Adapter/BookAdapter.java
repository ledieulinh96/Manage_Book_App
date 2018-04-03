package com.example.phuth.manage_book_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.phuth.manage_book_app.Model.Book;
import com.example.phuth.manage_book_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuth on 4/2/2018.
 */

public class BookAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Book> list;

    public BookAdapter(LayoutInflater inflater, List<Book> list) {
        this.inflater = inflater;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Book getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private TextView txtName,txtAuthor,txtPublisher;
    @Override
    public View getView(int position, View v, ViewGroup parent){
        Book book = list.get(position);
        if(v == null) {
            v = inflater.inflate(R.layout.item_book, null);
        }
        txtName = v.findViewById(R.id.txtBookName);
        txtAuthor = v.findViewById(R.id.txtAuthor);
        txtPublisher = v.findViewById(R.id.txtKindOfBook);
        txtPublisher.setText(book.getPublisher());
        txtAuthor.setText(book.getAuthor());
        txtName.setText(book.getTitle());
        return v;
    }
}