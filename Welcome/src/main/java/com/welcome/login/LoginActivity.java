package com.welcome.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.base.BaseActivity;
import com.view.NewsTitleTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.loginVP)
    ViewPager mViewPager;
    @Bind(R.id.viewgroup)
    ViewGroup mViewGroup;
    ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] mTabItems = new String[]{"快速登录", "普通登录"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        LoginFragment loginFragment1 = LoginFragment.newInstance(true);
        LoginFragment loginFragment2 = LoginFragment.newInstance(false);
        fragments.add(loginFragment1);
        fragments.add(loginFragment2);

        addViewPagerView();


    }

    //添加ViewPager
    private void addViewPagerView() {
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
                moveTitleLabel(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void moveTitleLabel(int position) {

        // 点击当前按钮所有左边按钮的总宽度
        int visiableWidth = 0;
        // HorizontalScrollView的宽度
        int scrollViewWidth = 0;

        mViewGroup.measure(mViewGroup.getMeasuredWidth(), -1);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                mViewGroup.getMeasuredWidth(), -1);
        params.gravity = Gravity.CENTER_VERTICAL;
        mViewGroup.setLayoutParams(params);
        for (int i = 0; i < mViewGroup.getChildCount(); i++) {
            NewsTitleTextView itemView = (NewsTitleTextView) mViewGroup.getChildAt(i);
            int width = itemView.getMeasuredWidth();
            if (i < position) {
                visiableWidth += width;
            }
            scrollViewWidth += width;

            if (i == mViewGroup.getChildCount()) {
                break;
            }
            if (position != i) {
                itemView.setTextColor(getResources().getColor(R.color.unSelectedTxt));
                itemView.setIsHorizontaline(false);
            } else {
                itemView.setTextColor(getResources().getColor(R.color.selectedTxt));
                itemView.setIsHorizontaline(true);
            }
        }
    }
}
