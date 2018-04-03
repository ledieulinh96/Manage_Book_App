package com.example.phuth.manage_book_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuth.manage_book_app.DAO.BookDao;
import com.example.phuth.manage_book_app.DAO.CategoryDao;
import com.example.phuth.manage_book_app.Model.Book;
import com.example.phuth.manage_book_app.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtTitle, txtAuthor, txtPublisher;
    Spinner spinner;
    Button btnSave, btnCancel;
    BookDao bookDao;
    boolean edit = false;
    Book bookEdit;

    CategoryDao categoryDao;
    List<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPublisher = findViewById(R.id.txtPublisher);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        spinner = findViewById(R.id.spinner);


        bookEdit = (Book) getIntent().getSerializableExtra("bookEdit");
        if(bookEdit != null){
            edit = true;
            txtTitle.setText(bookEdit.getTitle());
            txtAuthor.setText(bookEdit.getAuthor());
            txtPublisher.setText(bookEdit.getPublisher());
        }
         else{
            edit = false;
        }
        loadSpinner();

        btnSave.setOnClickListener(this);
        bookDao = new BookDao(this);
    }

    private void loadSpinner() {
        categoryDao = new CategoryDao(this);
        list = new ArrayList<>();
        list = categoryDao.getAllCategory();

        ArrayList<String> nameCategory = new ArrayList<>();
        for(int i = 0; i< list.size(); i++){
            nameCategory.add(list.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameCategory);
        spinner.setAdapter(adapter);
        if(edit){
  //          spinner.setSelection(bookDao.getIdCategory(bookEdit));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:

                  int indexCategory = spinner.getSelectedItemPosition();

                if(!edit) {
                    Book book = new Book(0, txtTitle.getText().toString(), txtAuthor.getText().toString(), txtPublisher.getText().toString());
                    boolean check = bookDao.addBook(book, indexCategory);
                    if (check) {
                        Toast.makeText(this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    bookEdit.setAuthor(txtAuthor.getText().toString());
                    bookEdit.setTitle(txtTitle.getText().toString());
                    bookEdit.setPublisher(txtPublisher.getText().toString());
                    boolean check = bookDao.updateBook(bookEdit, indexCategory);
                    if(check){
                        Toast.makeText(this, "Sửa thông tin sách thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                moveToHome();

            case R.id.btnCancel:
                moveToHome();
        }
    }

    private void moveToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

