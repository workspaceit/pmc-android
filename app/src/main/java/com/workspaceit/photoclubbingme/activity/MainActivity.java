package com.workspaceit.photoclubbingme.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.workspaceit.photoclubbingme.EventBus.LocationItemClickEventBus;
import com.workspaceit.photoclubbingme.R;

import com.workspaceit.photoclubbingme.activity.fragments.LocationTabFragmentHolder;
import com.workspaceit.photoclubbingme.adapter.EventAdapter;
import com.workspaceit.photoclubbingme.adapter.FolderAdapter;
import com.workspaceit.photoclubbingme.adapter.PagerAdapter;
import com.workspaceit.photoclubbingme.adapter.ThumbnailAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        FolderAdapter.ItemClickListener, ThumbnailAdapter.ItemClickListener {

    private EventAdapter eventAdapter;
    private ThumbnailAdapter thumbnailAdapter;
    private Toolbar toolbar;
    private SharedPreferences sharedPref;

    // tab page adapter
    private PagerAdapter adapter;
    private ViewPager viewPager;

    private boolean isLoggedIn=false;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Locations"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setText("SlideShows"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        // create the pager adapter only if it the first time it loaded
        if (adapter == null) {
            adapter = new PagerAdapter(getSupportFragmentManager());
        }
        for(int i=0; i<tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setText(adapter.getPageTitle(i));
            tabLayout.getTabAt(i).setCustomView(R.layout.tab_text);
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final TabLayout.Tab finalTab = tab;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(finalTab.getPosition());
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        sharedPref = this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        isLoggedIn=sharedPref.getBoolean("isLoggedIn",false);

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationItemClickEventBus event) {
        Fragment targetFragment = adapter.getItem(0);
        if (targetFragment instanceof LocationTabFragmentHolder) {
            LocationTabFragmentHolder locationTab = (LocationTabFragmentHolder) targetFragment;
            locationTab.showFragment(LocationTabFragmentHolder.USE_FRAGMENT_EVENTS, true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loc_name:
            case R.id.city_count:
            case R.id.recent_locations:
                Fragment targetFragment = adapter.getItem(0);
                if (targetFragment instanceof LocationTabFragmentHolder) {
                    LocationTabFragmentHolder locationTab = (LocationTabFragmentHolder) targetFragment;
                    locationTab.showFragment(LocationTabFragmentHolder.USE_FRAGMENT_VENUE, true);
                }
                //startActivity(new Intent(this,FolderActivity.class));
                break;
            
        }
        if(v.getId() == R.id.event_name){
            startActivity(new Intent(this,FolderActivity.class));
        }
    }

    @Override
    public void onFolderClick(View view, int position) {
        startActivity(new Intent(this, FolderActivity.class));
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

    @Override
    public void onThumbnailLongClick(View view, int position) {
//        if(!thumbnailAdapter.is_in_action_mode){
//            toolbar.getMenu().clear();
//            toolbar.inflateMenu(R.menu.multi_selected_menu);
//
//            thumbnailAdapter.is_in_action_mode = true;
//            thumbnailAdapter.notifyDataSetChanged();
//        }else{
//            toolbar.getMenu().clear();
//            thumbnailAdapter.is_in_action_mode = false;
//            thumbnailAdapter.notifyDataSetChanged();
//        }

    }
}
