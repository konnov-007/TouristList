package konnov.commr.vk.vpcoursework;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class ChangeTourActivity extends AppCompatActivity {
    TextView nameTV;
    TextView countryTV;
    EditText routeET;
    ImageView photo;
    EditText backpackET;
    EditText durationET;
    EditText weatherET;
    EditText websiteET;
    Bitmap bitmap;
    String name;
    String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tour);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        country = intent.getStringExtra("country");
        DBHelper dbHelper = new DBHelper(this);
        nameTV = findViewById(R.id.nameTextViewForChangeTour);
        countryTV = findViewById(R.id.countryTextViewForChangeTour);
        routeET = findViewById(R.id.routeEditTextForChangeTour);
        photo = findViewById(R.id.imageViewForChangeTour);
        backpackET = findViewById(R.id.backpackEditTextForChangeTour);
        durationET = findViewById(R.id.durationEditTextForChangeTour);
        weatherET = findViewById(R.id.weatherEditTextForChangeTour);
        websiteET = findViewById(R.id.websiteEditTextForChangeTour);

        ArrayList<String> list = dbHelper.getListForRow(name, country);
        nameTV.setText(list.get(0));
        countryTV.setText(list.get(1));
        routeET.setText("Маршрут: " + list.get(2));
        bitmap = new ImageSaver(this).
                setFileName(list.get(3)).
                setDirectoryName("images").
                load();
        photo.setImageBitmap(bitmap);
        backpackET.setText("Взять в дорогу: " + list.get(4));
        durationET.setText("Длительность: " + list.get(5));
        weatherET.setText("Погода: " + list.get(6));
        websiteET.setText("Сайт: " + list.get(7));
    }

    public void changeTour(View view) {
        if(routeET.getText().toString().isEmpty() ||
                /*not working*/photo.getDrawable() == getResources().getDrawable(R.drawable.addpicture) || backpackET.getText().toString().isEmpty() || durationET.getText().toString().isEmpty() ||
                weatherET.getText().toString().isEmpty() || websiteET.getText().toString().isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }else {
            DBHelper dbHelper = new DBHelper(this);
            Random random = new Random();
            String fileName = "image" + random.nextInt() + ".png";
            new ImageSaver(this).
                    setFileName(fileName).
                    setDirectoryName("images").
                    save(bitmap);
            dbHelper.editTour(name, country, routeET.getText().toString(),
                    fileName, backpackET.getText().toString(), durationET.getText().toString(), weatherET.getText().toString(), websiteET.getText().toString());
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
        }
    }



    public void changePicture(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else
                getPhoto();
        }else
            getPhoto();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getPhoto();
            }
        }
    }

    private void getPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                photo.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(ChangeTourActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }


}
