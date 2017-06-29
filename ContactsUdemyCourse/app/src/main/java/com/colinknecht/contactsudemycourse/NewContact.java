package com.colinknecht.contactsudemycourse;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Contact");
    }
}
