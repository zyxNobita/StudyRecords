package com.other.db.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.other.R;

public class DBActivity extends AppCompatActivity implements ViewInterface {

    @butterknife.Bind(R.id.inputTxt)
    TextView inputTxt;
    @butterknife.Bind(R.id.btnInit)
    Button btnInit;
    @butterknife.Bind(R.id.btnAdd)
    Button btnAdd;
    @butterknife.Bind(R.id.btnQuery)
    Button btnQuery;
    @butterknife.Bind(R.id.btnDelete)
    Button btnDelete;
    @butterknife.Bind(R.id.btnUpdate)
    Button btnUpdate;
    @butterknife.Bind(R.id.showTxt)
    TextView showTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        butterknife.ButterKnife.bind(this);
    }

    @Override
    public void showTxt(String txt) {

    }
}
