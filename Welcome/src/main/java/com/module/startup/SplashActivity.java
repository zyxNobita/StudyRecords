package com.module.startup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.welcome.ad.RoundProgressBar;
import com.welcome.guide.AppConstants;
import com.welcome.guide.SpUtils;
import com.welcome.guide.WelcomeGuideActivity;
import com.module.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

/**
 * 闪屏页+广告页
 * create by ZQiang on 2016年7月26日
 * TODO: 检测网络情况并提示用户，如果没有网络，广告如何展示？默认；      广告接口请求成功后显示广告，否则该如何处理？
 */
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
        splashRLayout.setClickable(false);
        roundProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 101;
            }
        });
    }


    private void enterNextActivity() {
        Intent intent;
        boolean flag = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN, true);
        if (flag) {
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
        splashRLayout.setClickable(true);
        roundProgressBar.setVisibility(View.VISIBLE);
        splashRLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SplashActivity.this, "进入广告页...", Toast.LENGTH_SHORT).show();
            }
        });

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progress <= 100) {
                    progress += 1;
                    System.out.println(progress);
                    roundProgressBar.setProgress(progress);
                    try {
                        Thread.sleep(40);
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
