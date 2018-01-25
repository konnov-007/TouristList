package konnov.commr.vk.vpcoursework;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class TravelCard extends AppCompatActivity {

    Bitmap passportPic;
    Bitmap ticketPic;
    ImageView passportIV;
    ImageView ticketIV;
    EditText descriptionET;
    EditText notesET;
    EditText websitesET;
    int imageState = 0; //1 will be for passport, 2 is for ticket, 0 is nothing
    final private String PASSPORT_IMG_NAME = "passportpic.png";
    final private String TICKET_IMG_NAME = "ticketpic.png";
    final private String DIRECTORY_NAME = "tourcardimg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_card);
        passportIV = findViewById(R.id.passportImageView);
        ticketIV = findViewById(R.id.ticketImageView);
        descriptionET = findViewById(R.id.descriptionEditText);
        notesET = findViewById(R.id.notesEditText);
        websitesET = findViewById(R.id.websitesEditText);
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);
        ArrayList<String> theList = sharedPreferenceHelper.getSharedPref();
        if(theList!= null){
            descriptionET.setText(theList.get(0));
            notesET.setText(theList.get(1));
            websitesET.setText(theList.get(2));
            passportPic = new ImageSaver(this).
                    setFileName(theList.get(3)).
                    setDirectoryName(DIRECTORY_NAME).
                    load();
            ticketPic = new ImageSaver(this).
                    setFileName(theList.get(4)).
                    setDirectoryName(DIRECTORY_NAME).
                    load();
            passportIV.setImageBitmap(passportPic);
            ticketIV.setImageBitmap(ticketPic);
            if(passportPic == null)
                passportIV.setImageDrawable(getResources().getDrawable(R.drawable.addpicture));
            if(ticketPic == null)
                ticketIV.setImageDrawable(getResources().getDrawable(R.drawable.addpicture));
        }
        
    }

    public void saveChanges(View view) {
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);

        new ImageSaver(this).
                setFileName(PASSPORT_IMG_NAME).
                setDirectoryName(DIRECTORY_NAME).
                save(passportPic);
        new ImageSaver(this).
                setFileName(TICKET_IMG_NAME).
                setDirectoryName(DIRECTORY_NAME).
                save(ticketPic);
        sharedPreferenceHelper.saveInSharedPref(descriptionET.getText().toString(), notesET.getText().toString(), websitesET.getText().toString(), PASSPORT_IMG_NAME, TICKET_IMG_NAME);
    }



    public void uploadPassport(View view) {
        imageState = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else
                getPhoto();
        }else
            getPhoto();
    }

    public void uploadTicket(View view) {
        imageState = 2;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else
                getPhoto();
        }else
            getPhoto();
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
                if(imageState == 1) {
                    passportPic = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    passportPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    passportIV.setImageBitmap(passportPic);
                    imageState = 0;
                }
                if(imageState == 2){
                    ticketPic = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    ticketPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    ticketIV.setImageBitmap(ticketPic);
                }
            } catch (Exception e) {
                Toast.makeText(TravelCard.this, e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }


}
