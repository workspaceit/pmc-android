package com.workspaceit.photoclubbingme.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.workspaceit.photoclubbingme.Util;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.workspaceit.photoclubbingme.R;

/**
 * Created by wsit on 10/2/17.
 */

public class NewSliderActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener{
    private SliderLayout mDemoSlider;
    Toolbar toolbar;
    List<Integer> drawables = new ArrayList<>();
    private SharedPreferences sharedPref;
    private boolean isLoggedIn;
    ImageView imageView;
    CountDownTimer bgTimmer;
    RelativeLayout rootLayout;
    ImageView adsImageView;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        rootLayout = (RelativeLayout)findViewById(R.id.rootLayout);
        mDemoSlider = (SliderLayout)findViewById(R.id.slideShow);
        adsImageView = (ImageView)findViewById(R.id.adsImageView);
        toolbar = (Toolbar) findViewById(R.id.slideshowToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.timerOnRun();
        Util.setTitleText("", this);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "https://ubistatic19-a.akamaihd.net/ubicomstatic/en-gb/global/search-thumbnail/acm_ubi_thumb_leap_mobile_275895.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "https://www.geo.tv/assets/uploads/updates/2017-04-08/l_137299_020615_updates.jpg");
        url_maps.put("Dead Men Tell No Tales", "http://www.joblo.com/movie-posters/2017/pirates-of-the-caribbean-dead-men-tell-no-tales#image-33997");
        url_maps.put("John  Wick 2", "https://i.pinimg.com/736x/60/f9/78/60f978dd719a3fb333bf4185d50fed7c--movieposter-twitter.jpg");
        url_maps.put("Logan", "https://i.pinimg.com/736x/70/66/e4/7066e48072f558c983215b99c2fe20cd---movies-new-movies.jpg");
        url_maps.put("Popeye", "http://i0.kym-cdn.com/photos/images/facebook/001/014/016/482.jpg");
        url_maps.put("Life", "http://fr.web.img2.acsta.net/r_1280_720/pictures/17/02/15/09/25/520233.jpg");
        url_maps.put("Mummy", "https://i.pinimg.com/736x/57/b1/fc/57b1fc5bc5c0bb685086fedcc293076d.jpg");
        url_maps.put("Wonder Women", "https://cdn.traileraddict.com/content/warner-bros-pictures/wonder-woman-2017-5.jpg");
        url_maps.put("Dunkrik", "http://www.shockmansion.com/wp-content/myimages/2016/12/fy.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.jelly);
        file_maps.put("Big Bang Theory",R.drawable.music);
        file_maps.put("House of Cards",R.drawable.dance);
        file_maps.put("Game of Thrones", R.drawable.tree);
        file_maps.put("Game of Thrones", R.drawable.travle);

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        chooseTransform();
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        bgTimmer.cancel();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.stop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == R.id.play) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.stop);
            mDemoSlider.startAutoCycle();
            bgTimmer.start();
        }

        if (item.getItemId() == R.id.stop) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.play);
            mDemoSlider.stopAutoCycle();
            bgTimmer.cancel();

        }

        return true;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    public void timerOnRun(){
        bgTimmer = new CountDownTimer(10000, 5000) {
            String[] bgs= {
                    "https://i.ytimg.com/vi/wnBXGxJdO_E/maxresdefault.jpg",
                    "https://img00.deviantart.net/5525/i/2014/167/4/1/galaxy_bg_resource_by_furesiya-d7mn02t.png",
                    "http://www.mahamconsultants.com/wp-content/uploads/2014/04/bokeh-cover-bg.jpg"
            };
            String [] ads  = {
                    "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/6/005/0a7/2fa/30e6f26.jpg",
                    "http://jukson.com/assets/images/FRANK-BANNER-AD-SALE-SQUARE-FINAL.jpg",
                    "http://static.shareasale.com/image/36237/250x250_square_ad_002.jpg",
                    "http://www.raftransportcommandmemorial.com/wp-content/uploads/2017/05/banner-1.jpg",
            };
            public void onTick(long millisUntilFinished) {
                new AsyncTask<Void, Void, Void>() {
                    Bitmap bmpBg;
                    Bitmap bmpAds;
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            InputStream in = new URL(bgs[new Random().nextInt(bgs.length)]).openStream();
                            InputStream is = new URL(ads[new Random().nextInt(ads.length)]).openStream();

                            bmpBg = BitmapFactory.decodeStream(in);
                            bmpAds = BitmapFactory.decodeStream(is);
                        } catch (Exception e) {
                            // log error
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        if (bmpBg != null)
                        {
                            Drawable d = new BitmapDrawable(getResources(), bmpBg);
                            rootLayout.setBackground(d);
                        }
                        if(bmpAds != null){
                            Drawable ad = new BitmapDrawable(getResources(), bmpAds);
                            adsImageView.setImageDrawable(ad);
                        }

                    }

                }.execute();



            }

            public void onFinish() {
                chooseTransform();

                start();// here, when your CountDownTimer has finished , we start it again :)
            }
        };
        bgTimmer.start();
    }
    void chooseTransform(){
        int index = new Random().nextInt(16);
        switch (index){
            case 0:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
                break;
            case 1:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
                break;
            case 2:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
                break;
            case 3:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                break;
            case 4:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);
                break;
            case 5:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Foreground2Background);
                break;
            case 6:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
                break;
            case 7:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipPage);
                break;
            case 8:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
                break;
            case 9:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);
                break;
            case 10:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
                break;
            case 11:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
                break;
            case 12:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
                break;
            case 13:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
                break;
            case 14:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomIn);
                break;
            case 15:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
                break;
            case 16:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
                break;
            default:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        }
    }

}
