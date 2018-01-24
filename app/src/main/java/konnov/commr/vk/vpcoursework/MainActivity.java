package konnov.commr.vk.vpcoursework;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //saving image
//        DBHelper dbHelper = new DBHelper(this);
//        dbHelper.deleteDB();
//        dbHelper.insertData("Средиземное блаженство", "Испания", "Новосибирск - Барселона", Extras.drawableToBytes(this, R.drawable.spainpic), "штаны и зубная паста", "30  дней", "29 градусов", "spain.com");
//        dbHelper.insertData("Пицца, паста, Ватикан", "Италия", "Новосибирск - Москва - Рим", Extras.drawableToBytes(this, R.drawable.italypic), "кетчуп хайнц, ламборгини и футболка D&G", "2  недели", "25 градусов", "experienceitaly.com");
//        dbHelper.insertData("Эта СПАРТА", "Греция", "Новосибирск - Москва - Афины", Extras.drawableToBytes(this, R.drawable.greecepic), "Копье для защиты спарты", "5 дней", "32 градуса", "thisissparta.com");


    }


    public void showTask(View view) {
        startActivity(new Intent(this, TaskActivity.class)); //displaying the problem
    }

    public void showDataBase(View view) {
        startActivity(new Intent(this, TourListActivity.class)); //displaying the list of tours

    }
}




//TODO String[] byteValues = response.substring(1, response.length() - 1).split(",");     byte[] bytes = new byte[byteValues.length];