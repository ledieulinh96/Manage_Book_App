package com.example.phuth.manage_book_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phuth.manage_book_app.Adapter.BookAdapter;
import com.example.phuth.manage_book_app.DAO.BookDao;
import com.example.phuth.manage_book_app.DAO.CategoryDao;
import com.example.phuth.manage_book_app.Model.Book;
import com.example.phuth.manage_book_app.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    ListView lvBook;
    List<Book> listBook;
    BookDao bookDao;
    BookAdapter adapter;

    ArrayList<String> listSpinner;
    Spinner spinnerFilter;
    int indexSpinner;
    ImageButton imgBtnAddBook ;
    ImageButton imgSearch;
    EditText txtSearch;

    CategoryDao categoryDao;
    List<Category> list;

    static final int REQUEST_CODE_SUA = 1;
    public static final int RESULT_CODE_SUA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lvBook = findViewById(R.id.lvBook);
       imgBtnAddBook = findViewById(R.id.imgBtnAddBook);
       spinnerFilter = findViewById(R.id.spinerFilter);
        imgSearch = findViewById(R.id.imgSearch);
        txtSearch = findViewById(R.id.txtSearch);

        bookDao = new BookDao(this);
        loadFromDB();

        initSpinner();

        imgBtnAddBook.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        registerForContextMenu(lvBook);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFromDB();
    }

    private void loadFromDB() {
        listBook = bookDao.getAllBook("");
        adapter = new BookAdapter(getLayoutInflater(),listBook);
        adapter.notifyDataSetChanged();
        lvBook.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
         super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.mnXoa :
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Book book = listBook.get(menuInfo.position);
                bookDao.deleteBook(book);
                loadFromDB();
                break;

            case R.id.mnSua :
                AdapterView.AdapterContextMenuInfo menuInfosua = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Intent iMoManHinhSua = new Intent(HomeActivity.this,AddBookActivity.class);

                Book book1 = listBook.get(menuInfosua.position);
                iMoManHinhSua.putExtra("bookEdit",book1);
                startActivity(iMoManHinhSua);
                break;
        }
        return true;

    }


    private void initSpinner() {
        indexSpinner = 0;

        categoryDao = new CategoryDao(this);
        list = new ArrayList<>();
        list = categoryDao.getAllCategory();
        //name = 0
        listSpinner = new ArrayList<>();
        listSpinner.add("Name");
        // category 123...
        ArrayList<String> nameCategory = new ArrayList<>();
        for(int i = 0; i< list.size(); i++){
            listSpinner.add(list.get(i).getName());
        }

        ArrayAdapter<String> adapterSpiner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSpinner);
        spinnerFilter.setAdapter(adapterSpiner);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               indexSpinner = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnAddBook: {
                Intent intent = new Intent(HomeActivity.this, AddBookActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.imgSearch:{
                listBook.clear();
                if(indexSpinner == 0){
                    listBook.addAll(bookDao.getAllBook(txtSearch.getText().toString()));
                    adapter.notifyDataSetChanged();
                } else{
                    listBook.addAll(bookDao.getAllBook(indexSpinner));
                    adapter.notifyDataSetChanged();
                }
                Toast.makeText(this, "index = " + indexSpinner, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
