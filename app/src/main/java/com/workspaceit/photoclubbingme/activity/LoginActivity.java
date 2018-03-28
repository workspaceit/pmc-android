package com.workspaceit.photoclubbingme.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.android.volley.toolbox.Volley;
import com.workspaceit.photoclubbingme.R;
import com.workspaceit.photoclubbingme.backend.core.Services;
import com.workspaceit.photoclubbingme.backend.data.DatabaseHandler;
import com.workspaceit.photoclubbingme.backend.data.Login;
import com.workspaceit.photoclubbingme.backend.data.VolleyCallback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button fab,forgotPassword;
    private EditText email,password;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private DatabaseHandler db;
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
        db = new DatabaseHandler(this);

        sharedPref = this.getSharedPreferences("MyData",Context.MODE_PRIVATE);
        fab=(Button)findViewById(R.id.login);
        fab.setOnClickListener(this);
        email= (EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        forgotPassword=(Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (email.getText().length() > 5 && password.getText().length() > 5){
             new signNow().execute();
        }
    }

    private class signNow extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            Login login=new Login();
            login.getToken(LoginActivity.this,Volley.newRequestQueue(LoginActivity.this),new String[]{email.getText().toString(),password.getText().toString()},new VolleyCallback(){
                @Override
                public void onSuccess(final String result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                                if(result.equals("unauthorized"))
                                    Services.AlartBox(LoginActivity.this, "Email Or Password Not Match");
                                else if(result.equals("success"))
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                else
                                    Services.AlartBox(LoginActivity.this, "Try Again");
                            }
                        }
                    });
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
