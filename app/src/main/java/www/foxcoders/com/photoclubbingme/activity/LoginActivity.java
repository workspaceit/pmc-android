package www.foxcoders.com.photoclubbingme.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import www.foxcoders.com.photoclubbingme.R;

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

         sharedPref = this.getSharedPreferences("MyData",Context.MODE_PRIVATE);


        fab=(Button)findViewById(R.id.login);
        fab.setOnClickListener(this);
        forgotPassword=(Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        if(v.getId()==fab.getId())
        {
            editor = sharedPref.edit();
            editor.putBoolean("isLoggedIn",true);
            editor.apply();
            editor.commit();
            startActivity(new Intent(this,MainActivity.class));
        }

        if(v.getId()==forgotPassword.getId())
        {
            editor = sharedPref.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();
            editor.commit();
            startActivity(new Intent(this,FolderActivity.class));

        }

    }
}
