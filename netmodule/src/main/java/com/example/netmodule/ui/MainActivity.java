package com.example.netmodule.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.netmodule.R;
import com.example.netmodule.bean.User;
import com.example.netmodule.net.CommParser;
import com.example.netmodule.net.net.Net;
import com.example.netmodule.net.net.Result;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Net.get("weatherUrl", new CommParser<User>("data") {
                },
                new Net.Callback<User>() {
                    @Override
                    public void callback(Result<User> result) {
                        if (result.getError_code() == Result.SUCCESS) {
//                            mTextView.setText("success");
                        } else {
//                            mTextView.setText("error");
                        }
                    }
                }, getClass().getName());
    }


    @Override
    protected void onDestroy() {
        Net.cancel(getClass().getName());
        super.onDestroy();
    }
}
