package com.colinknecht.contactsudemycourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin, btnCreateAccount, btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_Username_login);
        etPassword = (EditText) findViewById(R.id.et_Password_login);
        btnLogin = (Button) findViewById(R.id.btn_login_login);
        btnCreateAccount = (Button) findViewById(R.id.btn_createAccount_Login);
        btnResetPassword = (Button) findViewById(R.id.btn_resetPassword_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
