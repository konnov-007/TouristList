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
    int numberOfItems;

    public  TourListAdapter(Context context, int numberOfItems){
        this.context = context;
        this.numberOfItems = numberOfItems;
    }

    @Override
    public int getCount() {
        return numberOfItems;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View rowMain;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowMain = layoutInflater.inflate(R.layout.customrow, viewGroup, false);

            TextView nameTV = rowMain.findViewById(R.id.nameTextView);
            TextView countryTV = rowMain.findViewById(R.id.countryTextView);
            TextView routeTV = rowMain.findViewById(R.id.routeTextView);
            ImageView photo = rowMain.findViewById(R.id.imageView);
            TextView backpackTV = rowMain.findViewById(R.id.backpackTextView);
            TextView durationTV = rowMain.findViewById(R.id.durationTextView);
            TextView weatherTV = rowMain.findViewById(R.id.weatherTextView);
            TextView websiteTV = rowMain.findViewById(R.id.websiteTextView);

            ViewHolder viewHolder = new ViewHolder(nameTV, countryTV, routeTV, photo, backpackTV, durationTV, weatherTV, websiteTV);

            rowMain.setTag(viewHolder);
        }else {
            rowMain = convertView;
        }

        getTheList();
        ViewHolder viewHolder = (ViewHolder) rowMain.getTag();
        viewHolder.nameTV.setText(arrayLists.get(position).get(0));
        viewHolder.countryTV.setText(arrayLists.get(position).get(1));
        viewHolder.routeTV.setText("Маршрут: " + arrayLists.get(position).get(2));
        viewHolder.photo.setImageBitmap(bitmap[position]);
        viewHolder.backpackTV.setText("Взять в дорогу: " + arrayLists.get(position).get(4));
        viewHolder.durationTV.setText("Длительность: " + arrayLists.get(position).get(5));
        viewHolder.weatherTV.setText("Погода: " + arrayLists.get(position).get(6));
        viewHolder.websiteTV.setText("Сайт: " + arrayLists.get(position).get(7));
        return rowMain;
    }


    private class ViewHolder{
        TextView nameTV;
        TextView countryTV;
        TextView routeTV;
        ImageView photo;
        TextView backpackTV;
        TextView durationTV;
        TextView weatherTV;
        TextView websiteTV;
        private ViewHolder(TextView nameTV, TextView countryTV, TextView routeTV, ImageView photo, TextView backpackTV, TextView durationTV, TextView weatherTV, TextView websiteTV){
            this.nameTV = nameTV;
            this.countryTV = countryTV;
            this.routeTV = routeTV;
            this.photo = photo;
            this.backpackTV = backpackTV;
            this.durationTV = durationTV;
            this.weatherTV = weatherTV;
            this.websiteTV = websiteTV;
        }
    }

    private void getTheList(){
        DBHelper dbHelper = new DBHelper(context);
        arrayLists = dbHelper.dbToList();
        bitmap = new Bitmap[arrayLists.size()];
        for(int i = 0; i < arrayLists.size(); i++){
            bitmap[i] = new ImageSaver(context).
                setFileName(arrayLists.get(i).get(3)).
                setDirectoryName("images").
                load();
        }
    }
}
