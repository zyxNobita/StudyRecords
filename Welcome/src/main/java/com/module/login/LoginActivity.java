package com.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.BaseActivity;
import com.module.login.enums.LoginType;
import com.module.login.impl.LoginManager;
import com.module.login.interfaces.LoginCommons;
import com.module.register.RegisterActivity;
import com.view.CircleImageView;
import com.view.NewsTitleTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

/**
 * 登录页
 * create by ZQiang on 2016年7月26日
 * <p>
 * TODO
 */
public class LoginActivity extends BaseActivity implements LoginCommons.LoginUiAction {

    @Bind(R.id.loginVP)
    ViewPager mViewPager;
    @Bind(R.id.viewgroup)
    ViewGroup mViewGroup;
    @Bind(R.id.loginBtn)
    Button loginBtn;
    @Bind(R.id.imageView)
    CircleImageView imageView;
    @Bind(R.id.registerBtn)
    TextView registerBtn;
    @Bind(R.id.forgetPwdBtn)
    TextView forgetPwdBtn;
    @Bind(R.id.loginSinaBtn)
    ImageView loginSinaBtn;
    @Bind(R.id.loginQQBtn)
    ImageView loginQQBtn;
    @Bind(R.id.loginWeChatBtn)
    ImageView loginWeChatBtn;

    private LoginManager mLoginManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginManager = new LoginManager(this);
        mLoginManager.initView();
    }

    @Override
    public void initView(final ArrayList<LoginFragment> fragments, String[] mTabItems) {
        setOnClick();

        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.width = ViewPager.LayoutParams.WRAP_CONTENT;
        params.height = ViewPager.LayoutParams.WRAP_CONTENT;

        for (int i = 0; i < mTabItems.length; i++) {
            String label = mTabItems[i];
            NewsTitleTextView tv = new NewsTitleTextView(this);
            int itemWidth = (int) tv.getPaint().measureText(label);
            tv.setLayoutParams(new LinearLayout.LayoutParams((itemWidth * 3 / 2), -1));
            tv.setTextSize(15);
            tv.setText(label);
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < mViewGroup.getChildCount(); i++) {
                        NewsTitleTextView child = (NewsTitleTextView) mViewGroup.getChildAt(i);
                        if (child == view) {
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                }
            });
            if (i == 0) {
                tv.setTextColor(getResources().getColor(R.color.selectedTxt));
                tv.setIsHorizontaline(true);
            } else {
                tv.setTextColor(getResources().getColor(R.color.unSelectedTxt));
                tv.setIsHorizontaline(false);
                tv.setIsVerticalLine(true);
            }

            mViewGroup.addView(tv);
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLoginManager.moveTitleLabel(mViewGroup, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setOnClick() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginManager.login();
            }
        });

        forgetPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO  使用浏览器打开找回密码的网页
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginSinaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginManager.loginWithThird(LoginType.SINA);
            }
        });

        loginQQBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginManager.loginWithThird(LoginType.QQ);
            }
        });

        loginWeChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginManager.loginWithThird(LoginType.WECHAT);
            }
        });
    }


    @Override
    public void showTips(String tips) {
        Toast.makeText(this, tips, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void disLoading() {

    }


}
