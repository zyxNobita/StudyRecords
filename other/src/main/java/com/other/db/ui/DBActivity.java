package com.other.db.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.other.R;

public class DBActivity extends AppCompatActivity implements ViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
    }

    @Override
    public void showTxt(String txt) {

    }
}
