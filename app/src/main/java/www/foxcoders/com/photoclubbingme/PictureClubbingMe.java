package www.foxcoders.com.photoclubbingme;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
