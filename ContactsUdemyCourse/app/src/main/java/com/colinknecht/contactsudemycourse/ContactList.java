package com.colinknecht.contactsudemycourse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {
    private static final String TAG = "ContactList";
    ListView lvContacts;
    ArrayList<ParseObject> contacts;
    ContactListArrayAdapter adapter; //an instance of the created ContactListArrayAdapter java class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        lvContacts = (ListView) findViewById(R.id.lv_contacts);//connects java listview object to activity_contact_list.xml listview
        contacts = new ArrayList<ParseObject>();//initialize ArrayAdapter

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Contacts");
    }

    private void populateArrayList() { //places values from database as parseobjects inside of contacts arraylist
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Contacts");//retrieves data from Contacts class in the Parse db
        query.whereEqualTo("userName", ParseUser.getCurrentUser().getUsername());//gets the users contacts in the database
        query.addAscendingOrder("firstName");//sorts the data in alphabetical order--use "lastName" to sort by lastname

        final ProgressDialog progressDialog = new ProgressDialog(ContactList.this);
        progressDialog.setMessage("Busy Loading all contacts");
        progressDialog.show();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) { //the list in this line is all the ParseObjects the database found
                if (e == null) {
                    //list could have 0 values
                    if (objects.size() == 0) { //if theres nothing in the list
                        Toast.makeText(ContactList.this, "No Contacts Found in the Database", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                    else { //contacts in database found
                        for (int i = 0; i < objects.size(); i++){//run through the list
                            contacts.add(objects.get(i));//add the object to the contacts arraylist
                        }
                        adapter = new ContactListArrayAdapter(ContactList.this, contacts);//connect the adapter to this class, while adding in contacts parseObjects
                        lvContacts.setAdapter(adapter);//connect the listview to the adapter
                        progressDialog.dismiss();
                    }
                }
                else {//errors
                    Toast.makeText(ContactList.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "populateArrayList-->findInBackground-->done: " + e.getMessage() );
                    progressDialog.dismiss();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_contact_list,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar clicks here. The action bar will
        //automatically handle clicks on the home/up button, so long
        //as you specify a parent activity in the AndroidManifest.xml

        int id = item.getItemId();

        switch (id) {
            case R.id.add_contact: {
                //when menu item add contact is clicked its gonna open up add contact class
                Intent intent = new Intent(this, NewContact.class);
                startActivity(intent);
                return true;
            }
            case R.id.logout: {
                //if user logs out take them to login screen
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);

                ParseUser.getCurrentUser().logOut();

                ContactList.this.finish();

                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
