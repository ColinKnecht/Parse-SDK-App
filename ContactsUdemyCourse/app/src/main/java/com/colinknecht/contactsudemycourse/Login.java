package com.colinknecht.contactsudemycourse;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

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
                LayoutInflater pwInflater = getLayoutInflater();
                final View passwordResetLayout = pwInflater.inflate(R.layout.reset_password_popup,null);//connects reset button to forgotPassword xml file
                final EditText etMail = (EditText) passwordResetLayout.findViewById(R.id.et_hint_password_reset);//etMail grabs the EditText from forgotPassword xml file

                AlertDialog.Builder dlgReset = new AlertDialog.Builder(Login.this);//shows popup message over LoginActivity
                dlgReset.setView(passwordResetLayout);//set View to Layout defined on line 108 and 109
                dlgReset.setTitle("Reset Password");

                dlgReset.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pdia = new ProgressDialog(Login.this);//shows progressDialog over Login Activity
                        pdia.setMessage("Sending message to your email for password reset");
                        pdia.show();//shows progress dialog

                        ParseUser.requestPasswordResetInBackground(etMail.getText().toString(),
                                new RequestPasswordResetCallback() { //calls ParseUser class to reset password
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {//if there was NO error... i.e. ParseException
                                    Toast.makeText(Login.this, "Reset Instructions Sent To Your Email", Toast.LENGTH_SHORT).show();
                                    pdia.dismiss();
                                }
                                else {//if theres an error
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    pdia.dismiss();//dismisses Progress Dialog
                                }
                            }
                        });
                    }
                });

                dlgReset.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dlgReset.show();//shows popup window

            }
        });//resetPassword onClick

    }
}
