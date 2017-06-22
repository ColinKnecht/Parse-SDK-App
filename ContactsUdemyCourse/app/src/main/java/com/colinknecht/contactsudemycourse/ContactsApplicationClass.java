package com.colinknecht.contactsudemycourse;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by colinknecht on 6/22/17.
 */

public class ContactsApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("2xJIj9mGOJlv8em2uivchfYhzLpV5YTMUFLyHpiN")
                .clientKey("2VC0jyhnC4Wh7CewNF8dJtCEOPFBMdqPdjNdlnfT")
                .server("https://parseapi.back4app.com/").build()
        );
    }
}
