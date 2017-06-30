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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class NewContact extends AppCompatActivity {
    private static final String TAG = "NewContact";
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
        btnAddNewContact = (Button) findViewById(R.id.btn_add_new_contact);

        btnAddNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFirstName.getText().toString() == null || etFirstName.getText().toString().equals("") ||
                        etLastName.getText().toString() == null || etLastName.getText().toString().equals("") ||
                        etPhoneNumber.getText().toString() == null || etPhoneNumber.getText().toString().equals("")) {
                    Toast.makeText(NewContact.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ProgressDialog progressDialog = new ProgressDialog(NewContact.this);
                    progressDialog.setMessage("Busy Creating New Contact...Please Wait");
                    progressDialog.show();

                    ParseObject newUser = new ParseObject("Contacts");//A parseObject is a new row in the contacts database
                    newUser.put("firstName", etFirstName.getText().toString());//put the etFirstname into the firstname field in Parse
                    newUser.put("lastName", etLastName.getText().toString());
                    newUser.put("phoneNumber", etPhoneNumber.getText().toString());
                    newUser.saveInBackground(new SaveCallback() {//this saves the records to the database
                        @Override
                        public void done(ParseException e) {//when done saving; it will go to this method
                            if (e == null) {//no errors

                                Toast.makeText(NewContact.this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                NewContact.this.finish();
                                Intent intent = new Intent(NewContact.this,ContactList.class);
                                startActivity(intent);
                            }
                            else {//errors
                                Toast.makeText(NewContact.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Log.e(TAG, "done: " + e.getMessage() );

                                NewContact.this.finish();
                                Intent intent = new Intent(NewContact.this, ContactList.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }


}
