package com.example.netmodule;

import android.app.Application;

import com.example.netmodule.net.net.INetStack;
import com.example.netmodule.net.net.Net;
import com.example.netmodule.net.okhttp.OkHttpStack;

/**
 * Created by Administrator on 2016/5/31.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化okHttp队列
        OkHttpStack okHttpStack = new OkHttpStack();
        Net.init((INetStack) okHttpStack);
    }
}
