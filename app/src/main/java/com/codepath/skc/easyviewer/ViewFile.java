package com.codepath.skc.easyviewer;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ViewFile")

public class ViewFile extends ParseObject {


    public static final String KEY_FILENAME="filename";
    public static final String KEY_FILECREATED="filecreated";
    public static final String KEY_FILEDESCRIPTION="Description";


    public String getFilename() {
        return  getString(KEY_FILENAME);
    }

    public void setFilename(String filename) {
        put(KEY_FILENAME,filename);
    }

    public String getFilecreated() {
        return getString(KEY_FILECREATED);
    }

    public void setFilecreated(String filecreated) {
        put(KEY_FILECREATED,filecreated);
    }

    public String getDescription() {
        return getString(KEY_FILEDESCRIPTION);
    }

    public void setDescription(String description)
    {
       put(KEY_FILEDESCRIPTION,description);
    }

}
