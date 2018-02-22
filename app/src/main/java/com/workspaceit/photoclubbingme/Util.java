package com.workspaceit.photoclubbingme;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.workspaceit.photoclubbingme.adapter.item.ThumbnailImageItem;

import java.util.ArrayList;

import com.workspaceit.photoclubbingme.R;

import com.workspaceit.photoclubbingme.adapter.item.ThumbnailItem;
import com.workspaceit.photoclubbingme.adapter.item.ThumnailImageGroupTitle;

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
