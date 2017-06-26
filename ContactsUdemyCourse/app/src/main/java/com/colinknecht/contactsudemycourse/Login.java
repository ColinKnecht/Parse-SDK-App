package com.colinknecht.contactsudemycourse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin, btnCreateAccount, btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser user = ParseUser.getCurrentUser(); //getCurrentUser checks to see if there is a user currently logged in
        if (user == null) { //if no one is logged in
            setContentView(R.layout.activity_login);//no one is logged in so you need to show login activity

            etUsername = (EditText) findViewById(R.id.et_Username_login);
            etPassword = (EditText) findViewById(R.id.et_Password_login);
            btnLogin = (Button) findViewById(R.id.btn_login_login);
            btnCreateAccount = (Button) findViewById(R.id.btn_createAccount_Login);
            btnResetPassword = (Button) findViewById(R.id.btn_resetPassword_login);

            ActionBar actionBar = getSupportActionBar();//Action Bar is top of app that displays app name
            actionBar.setTitle("Login");
        }
        else {//someone is already logged in
            //direct user to first page after logging in
            Intent intent = new Intent(this, ContactList.class);
            startActivity(intent);
            this.finish();//finish Login Activity
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString() == null || etUsername.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                else if (etPassword.getText().toString() == null || etPassword.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else {//If user has filled in username and password
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Logging You In Please Wait");
                    progressDialog.show();

                    ParseUser.logInInBackground(etUsername.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            
                        }
                    })
                }

            }
        });////login onClick

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateAccount.class);
                startActivity(intent);
            }
        });//createAccount onClick

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });//resetPassword onClick

    }
}
