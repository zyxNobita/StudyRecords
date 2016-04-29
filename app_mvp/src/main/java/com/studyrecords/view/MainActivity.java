package com.studyrecords.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.studyrecords.R;
import com.studyrecords.model.entity.User;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User mUser = getIntent().getParcelableExtra("USER");
        Toast.makeText(this,mUser.getUserName().toString(),Toast.LENGTH_SHORT).show();
    }
}
