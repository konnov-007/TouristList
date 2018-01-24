package konnov.commr.vk.vpcoursework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;

/**
 * Created by ilya on 24/01/2018.
 */

public class Extras {
    public static enum Elements{Name, Country, Route, Photo, Backpack, Duration, Weather, Website};


    public static Bitmap ByteArrayToBitmap(byte[] byteArray)
    {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
