package com.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * BaseActivity
 * Created by ZQiang on 2016/7/26.
 */

public class BaseActivity extends AppCompatActivity {

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
