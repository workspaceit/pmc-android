package www.foxcoders.com.photoclubbingme.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.activity.fragments.SlideShowDetails;
import www.foxcoders.com.photoclubbingme.activity.fragments.WebVideoFragment;
import www.foxcoders.com.photoclubbingme.adapter.ThumbnailAdapter;

/**
 * Created by fadedreamz on 1/22/18.
 */

public class MainSlideshowActivity extends AppCompatActivity implements ThumbnailAdapter.ItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow_details);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/RobotoCondensed-Bold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, new SlideShowDetails(), null)
                .commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onThumbnailClick(View view, int position) {
        Bundle b = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //b = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(),
            //                                         view.getHeight()).toBundle();
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(Color.parseColor("#211f49"));
            b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 5).toBundle();
        }
        startActivity(new Intent(this, FolderActivity.class), b);
    }

    public void onSlideshowClick(View view) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_holder, new WebVideoFragment(), null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onThumbnailLongClick(View view, int position) {

    }
}
