package www.foxcoders.com.photoclubbingme;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.ArrayList;

import www.foxcoders.com.photoclubbingme.adapter.item.ThumbnailImageItem;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumbnailItem;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumnailImageGroupTitle;

/**
 * Created by Ray on 9/17/2017.
 */

public class Util {

    public static void setTitleText(String title, Activity context)
    {
//        TextView textView= (TextView) context.findViewById(R.id.toolbar_title);
//        textView.setText(title);

    }

    public static ArrayList<ThumbnailItem> getDummyThumbnailImagesWithGroupings() {
        ArrayList<ThumbnailItem> imgItems = new ArrayList<>();
        imgItems.add(new ThumnailImageGroupTitle("December"));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_1));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_3));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_4));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_5));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_6));
        imgItems.add(new ThumnailImageGroupTitle("November"));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_1));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_3));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_4));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_5));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_6));
        imgItems.add(new ThumnailImageGroupTitle("October"));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_1));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_3));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_4));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_5));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_6));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        return imgItems;
    }

    public static ArrayList<ThumbnailItem> getDummyThumbnailImages() {
        ArrayList<ThumbnailItem> imgItems = new ArrayList<>();
        imgItems.add(new ThumbnailImageItem(R.drawable.img_1));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_3));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_4));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_5));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_6));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_1));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_3));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_4));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_5));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_6));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_1));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_3));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_4));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_5));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_6));
        imgItems.add(new ThumbnailImageItem(R.drawable.img_2));
        return imgItems;
    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
