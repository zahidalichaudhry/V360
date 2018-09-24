package com.itpvt.v360.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.itpvt.v360.Config;
import com.itpvt.v360.R;

public class Home extends AppCompatActivity {

    private boolean exit = false;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.textactive));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setBackgroundColor(getResources().getColor(R.color.iron));

    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true

        if (loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Home.this, ChoosePanel.class);
        startActivity(intent);
//    }
//        if (exit)
//        {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                this.finishAffinity();
//            }
//        }
//        else {
//            Toast.makeText(this, "Press Back again to Exit.",
//                    Toast.LENGTH_SHORT).show();
//            exit = true;
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exit = false;
//                }
//            }, 3 * 1000);
//
//        }
    }
}
