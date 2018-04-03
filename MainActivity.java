package com.example.phuth.manage_book_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phuth.manage_book_app.DAO.AccountDao;
import com.example.phuth.manage_book_app.Model.Account;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    AccountDao accountDao;

    EditText txtUsername, txtPassword;
    Button btnSignup, btnSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignin = findViewById(R.id.btnSignin);
        btnSignin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        accountDao = new AccountDao(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignin:
                Account account = new Account();
                account.setUsername(txtUsername.getText().toString());
                account.setPassword(txtPassword.getText().toString());
                boolean check = accountDao.checkAccount(account);
                if(check) {
        //            Toast.makeText(this, "check = " + check, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.btnSignup:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
}
