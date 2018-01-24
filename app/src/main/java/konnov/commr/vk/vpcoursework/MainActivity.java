package konnov.commr.vk.vpcoursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
        //dbHelper.insertData("тестовый тур", "Испания", "Новосибирск - Барселона", null, "штаны и зубная паста", "30  дней", "29 градусов", "spain.com");
//        ArrayList<ArrayList<String>> arrayLists = dbHelper.dbToList();
//        System.out.println(arrayLists);
    }


    public void showTask(View view) {
        startActivity(new Intent(this, TaskActivity.class)); //displaying the problem
    }

    public void showDataBase(View view) {
    }
}




//TODO String[] byteValues = response.substring(1, response.length() - 1).split(",");     byte[] bytes = new byte[byteValues.length];