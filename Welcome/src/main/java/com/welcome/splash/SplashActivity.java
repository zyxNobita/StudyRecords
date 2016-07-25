package com.welcome.splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.welcome.ad.RoundProgressBar;
import com.welcome.guide.AppConstants;
import com.welcome.guide.SpUtils;
import com.welcome.guide.WelcomeGuideActivity;
import com.welcome.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

public class SplashActivity extends Activity implements SplashManager.SplashInf {

    @Bind(R.id.roundProgressBar)
    RoundProgressBar roundProgressBar;
    @Bind(R.id.splashRLayout)
    RelativeLayout splashRLayout;

    private int progress = 0;
    private SplashManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mManager = new SplashManager(this);

        mManager.showView(SplashActivity.this);


        roundProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 101;
            }
        });
    }


    private void enterNextActivity() {
        Intent intent;
        if (SpUtils.getBoolean(this, AppConstants.FIRST_OPEN, true)) {
            intent = new Intent(this, WelcomeGuideActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void showAdView(Drawable drawable) {
        splashRLayout.setBackground(drawable);
        roundProgressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progress <= 100) {
                    progress += 1;
                    System.out.println(progress);
                    roundProgressBar.setProgress(progress);
                    try {
                        Thread.sleep(50);
                        if (progress > 100) {
                            enterNextActivity();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
