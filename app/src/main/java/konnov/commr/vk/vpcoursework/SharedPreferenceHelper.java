package konnov.commr.vk.vpcoursework;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by ilya on 26/01/2018.
 */

public class SharedPreferenceHelper {
    enum cardKeys{desctiption, notes, websites, passport, ticket};

    private final String DESCRIPTION = "description";
    private final String NOTES = "notes";
    private final String WEBSITES = "websites";
    private final String PASSPORT = "passport";
    private final String TICKET = "ticket";


    public final String DEFAULT_VALUE = "";
    private Activity activity;

    public SharedPreferenceHelper(Activity activity){
        this.activity = activity;
        String PREFERENCE_FILE_KEY = "travel_card";
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }


    public void saveInSharedPref(String description, String notes, String websites, String passport, String ticket){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(DESCRIPTION, description);
        editor.putString(NOTES, notes);
        editor.putString(WEBSITES, websites);
        editor.putString(PASSPORT, passport);
        editor.putString(TICKET, ticket);
        editor.commit();
    }

    public ArrayList<String> getSharedPref(){
        ArrayList<String> arrayList = new ArrayList<>();
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        arrayList.add(sharedPref.getString(DESCRIPTION, DEFAULT_VALUE));
        arrayList.add(sharedPref.getString(NOTES, DEFAULT_VALUE));
        arrayList.add(sharedPref.getString(WEBSITES, DEFAULT_VALUE));
        arrayList.add(sharedPref.getString(PASSPORT, DEFAULT_VALUE));
        arrayList.add(sharedPref.getString(TICKET, DEFAULT_VALUE));

        if(!arrayList.get(0).equals(DEFAULT_VALUE) && !arrayList.get(1).equals(DEFAULT_VALUE) && !arrayList.get(2).equals(DEFAULT_VALUE) &&
                !arrayList.get(3).equals(DEFAULT_VALUE) && !arrayList.get(4).equals(DEFAULT_VALUE))
            return arrayList;
        else
            return null;
    }
}

