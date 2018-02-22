package com.workspaceit.photoclubbingme.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.workspaceit.photoclubbingme.ListSpacingDecoration;
import com.workspaceit.photoclubbingme.Util;
import com.workspaceit.photoclubbingme.adapter.SendRecycleAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.workspaceit.photoclubbingme.R;

public class SendActivity extends AppCompatActivity {
    String[] data2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "2", "3", "4", "5", "6", "7", "8", "9", "2", "3", "4", "5", "6", "7", "8", "9"};
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        toolbar=(Toolbar)findViewById(R.id.sendToolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Util.setTitleText("Back",this);
        recyclerView=(RecyclerView)findViewById(R.id.send_rv);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        SendRecycleAdapter sendRecycleAdapter= new SendRecycleAdapter(this,data2);
        recyclerView.addItemDecoration(new ListSpacingDecoration(30));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(sendRecycleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
