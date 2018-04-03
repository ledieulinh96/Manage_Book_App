package com.example.phuth.manage_book_app;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phuth.manage_book_app.DAO.AccountDao;
import com.example.phuth.manage_book_app.DAO.BookDao;
import com.example.phuth.manage_book_app.DAO.CategoryDao;
import com.example.phuth.manage_book_app.Model.Account;
import com.example.phuth.manage_book_app.Model.Book;
import com.example.phuth.manage_book_app.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    AccountDao accountDao;

    EditText txtUsername, txtPassword, txtPassword2;
    Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);

        accountDao = new AccountDao(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignup:
                Account account2 = new Account();
                account2.setUsername(txtUsername.getText().toString());
                account2.setPassword(txtPassword.getText().toString());
                if(!txtPassword2.getText().toString().equals(txtPassword.getText().toString())){
                    Toast.makeText(this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean check2 = accountDao.addAccount(account2);
                if(check2 == true){
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT ).show();
                }
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}
