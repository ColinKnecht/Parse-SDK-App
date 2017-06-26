package com.colinknecht.contactsudemycourse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "Login";
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
                            if (user != null) {//user has logged in successfully
                                boolean verified = user.getBoolean("emailVerified");//grabs the boolean from the parse.com website database
                                Log.d(TAG, "logInInBackground done: Boolean verified =" + verified);

                                if (!verified) { //if users not verified
                                    Toast.makeText(Login.this, "Please verify email before logging in", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    ParseUser.logOut();
                                }
                                else { // user has been verified
                                    Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Login.this, ContactList.class);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    Login.this.finish();//finish login activity
                                }
                            }
                            else { //there may be an exception
                                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "logInInBackground done: " + e.getMessage() );
                                progressDialog.dismiss();
                            }
                        }
                    });
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
