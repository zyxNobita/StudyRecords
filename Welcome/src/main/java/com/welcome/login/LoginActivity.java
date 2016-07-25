package com.welcome.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.view.ColumnHorizontalScrollView;
import com.welcome.guide.AppConstants;
import com.welcome.guide.SpUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.loginBtn)
    Button loginBtn;

    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        LoginFragment loginFragment1 = LoginFragment.newInstance(true);
        LoginFragment loginFragment2 = LoginFragment.newInstance(false);
        fragments.add(loginFragment1);
        fragments.add(loginFragment2);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtils.putBoolean(LoginActivity.this, AppConstants.FIRST_OPEN, true);
            }
        });


        ViewPager mViewPager = (ViewPager) findViewById(R.id.loginVP);
        ColumnHorizontalScrollView title = (ColumnHorizontalScrollView) findViewById(R.id.loginTitle);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });
        title.setTitle("快速登录", "普通登录");  //这个是设置标题的

        title.setspace(40);
        title.setViewPager(mViewPager);  //这个是将ViewPager对象给自定义的View
    }

}
