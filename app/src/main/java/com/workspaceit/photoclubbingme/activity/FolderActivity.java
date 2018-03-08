package com.workspaceit.photoclubbingme.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.workspaceit.photoclubbingme.ListSpacingDecoration;
import com.workspaceit.photoclubbingme.Util;
import com.workspaceit.photoclubbingme.adapter.FolderAdapter;
import com.workspaceit.photoclubbingme.adapter.ThumbnailAdapter;
import com.workspaceit.photoclubbingme.components.GridRecycleView;
import com.workspaceit.photoclubbingme.interfaces.OnZoomListener;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.workspaceit.photoclubbingme.R;


public class FolderActivity extends AppCompatActivity implements View.OnClickListener, FolderAdapter.ItemClickListener, ThumbnailAdapter.ItemClickListener {

    final  ArrayList<Integer> itemsimg = new ArrayList<Integer>();
    Spinner spinner;
    ThumbnailAdapter thumbnailAdapter, thumbnailAdapter2;
    FolderAdapter folderAdapter;
    View bottomBottom;
    ImageView adv;
    ImageButton cancelFullScreen;
    LinearLayout linearLayout;
    RecyclerView folder_rv;
    GridRecycleView thumb_rv;
    private Toolbar toolbar;
    private ImageView imageViewSms, imageView1, imageView2, imageView3Mail;
    private SharedPreferences sharedPref;
    private boolean isLoggedIn = false;
    private LinearLayout bottom;
    private boolean fullThumb = false;
    private ScaleGestureDetector mDetector;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
//        spinner=(Spinner)findViewById(R.id.spnDate);


        sharedPref = this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);
        adv = (ImageView) findViewById(R.id.adv);
        bottomBottom = findViewById(R.id.nav);
        cancelFullScreen = (ImageButton) findViewById(R.id.cancelFullscreen);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        cancelFullScreen.setOnClickListener(this);
        cancelFullScreen.setBackgroundResource(R.drawable.ic_keyboard_arrow_left_white_24dp);
        if (isLoggedIn) {
            adv.setVisibility(View.GONE);
        } else {
            bottomBottom.setVisibility(View.GONE);
        }
        bottom = (LinearLayout) findViewById(R.id.navView);
//        bottom.setVisibility(LinearLayout.GONE);



        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_2);
        folder_rv = (RecyclerView) findViewById(R.id.folder_rv);
        thumb_rv = (GridRecycleView) findViewById(R.id.thumb_rv);

        toolbar = (Toolbar) findViewById(R.id.folderToolbar);

        setSupportActionBar(toolbar);
//        ImageButton imageButton = (ImageButton) toolbar.findViewById(R.id.preview);
//        imageButton.setOnClickListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Util.setTitleText("Picture Me Clubbing", this);



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        folderAdapter = new FolderAdapter(this, itemsimg);
        folder_rv.setLayoutManager(linearLayoutManager);
        folder_rv.addItemDecoration(new ListSpacingDecoration(this, R.dimen.folder));
        folder_rv.setAdapter(folderAdapter);


        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, ThumbnailAdapter.INITIAL_SPAN_COUNT);
        thumbnailAdapter = new ThumbnailAdapter( thumb_rv, this,2 , Util.getDummyThumbnailImages());
        gridLayoutManager.setSpanSizeLookup(thumbnailAdapter.getSpanSizeLookup());
        thumb_rv.addItemDecoration(new ListSpacingDecoration(10));
        thumb_rv.setLayoutManager(gridLayoutManager);
        thumb_rv.setHasFixedSize(true);
        thumb_rv.setAdapter(thumbnailAdapter);
        thumb_rv.setOnZoomListener(new OnZoomListener() {
            @Override
            public void onZoomIn() {
                thumbnailAdapter.increaseSpanSize();
                thumbnailAdapter.notifyChange(((GridLayoutManager)thumb_rv.getLayoutManager())
                        .findFirstVisibleItemPosition(), thumbnailAdapter.getViewItemsInRange());
                thumb_rv.invalidate();
            }

            @Override
            public void onZoomOut() {
                thumbnailAdapter.decreaseSpanSize();
                thumbnailAdapter.notifyChange(((GridLayoutManager)thumb_rv.getLayoutManager())
                        .findFirstVisibleItemPosition(), thumbnailAdapter.getViewItemsInRange());
                thumb_rv.invalidate();
            }
        });

        imageViewSms = (ImageView) findViewById(R.id.sms);
        imageView1 = (ImageView) findViewById(R.id.iv2);
        imageView2 = (ImageView) findViewById(R.id.iv3);
        imageView3Mail = (ImageView) findViewById(R.id.mail);

        imageViewSms.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3Mail.setOnClickListener(this);


