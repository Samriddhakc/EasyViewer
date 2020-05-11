package com.codepath.skc.easyviewer;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        ParseObject.registerSubclass(ViewFile.class);
        //ParseObject.registerSubclass(Assignment.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("samriddhakc-easystorage") // should correspond to APP_ID env variable
                .clientKey("EasyStorageMoveFastParse")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://samriddhakc-easystorage.herokuapp.com/parse/").build());
        //Test to check if the parse dashboard is working, and we can control
        //the parse application from the database.
        ParseObject testObject = new ParseObject("U");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

}
