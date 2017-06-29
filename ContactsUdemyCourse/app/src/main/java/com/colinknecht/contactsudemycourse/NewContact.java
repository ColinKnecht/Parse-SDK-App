package com.colinknecht.contactsudemycourse;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewContact extends AppCompatActivity {
    EditText etFirstName, etLastName, etPhoneNumber;
    Button btnAddNewContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Contact");

        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);

        btnAddNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
