package com.colinknecht.contactsudemycourse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by colinknecht on 6/30/17.
 */

public class ContactListArrayAdapter extends ArrayAdapter<ParseObject> {
    private static final String TAG = "ContactListArrayAdapter";

    private final Context CONTEXT;
    private final ArrayList<ParseObject> VALUES;

    public ContactListArrayAdapter(Context context, ArrayList<ParseObject> list){
        //context passed in will be the class using this Java class(ContactListArrayAdapter)
        //the list will be all the ParseObjects that you put into the list

        super(context, R.layout.contact_row_layout, list);//super calls the ArrayAdapter constructor... needs the context and the layout file
        this.CONTEXT = context;
        this.VALUES = list;
    }

    @NonNull
    @Override //for every item in the list..you need to run this method..thats why position is passed through
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //in this method you inflate the layout(contact_row_layout) for every entry in the database

        LayoutInflater inflater = (LayoutInflater) CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.contact_row_layout, parent, false);//pass in the xml file you want to inflate..rowView has a connection to layout file

        //now connect the textviews in the contact_row_layout file
        TextView tvName = (TextView) rowView.findViewById(R.id.tv_full_name);
        TextView tvPhoneNumber = (TextView) rowView.findViewById(R.id.tv_phone_number);

        //now set values of the textviews
        tvName.setText(VALUES.get(position).getString("firstName") + " " + VALUES.get(position).getString("lastName"));//this call grabs the info from the database
        tvPhoneNumber.setText(VALUES.get(position).getString("phoneNumber"));

        return rowView;//return rowView defined in this method
    }
}
