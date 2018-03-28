package com.workspaceit.photoclubbingme.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.workspaceit.photoclubbingme.R;
import com.workspaceit.photoclubbingme.backend.data.Login;
import com.workspaceit.photoclubbingme.backend.entity.Config;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button fab,forgotPassword;
    TextView textView;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},1);

        sharedPref = this.getSharedPreferences("MyData",Context.MODE_PRIVATE);


        fab=(Button)findViewById(R.id.login);
        fab.setOnClickListener(this);
        forgotPassword=(Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Login login=new Login();
        login.getToken(requestQueue,LoginActivity.this);

    }
}
