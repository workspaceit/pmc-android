package com.workspaceit.photoclubbingme.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.makeramen.roundedimageview.RoundedImageView;
import com.workspaceit.photoclubbingme.Util;

import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.workspaceit.photoclubbingme.R;

public class ShowCaseActivity extends AppCompatActivity implements View.OnClickListener , BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {

    private ImageView imageViewSms, imageView1, imageView2, imageView3Mail;
    Toolbar toolbar;
    RoundedImageView imgWaterMark;
    RelativeLayout hideMask;
    RelativeLayout deleteMask;
    private SliderLayout showcase;
    private SharedPreferences sharedPref;
    private boolean isLoggedIn;
    private ImageView adv;
    private View bottomBottom;
    private GridLayout showCaseTitleBar;
    private LinearLayout imageInfoHolder;
    private LinearLayout showCaseFooter;
    private boolean panelsShowing = false;
    private boolean isAnimationRunning = false;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initPanelShowStatus() {
        showCaseTitleBar.setAlpha(0f);
        showCaseFooter.setAlpha(0f);
        imageInfoHolder.setAlpha(0f);
    }

    private void showPanels(final boolean shouldShow) {
        ObjectAnimator.ofFloat(showCaseTitleBar, "alpha", shouldShow ? 1f : 0f)
                .setDuration(500).start();
        ObjectAnimator.ofFloat(showCaseFooter, "alpha", shouldShow ? 1f : 0f)
                .setDuration(500).start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageInfoHolder, "alpha", shouldShow ? 1f : 0f)
                .setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                synchronized (ShowCaseActivity.this) {
                    panelsShowing = shouldShow;
                    isAnimationRunning = false;
                }
            }
        });
        animator.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_case);
        showcase     = (SliderLayout)findViewById(R.id.show_case_slideShow);
        imgWaterMark = (RoundedImageView) findViewById(R.id.imgWatermark);
        sharedPref = this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);
        adv = (ImageView) findViewById(R.id.adv);
        bottomBottom = findViewById(R.id.nav);
        adv.setVisibility(View.GONE);
        bottomBottom.setVisibility(View.GONE);
        showCaseTitleBar = (GridLayout) findViewById(R.id.show_case_titlebar);
        imageInfoHolder = (LinearLayout) findViewById(R.id.image_tnd_holder);
        showCaseFooter = (LinearLayout) findViewById(R.id.show_case_footer);
//        if (isLoggedIn) {
//            adv.setVisibility(View.GONE);
//        } else {
//            bottomBottom.setVisibility(View.GONE);
//        }

        toolbar = (Toolbar) findViewById(R.id.showCaseToolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Util.setTitleText("Image_title", this);
        hideMask = (RelativeLayout) findViewById(R.id.hideMask);
        deleteMask = (RelativeLayout) findViewById(R.id.deleteMask);

        imageViewSms = (ImageView) findViewById(R.id.sms);
        imageView1 = (ImageView) findViewById(R.id.iv2);
        imageView2 = (ImageView) findViewById(R.id.iv3);
        imageView3Mail = (ImageView) findViewById(R.id.mail);

        imageViewSms.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3Mail.setOnClickListener(this);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "https://i.pinimg.com/originals/86/8a/e0/868ae003d42ce1d4e8eb861e5a5b1602.jpg");
        url_maps.put("2", "https://i.pinimg.com/originals/1e/a5/05/1ea5050edd7ca3ad87561e272c35be45.jpg");
        url_maps.put("3", "http://i.dailymail.co.uk/i/pix/2013/01/01/article-2255671-0B5478D6000005DC-897_634x358.jpg");
        url_maps.put("4", "https://nowtoronto.com/downloads/84118/download/OVO-FEST-3949.jpg?cb=ee4dea9209a5a47ac839a523035408a2&w=640");
        url_maps.put("5", "https://ak7.picdn.net/shutterstock/videos/27263227/thumb/11.jpg");


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.img_1);
        file_maps.put("Big Bang Theory",R.drawable.img_2);
        file_maps.put("House of Cards",R.drawable.img_3);
        file_maps.put("Game of Thrones", R.drawable.img_4);
        file_maps.put("Game of Thrones", R.drawable.img_5);
        file_maps.put("Game of Thrones", R.drawable.img_6);
        showcase.setPresetTransformer(SliderLayout.Transformer.Default);
        showcase.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        showcase.setCustomAnimation(new DescriptionAnimation());
//        showcase.setDuration(4000);
        showcase.addOnPageChangeListener(this);
        showcase.stopAutoCycle();

        initPanelShowStatus();

        for(String name : url_maps.keySet()){
            DefaultSliderView slider = new  DefaultSliderView(this);
            slider.image(url_maps.get(name)) .setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
            showcase.addSlider(slider);
        }
    }

    public void go(View v) {
        
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sms) {
            startActivity(new Intent(this, SendActivity.class));
        }

        if(v.getId() == R.id.mail) {
            startActivity(new Intent(this, SendActivity.class));
        }
        goToSend();
    }

    public void goToSend() {
        startActivity(new Intent(this, SendInfoActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.showcase_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.hide) {
//            toolbar.getMenu().clear();
//            toolbar.inflateMenu(R.menu.showcase_conxtual_menu);
//            hideMask.setVisibility(View.VISIBLE);
//        }
        if (item.getItemId() == R.id.unHide) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.showcase_menu);
            hideMask.setVisibility(View.GONE);
        }
        if (item.getItemId() == R.id.delete) {
            if (hideMask.getVisibility() == View.VISIBLE) {
                hideMask.setVisibility(View.GONE);
            }
            deleteMask.setVisibility(View.VISIBLE);
        }
        if (item.getItemId() == R.id.toQueue) {
            finish();
        }

        if (item.getItemId() == R.id.watermark) {
            imgWaterMark.setVisibility(View.VISIBLE);
        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed

        showcase.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        synchronized (ShowCaseActivity.this) {
            if (isAnimationRunning) return;
            isAnimationRunning = true;
        }
        panelsShowing = !panelsShowing;
        showPanels(panelsShowing);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
