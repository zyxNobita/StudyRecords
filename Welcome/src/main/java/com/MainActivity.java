package com;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import wechatedit.com.myapplication.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
    }
}
