package com.module.startup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import wechatedit.com.myapplication.R;

/**
 * 广告接口请求
 * Created by ZQiang on 2016/7/25.
 */

public class SplashManager {

    private SplashInf mView;
    private Context mContext;

    public SplashManager(SplashInf view) {
        this.mView = view;
    }

    public void showView(Context context) {
        mContext = context;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.showAdView(getAdDrawable());
            }
        }, 1500);

    }

    public Drawable getAdDrawable() {

        //TODO 网络接口，请求数据
        Resources resources = mContext.getResources();
        Drawable drawable = resources.getDrawable(R.mipmap.pic_guidepage_2);

        return drawable;
    }


    public interface SplashInf {
        void showAdView(Drawable drawable);
    }

}
