package com.colinknecht.contactsudemycourse;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Contact Details");


        tvName = (TextView) findViewById(R.id.tv_name_edit_contact);
        tvPhone = (TextView) findViewById(R.id.tv_phone_edit_contact);
        btnEditContact = (Button) findViewById(R.id.btn_edit_contact);
        etFirstName = (EditText) findViewById(R.id.et_firstname_edit_contact);
        etLastName = (EditText) findViewById(R.id.et_lastname_edit_contact);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_edit_contact);
        ivCall = (ImageView) findViewById(R.id.iv_call);

        etFirstName.setVisibility(View.GONE);///hides a field..when the activity start u will not see these items
        etLastName.setVisibility(View.GONE);
        etPhoneNumber.setVisibility(View.GONE);
        btnEditContact.setVisibility(View.GONE);

        //remember you passed values from the ContactList Intent... below we grab these values
        tvName.setText(getIntent().getStringExtra("firstName") + " " + getIntent().getStringExtra("lastName"));
        tvPhone.setText(getIntent().getStringExtra("phoneNumber"));

        btnEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //what happens when u want to call the person
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getIntent().getStringExtra("phoneNumber") ));//intent is from ContactList
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu.. this adds items to the action bar if present
        getMenuInflater().inflate(R.menu.menu_edit_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle action bar item clicks here..the action bar will automatically handle
        // clicks on the home/up button, so long as you specify a parent activity in the AndroidManifest.xml
        int id = item.getItemId();

        switch (id){
            case R.id.action_edit: {
                //when user clicks on edit....show the button and edit texts
                etFirstName.setVisibility(View.VISIBLE);
                etLastName.setVisibility(View.VISIBLE);
                etPhoneNumber.setVisibility(View.VISIBLE);
                btnEditContact.setVisibility(View.VISIBLE);


                //now populate the edittexts with Contact's info so we can change information
                //remember Intent was passed from contact list
                etFirstName.setText(getIntent().getStringExtra("firstName"));
                etLastName.setText(getIntent().getStringExtra("lastName"));
                etPhoneNumber.setText(getIntent().getStringExtra("phoneNumber"));
                return true;
            }
            case R.id.action_delete: {
                return true;
            }
            default: {
                return onOptionsItemSelected(item);
            }
        }//switch

    }
}//end class
