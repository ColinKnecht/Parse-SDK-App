package com.colinknecht.contactsudemycourse;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccount extends AppCompatActivity {
    EditText etUsername, etPassword, etRetypePassword, etEmail;
    Button btnCreateAccount;
    private static final String TAG = "CreateAccount";

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
                if (etUsername.getText().toString() == null || etUsername.getText().toString().equals("")){
                    Toast.makeText(CreateAccount.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                else if (etPassword.getText().toString() == null || etPassword.getText().toString().equals("")) {
                    Toast.makeText(CreateAccount.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (etRetypePassword.getText().toString() == null || etRetypePassword.getText().toString().equals("")) {
                    Toast.makeText(CreateAccount.this, "Please Retype Password", Toast.LENGTH_SHORT).show();
                }
                else if (etEmail.getText().toString() == null || etEmail.getText().toString().equals("")) {
                    Toast.makeText(CreateAccount.this, "Please Retype Password", Toast.LENGTH_SHORT).show();
                }
                else if (!etPassword.getText().toString().equals(etRetypePassword.getText().toString())) {
                    Toast.makeText(CreateAccount.this, "Passwords are not Identical", Toast.LENGTH_SHORT).show();
                }
                else { //if there were no user errors typing in info....create account
                    final ProgressDialog progressDialog = new ProgressDialog(CreateAccount.this);
                    progressDialog.setMessage("Creating Account....Please Wait...");
                    progressDialog.show();

                    ParseUser user = new ParseUser();
                    user.setUsername(etUsername.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.setEmail(etEmail.getText().toString());

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) { //if there were no errors
                                progressDialog.dismiss();
                                Toast.makeText(CreateAccount.this, "Account Created Successfully! Welcome " + etUsername.getText().toString() + "!", Toast.LENGTH_SHORT).show();
                                CreateAccount.this.finish();//closes the class after create account was successful
                            }
                            else { //if there was an error
                                progressDialog.dismiss();
                                Toast.makeText(CreateAccount.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "done: " + e.getMessage() );
                                //Log.e(TAG, "done: ", e.printStackTrace() );
                            }
                        }
                    });
                }

            }
        });/////////createAccount onClick
    }
}
