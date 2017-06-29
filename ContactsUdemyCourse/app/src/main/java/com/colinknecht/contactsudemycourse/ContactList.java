package com.colinknecht.contactsudemycourse;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

public class ContactList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Contacts");
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
