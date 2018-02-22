package com.workspaceit.photoclubbingme.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.workspaceit.photoclubbingme.R;
import com.workspaceit.photoclubbingme.Util;
import com.workspaceit.photoclubbingme.adapter.SlideshowAdapter;
import com.workspaceit.photoclubbingme.adapter.item.SlideShowItem;

public class SlideShowActivity extends AppCompatActivity {

    Toolbar toolbar;
    List<Integer> drawables = new ArrayList<>();
    private SharedPreferences sharedPref;
    private boolean isLoggedIn;
    ImageView imageView;
    private HorizontalInfiniteCycleViewPager viewPager;
    private ListView listView;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);

        sharedPref = this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);

//        if (!isLoggedIn) {
//            imageView = (ImageView) findViewById(R.id.adv);
//            imageView.setVisibility(View.VISIBLE);
//        }


        toolbar = (Toolbar) findViewById(R.id.slideshowToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Util.setTitleText("", this);

//        viewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.slideShow);
        listView = (ListView) findViewById(R.id.slideshow_items);
        SlideshowAdapter slideshowAdapter = new SlideshowAdapter(this);
        initImages(slideshowAdapter);
//        if (drawables.size() > 2) {
//            viewPager.startAutoScroll(true);
//            viewPager.setScrollDuration(2500);
//        }

        listView.setAdapter(slideshowAdapter);
        listView.invalidateViews();
    }

    private void initImages(final SlideshowAdapter adapter) {
        adapter.add(new SlideShowItem(R.drawable.music,
                "The Annex", "Annex Trip", "December 12, 2017"));
        adapter.add(new SlideShowItem(R.drawable.jelly,
                "The Jelly", "Jelly Trip", "December 13, 2017"));
//        drawables.add(R.drawable.unnamed);
//        drawables.add(R.drawable.star);
//        if(!isLoggedIn)
//        {
//            drawables.add(3,R.drawable.advlarge);
//        }

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
            viewPager.startAutoScroll(true);
            viewPager.setScrollDuration(2000);
        }

        if (item.getItemId() == R.id.stop) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.play);
            viewPager.setScrollDuration(250);
            viewPager.stopAutoScroll();

        }

        return true;
    }

}
