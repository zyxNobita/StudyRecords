package com.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * BaseActivity
 * Created by ZQiang on 2016/7/26.
 */

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoadingDialog() {
        Toast.makeText(this, "加载中...", Toast.LENGTH_SHORT).show();
    }

    public void showWarring(String warring) {

    }
}
