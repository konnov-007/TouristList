package konnov.commr.vk.vpcoursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ilya on 24/01/2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "toursdb";
    private static final String TABLE_NAME = "tourstable";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_ROUTE = "route";
    private static final String COLUMN_PHOTO = "photo";
    private static final String COLUMN_BACKPACK = "backpack";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_WEATHER = "weather";
    private static final String COLUMN_WEBSITE = "website";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " VARCHAR, " + COLUMN_COUNTRY + " VARCHAR, " + COLUMN_ROUTE + " VARCHAR, " +
                COLUMN_PHOTO + " VARCHAR, " + COLUMN_BACKPACK + " VARCHAR, " + COLUMN_DURATION + " VARCHAR, " + COLUMN_WEATHER + " VARCHAR, " +
                        COLUMN_WEBSITE + " VARCHAR" + ");") ;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }



    public void insertData(String nameString, String countryString, String routeString, String photo, String backpackString, String durationString, String weatherString, String websiteString){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, nameString);
        contentValues.put(COLUMN_COUNTRY, countryString);
        contentValues.put(COLUMN_ROUTE, routeString);
        contentValues.put(COLUMN_PHOTO, photo); /// saving a picture
        contentValues.put(COLUMN_BACKPACK, backpackString);
        contentValues.put(COLUMN_DURATION, durationString);
        contentValues.put(COLUMN_WEATHER, weatherString);
        contentValues.put(COLUMN_WEBSITE, websiteString);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }



    public ArrayList<ArrayList<String>> dbToList(){
        ArrayList<ArrayList<String>> outerArrayList = new ArrayList<ArrayList<String>>();
        String dbString;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            ArrayList<String> innerArrayList = new ArrayList<String>();
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_NAME)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_COUNTRY)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_ROUTE)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_PHOTO)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_BACKPACK)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_DURATION)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_WEATHER)));
            innerArrayList.add(c.getString(c.getColumnIndex(COLUMN_WEBSITE)));
            outerArrayList.add(innerArrayList);
            c.moveToNext();
        }
        sqLiteDatabase.close();
        c.close();
        return outerArrayList;
    }



    public void editTour(String nameString, String countryString, String routeString, String photo, String backpackString, String durationString, String weatherString, String websiteString){
        SQLiteDatabase db = getWritableDatabase();
        //db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + previousName + "' AND " + COLUMN_COUNTRY + " = '" + previousCountry + "'", null);
//        db.execSQL(" UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = '" + nameString + "', " + COLUMN_COUNTRY + " = '" + countryString +
//                "', " + COLUMN_ROUTE + " = '" + routeString + "', " + COLUMN_PHOTO + " = '" + photo + "', " + COLUMN_BACKPACK + " = '" +
//                backpackString + "', " + COLUMN_DURATION + " = '" + durationString + "', " + COLUMN_WEATHER + " = '" + weatherString +
//                "', " + COLUMN_WEBSITE + " = '" + websiteString + "' WHERE " + COLUMN_NAME + " = '" + nameString + "' AND " + COLUMN_COUNTRY + " = '" + countryString + "'");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ROUTE, routeString);
        contentValues.put(COLUMN_PHOTO, photo); /// saving a picture
        contentValues.put(COLUMN_BACKPACK, backpackString);
        contentValues.put(COLUMN_DURATION, durationString);
        contentValues.put(COLUMN_WEATHER, weatherString);
        contentValues.put(COLUMN_WEBSITE, websiteString);
        db.update(TABLE_NAME, contentValues, COLUMN_NAME + " = '" + nameString + "' AND " + COLUMN_COUNTRY + " = '" + countryString + "'", null);
        db.close();
    }

    public ArrayList<String> getListForRow(String name, String country){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + name + "' AND " + COLUMN_COUNTRY + " = '" + country + "'";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        list.add(c.getString(c.getColumnIndex(COLUMN_NAME)));
        list.add(c.getString(c.getColumnIndex(COLUMN_COUNTRY)));
        list.add(c.getString(c.getColumnIndex(COLUMN_ROUTE)));
        list.add(c.getString(c.getColumnIndex(COLUMN_PHOTO)));
        list.add(c.getString(c.getColumnIndex(COLUMN_BACKPACK)));
        list.add(c.getString(c.getColumnIndex(COLUMN_DURATION)));
        list.add(c.getString(c.getColumnIndex(COLUMN_WEATHER)));
        list.add(c.getString(c.getColumnIndex(COLUMN_WEBSITE)));
        c.close();
        return list;
    }


    public void deleteItemFromDB(String name, String country){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, COLUMN_NAME + " = '" + name + "' AND " + COLUMN_COUNTRY + " = '" + country + "'", null);
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return (int) count;
    }


}
