package com.module.sliding;

import android.os.Bundle;
import android.view.Window;

import com.base.BaseActivity;

import wechatedit.com.myapplication.R;

public class Main2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
    }
}
