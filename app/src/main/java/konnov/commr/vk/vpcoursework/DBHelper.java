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

    private static final String DB_NAME = "notesDB";
    private static final String TABLE_NAME = "notestable";
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



//    public void changeNote(int id, String note){
//        id++; //incrementing it since database id starts with 1, not with 0
//        note = "\'"+note+"\'";
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_NOTE + " = " + note + " WHERE " + COLUMN_ID + " = " + id);
////        Cursor cursor = db.rawQuery(query, null);
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(COLUMN_NOTE, note);
////        db.update(TABLE_NAME, contentValues, COLUMN_ID, new String[] {String.valueOf(id)});
//
//        //       cursor.close();
//    }


//    public void deleteItemFromDB(int index){
//        SQLiteDatabase database = getWritableDatabase();
//        index++;
//        String stringId = String.valueOf(index);
//        database.delete(TABLE_NAME, COLUMN_ID + "=" + stringId, null);
//
//        long lastID = DatabaseUtils.longForQuery(database, "SELECT MAX(_id) FROM " + TABLE_NAME, null);
//        if (lastID > index)
//            database.execSQL("UPDATE " + TABLE_NAME + " SET _id = " + index + " WHERE _id = " + lastID);
//        if(lastID == 0)
//            deleteDB();
//
//    }

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
