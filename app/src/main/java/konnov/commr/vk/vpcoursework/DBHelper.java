package konnov.commr.vk.vpcoursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ilya on 24/01/2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "toursdb";
    private static final String TABLE_TOURS = "tourstable";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "_id";

    private static final String TOURS_COLUMN_NAME = "name";
    private static final String TOURS_COLUMN_COUNTRY = "country";
    private static final String TOURS_COLUMN_ROUTE = "route";
    private static final String TOURS_COLUMN_PHOTO = "photo";
    private static final String TOURS_COLUMN_BACKPACK = "backpack";
    private static final String TOURS_COLUMN_DURATION = "duration";
    private static final String TOURS_COLUMN_WEATHER = "weather";
    private static final String TOURS_COLUMN_WEBSITE = "website";

//    private static final String TABLE_CARD = "cardstable";
//    private static final String CARD_COLUMN_DESCRIPTION = "description";
//    private static final String CARD_COLUMN_TICKET = "ticket";
//    private static final String CARD_COLUMN_PASSPORT = "passport";





    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TOURS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TOURS_COLUMN_NAME + " VARCHAR, " + TOURS_COLUMN_COUNTRY + " VARCHAR, " + TOURS_COLUMN_ROUTE + " VARCHAR, " +
                TOURS_COLUMN_PHOTO + " VARCHAR, " + TOURS_COLUMN_BACKPACK + " VARCHAR, " + TOURS_COLUMN_DURATION + " VARCHAR, " + TOURS_COLUMN_WEATHER + " VARCHAR, " +
                TOURS_COLUMN_WEBSITE + " VARCHAR" + ");") ;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TOURS);
        onCreate(sqLiteDatabase);
    }



    public void insertDataIntoToursTable(String nameString, String countryString, String routeString, String photo, String backpackString, String durationString, String weatherString, String websiteString){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOURS_COLUMN_NAME, nameString);
        contentValues.put(TOURS_COLUMN_COUNTRY, countryString);
        contentValues.put(TOURS_COLUMN_ROUTE, routeString);
        contentValues.put(TOURS_COLUMN_PHOTO, photo); /// saving a picture
        contentValues.put(TOURS_COLUMN_BACKPACK, backpackString);
        contentValues.put(TOURS_COLUMN_DURATION, durationString);
        contentValues.put(TOURS_COLUMN_WEATHER, weatherString);
        contentValues.put(TOURS_COLUMN_WEBSITE, websiteString);
        db.insert(TABLE_TOURS, null, contentValues);
        db.close();
    }



    public ArrayList<ArrayList<String>> toursTableToList(){
        ArrayList<ArrayList<String>> outerArrayList = new ArrayList<ArrayList<String>>();
        String dbString;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TOURS + " WHERE 1";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            ArrayList<String> innerArrayList = new ArrayList<String>();
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_NAME)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_COUNTRY)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_ROUTE)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_PHOTO)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_BACKPACK)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_DURATION)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_WEATHER)));
            innerArrayList.add(c.getString(c.getColumnIndex(TOURS_COLUMN_WEBSITE)));
            outerArrayList.add(innerArrayList);
            c.moveToNext();
        }
        sqLiteDatabase.close();
        c.close();
        return outerArrayList;
    }



    public void editTour(String nameString, String countryString, String routeString, String photo, String backpackString, String durationString, String weatherString, String websiteString){
        SQLiteDatabase db = getWritableDatabase();
        //db.rawQuery("SELECT * FROM " + TABLE_TOURS + " WHERE " + TOURS_COLUMN_NAME + " = '" + previousName + "' AND " + TOURS_COLUMN_COUNTRY + " = '" + previousCountry + "'", null);
//        db.execSQL(" UPDATE " + TABLE_TOURS + " SET " + TOURS_COLUMN_NAME + " = '" + nameString + "', " + TOURS_COLUMN_COUNTRY + " = '" + countryString +
//                "', " + TOURS_COLUMN_ROUTE + " = '" + routeString + "', " + TOURS_COLUMN_PHOTO + " = '" + photo + "', " + TOURS_COLUMN_BACKPACK + " = '" +
//                backpackString + "', " + TOURS_COLUMN_DURATION + " = '" + durationString + "', " + TOURS_COLUMN_WEATHER + " = '" + weatherString +
//                "', " + TOURS_COLUMN_WEBSITE + " = '" + websiteString + "' WHERE " + TOURS_COLUMN_NAME + " = '" + nameString + "' AND " + TOURS_COLUMN_COUNTRY + " = '" + countryString + "'");
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOURS_COLUMN_ROUTE, routeString);
        contentValues.put(TOURS_COLUMN_PHOTO, photo); /// saving a picture
        contentValues.put(TOURS_COLUMN_BACKPACK, backpackString);
        contentValues.put(TOURS_COLUMN_DURATION, durationString);
        contentValues.put(TOURS_COLUMN_WEATHER, weatherString);
        contentValues.put(TOURS_COLUMN_WEBSITE, websiteString);
        db.update(TABLE_TOURS, contentValues, TOURS_COLUMN_NAME + " = '" + nameString + "' AND " + TOURS_COLUMN_COUNTRY + " = '" + countryString + "'", null);
        db.close();
    }

    public ArrayList<String> getTourRow(String name, String country){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TOURS + " WHERE " + TOURS_COLUMN_NAME + " = '" + name + "' AND " + TOURS_COLUMN_COUNTRY + " = '" + country + "'";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_NAME)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_COUNTRY)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_ROUTE)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_PHOTO)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_BACKPACK)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_DURATION)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_WEATHER)));
        list.add(c.getString(c.getColumnIndex(TOURS_COLUMN_WEBSITE)));
        c.close();
        return list;
    }


    public void deleteItemFromToursTable(String name, String country){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_TOURS, TOURS_COLUMN_NAME + " = '" + name + "' AND " + TOURS_COLUMN_COUNTRY + " = '" + country + "'", null);
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOURS);
        onCreate(db);
    }

    public int numberOfRowsInToursTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_TOURS);
        db.close();
        return (int) count;
    }


}
