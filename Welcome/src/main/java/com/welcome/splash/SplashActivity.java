package com.welcome.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.welcome.ad.AdActivity;

import wechatedit.com.myapplication.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //延时1.5s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterNextActivity();
            }
        }, 1500);

    }

    private void enterNextActivity() {
        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);
        finish();
    }
}
