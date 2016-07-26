package com.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
/**
 * Created by ZQiang on 2016/7/26.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