//        spinner.setAdapter(adapter);
        thumb_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int ydy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getId() == R.id.thumb_rv) {
                    int offset = dy - ydy;
                    ydy = dy;
                    boolean shouldRefresh = (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0)
                            && (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) && offset > 30;
                    if (shouldRefresh) {
//                        linearLayout.setVisibility(View.GONE);
//                        cancelFullScreen.setVisibility(View.VISIBLE);
                        return;
                    }
                    boolean shouldPullUpRefresh = linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getChildCount()
                            && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING && offset < -30;
                    if (shouldPullUpRefresh) {

                        return;
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        thumbnailAdapter.is_in_action_mode = false;
        thumbnailAdapter.notifyDataSetChanged();
        toolbar.getMenu().clear();
        super.onResume();
    }

    public void goToSend() {
        startActivity(new Intent(this, SendInfoActivity.class));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancelFullscreen) {
            if (linearLayout.getVisibility() == View.VISIBLE) {
                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.slide_to_left);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        linearLayout.setVisibility(View.GONE);
                        cancelFullScreen.setBackgroundResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                        thumbnailAdapter.setExpanded(true);
                        thumbnailAdapter.notifyDataSetChanged();
//                        final GridLayoutManager gridLayoutManager = new GridLayoutManager(FolderActivity.this, ThumbnailAdapter.INITIAL_SPAN_COUNT);
//                        thumb_rv.setLayoutManager(gridLayoutManager);
//                        thumbnailAdapter = new ThumbnailAdapter(thumb_rv,FolderActivity.this,4 , Util.getDummyThumbnailImages());
//                        gridLayoutManager.setSpanSizeLookup(thumbnailAdapter.getSpanSizeLookup());
//                        thumb_rv.setAdapter(thumbnailAdapter);
                    }
                });
                linearLayout.setPivotX(0f);
                animator.setTarget(linearLayout);
                animator.start();
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.startAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.anim_slide_in_right));
                cancelFullScreen.setBackgroundResource(R.drawable.ic_keyboard_arrow_left_white_24dp);

//                final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, ThumbnailAdapter.INITIAL_SPAN_COUNT);
//                thumb_rv.setLayoutManager(gridLayoutManager);
//                thumbnailAdapter = new ThumbnailAdapter(this,2 , Util.getDummyThumbnailImages());
//                thumb_rv.setAdapter(thumbnailAdapter);
                thumbnailAdapter.setExpanded(false);
                thumbnailAdapter.notifyDataSetChanged();
            }
        }

        if(v.getId() == R.id.sms) {
            startActivity(new Intent(this, SendInfoActivity.class));
        }

        if(v.getId() == R.id.mail) {
            startActivity(new Intent(this, SendInfoActivity.class));
        }
//        if(v.getId() == R.id.preview){
//            if (!this.fullThumb){
//                thumb_rv.setLayoutManager(null);
//                thumb_rv.setAdapter(null);
//                final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//                thumb_rv.setLayoutManager(gridLayoutManager);
//                thumbnailAdapter = new ThumbnailAdapter(this,2 ,itemsimg);
//                thumb_rv.setAdapter(thumbnailAdapter);
//            }else{
//                thumb_rv.setLayoutManager(null);
//                thumb_rv.setAdapter(null);
//                final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
//                thumb_rv.setLayoutManager(gridLayoutManager);
//                thumbnailAdapter = new ThumbnailAdapter(this,4 ,itemsimg);
//                thumb_rv.setAdapter(thumbnailAdapter);
//            }
//            this.fullThumb = !this.fullThumb;
//        }
    }

    @Override
    public void onFolderClick(View view, int position) {

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
        startActivity(new Intent(this, ShowCaseActivity.class), b);

    }

    @Override
    public void onThumbnailLongClick(View view, int position) {
        if(!thumbnailAdapter.is_in_action_mode){
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.multi_selected_menu);

            thumbnailAdapter.is_in_action_mode = true;
            thumbnailAdapter.notifyDataSetChanged();
        }else{
            toolbar.getMenu().clear();
            thumbnailAdapter.is_in_action_mode = false;
            thumbnailAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (item.getItemId() == R.id.slide) {
////            startActivity(new Intent(this,SlideShowActivity.class));
//            startActivity(new Intent(this, NewSliderActivity.class));
//        }
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }

        return true;
    }


}
