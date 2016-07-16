package com.welcome.ad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MainActivity;
import com.welcome.guide.AppConstants;
import com.welcome.guide.SpUtils;
import com.welcome.guide.WelcomeGuideActivity;

import wechatedit.com.myapplication.R;

public class AdActivity extends Activity {

    @butterknife.Bind(R.id.roundProgressBar)
    RoundProgressBar mRoundProgressBar;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        butterknife.ButterKnife.bind(this);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progress <= 100) {
                    progress += 1;
                    System.out.println(progress);
                    mRoundProgressBar.setProgress(progress);
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

        mRoundProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 101;
            }
        });
    }

    private void enterNextActivity() {
        Intent intent = null;
        if (SpUtils.getBoolean(this, AppConstants.FIRST_OPEN, false)) {
            intent = new Intent(this, WelcomeGuideActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
