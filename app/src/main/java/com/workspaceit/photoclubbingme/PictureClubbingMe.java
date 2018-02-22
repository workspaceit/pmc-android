package com.workspaceit.photoclubbingme;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import com.workspaceit.photoclubbingme.R;

/**
 * Created by wsit on 9/25/17.
 */

public class PictureClubbingMe extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/RobotoCondensed-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
