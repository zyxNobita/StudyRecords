package com.module.login.impl;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.module.login.LoginFragment;
import com.module.login.enums.LoginType;
import com.module.login.interfaces.LoginCommons;
import com.utils.TelNumMatch;
import com.view.NewsTitleTextView;

import java.util.ArrayList;

import wechatedit.com.myapplication.R;

/**
 * Created by ZQiang on 2016/7/27.
 */

public class LoginManager implements LoginCommons.ILoginManager {
    private Context mContext;
    private LoginCommons.LoginUiAction mUiAction;
    ArrayList<LoginFragment> fragments = new ArrayList<>();
    private int mPosition;


    public LoginManager(LoginCommons.LoginUiAction uiAction) {
        mUiAction = uiAction;
        mContext = (Context) uiAction;
    }

    @Override
    public void initView() {
        String[] mTabItems = new String[]{"快速登录", "普通登录"};
        LoginFragment loginFragment1 = LoginFragment.newInstance(true);
        LoginFragment loginFragment2 = LoginFragment.newInstance(false);
        fragments.add(loginFragment1);
        fragments.add(loginFragment2);
        mUiAction.initView(fragments, mTabItems);
    }

    @Override
    public void login() {
        String phoneNumber = fragments.get(mPosition).getPhoneNumber();
        String password = fragments.get(mPosition).getPassword();
        //TODO check
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            mUiAction.showTips("手机号或密码不能为空");
            return;
        } else if (!TelNumMatch.isValidPhoneNumber(phoneNumber)) {
            mUiAction.showTips("请填写正确的手机号码");
            return;
        } else {
            //TODO 执行登录接口
            mUiAction.showTips("TODO 执行登录接口");
            mUiAction.showLoading();
        }
    }

    @Override
    public void loginWithThird(LoginType loginType) {
        switch (loginType) {
            case QQ:
                break;
            case SINA:
                break;
            case WECHAT:
                break;
            default:
                break;
        }
    }


    public void moveTitleLabel(ViewGroup mViewGroup, int position) {
        mPosition = position;

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
                itemView.setTextColor(mContext.getResources().getColor(R.color.unSelectedTxt));
                itemView.setIsHorizontaline(false);
            } else {
                itemView.setTextColor(mContext.getResources().getColor(R.color.selectedTxt));
                itemView.setIsHorizontaline(true);
            }
        }
    }
}
