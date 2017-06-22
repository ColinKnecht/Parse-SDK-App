package com.colinknecht.contactsudemycourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {
    EditText etUsername, etPassword, etRetypePassword, etEmail;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etUsername = (EditText) findViewById(R.id.et_Username_ca);
        etPassword = (EditText) findViewById(R.id.et_Password_ca);
        etRetypePassword = (EditText) findViewById(R.id.et_RetypePassword_ca);
        etEmail = (EditText) findViewById(R.id.et_Email_ca);
        btnCreateAccount = (Button) findViewById(R.id.btn_createAccount_ca);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });/////////createAccount onClick
    }
}
