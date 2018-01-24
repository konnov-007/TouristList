package konnov.commr.vk.vpcoursework;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ilya on 24/01/2018.
 */

public class TourListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArrayList<String>> arrayLists;
    private Bitmap[] bitmap;

    public  TourListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rowMain = layoutInflater.inflate(R.layout.customrow, viewGroup, false);
        TextView nameTV = rowMain.findViewById(R.id.nameTextView);
        TextView countryTV = rowMain.findViewById(R.id.countryTextView);
        TextView routeTV = rowMain.findViewById(R.id.routeTextView);
        ImageView photo = rowMain.findViewById(R.id.imageView);
        TextView backpackTV = rowMain.findViewById(R.id.backpackTextView);
        TextView durationTV = rowMain.findViewById(R.id.durationTextView);
        TextView weatherTV = rowMain.findViewById(R.id.weatherTextView);
        TextView websiteTV = rowMain.findViewById(R.id.websiteTextView);
        getTheList();

        nameTV.setText(arrayLists.get(0).get(0));
        countryTV.setText(arrayLists.get(0).get(1));
        routeTV.append(arrayLists.get(0).get(2));
        photo.setImageBitmap(bitmap[0]);
        backpackTV.append(arrayLists.get(0).get(4));
        durationTV.append(arrayLists.get(0).get(5));
        weatherTV.append(arrayLists.get(0).get(6));
        websiteTV.append(arrayLists.get(0).get(7));
        return rowMain;
    }

    private void getTheList(){
        DBHelper dbHelper = new DBHelper(context);
        arrayLists = dbHelper.dbToList();
        bitmap = new Bitmap[arrayLists.size()];
        for(int i = 0; i < arrayLists.size(); i++){
            String response = arrayLists.get(i).get(3);
            String [] byteValues = response.substring(1, response.length() - 1).split(",");
            byte[] bytes = new byte[byteValues.length];
            for (int j=0, len=bytes.length; j<len; j++) {
                bytes[j] = Byte.parseByte(byteValues[j].trim());
            }
            bitmap[i] = Extras.ByteArrayToBitmap(bytes);
        }




    }
}
