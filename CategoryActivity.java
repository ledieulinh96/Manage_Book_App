package com.example.phuth.manage_book_app;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.phuth.manage_book_app.DAO.CategoryDao;
import com.example.phuth.manage_book_app.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends ListActivity {

    CategoryDao categoryDao;
    List<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryDao = new CategoryDao(this);
        list = new ArrayList<>();
        list = categoryDao.getAllCategory();

        ArrayList<String> nameCategory = new ArrayList<>();
        for(int i = 0; i< list.size(); i++){
            nameCategory.add(list.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameCategory);
        setListAdapter(adapter);
    }
}
