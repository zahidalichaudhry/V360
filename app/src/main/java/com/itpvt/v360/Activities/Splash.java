package com.itpvt.v360.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.itpvt.v360.R;

public class Splash extends Activity {

    // Splash screen apperance time (3.5 seconds)
    private static int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //thread to show the splash screen for 3.5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, ChoosePanel.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
