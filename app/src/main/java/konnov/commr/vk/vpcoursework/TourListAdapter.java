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

/**
 * Created by ilya on 24/01/2018.
 */

public class TourListAdapter extends BaseAdapter {
    private Context context;
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
        ImageView pic = rowMain.findViewById(R.id.imageView);
        pic.setImageBitmap(MainActivity.bitmap);
        return rowMain;
    }
}
