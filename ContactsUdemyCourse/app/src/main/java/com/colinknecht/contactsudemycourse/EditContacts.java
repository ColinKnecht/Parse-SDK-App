package com.colinknecht.contactsudemycourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditContacts extends AppCompatActivity {
    private static final String TAG = "EditContacts";

    TextView tvName, tvPhone;
    Button btnEditContact;
    EditText etFirstName, etLastName, etPhoneNumber;
    ImageView ivCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        tvName = (TextView) findViewById(R.id.tv_name_edit_contact);
        tvPhone = (TextView) findViewById(R.id.tv_phone_edit_contact);
        btnEditContact = (Button) findViewById(R.id.btn_edit_contact);
        etFirstName = (EditText) findViewById(R.id.et_firstname_edit_contact);
        etLastName = (EditText) findViewById(R.id.et_lastname_edit_contact);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_edit_contact);
        ivCall = (ImageView) findViewById(R.id.iv_call);

        btnEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
