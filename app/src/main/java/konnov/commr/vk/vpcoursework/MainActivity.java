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
//        DBHelper dbHelper = new DBHelper(this);
//        ArrayList<ArrayList<String>> arrayLists = dbHelper.dbToList();
        //getting image
//        String response = arrayLists.get(0).get(3);
//        String [] byteValues = response.substring(1, response.length() - 1).split(",");
//        byte[] bytes = new byte[byteValues.length];
//        for (int i=0, len=bytes.length; i<len; i++) {
//            bytes[i] = Byte.parseByte(byteValues[i].trim());
//        }
//        bitmap = Extras.ByteArrayToBitmap(bytes);

        //System.out.println(arrayLists);


        //saving image
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spainpic);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        dbHelper.insertData("тестовый тур", "Испания", "Новосибирск - Барселона", byteArray, "штаны и зубная паста", "30  дней", "29 градусов", "spain.com");

    }


    public void showTask(View view) {
        startActivity(new Intent(this, TaskActivity.class)); //displaying the problem
    }

    public void showDataBase(View view) {
        startActivity(new Intent(this, TourListActivity.class)); //displaying the list of tours

    }
}




//TODO String[] byteValues = response.substring(1, response.length() - 1).split(",");     byte[] bytes = new byte[byteValues.length];