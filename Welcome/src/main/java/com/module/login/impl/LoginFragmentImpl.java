package com.module.login.impl;

import android.os.CountDownTimer;

import com.module.login.interfaces.LoginCommons;
import com.utils.TelNumMatch;

/**
 * Created by ZQIang on 2016/7/27.
 */

public class LoginFragmentImpl implements LoginCommons.ILoginFragmentManager {
    private LoginCommons.ILoginFragmentUiAction mUiAction;

    public LoginFragmentImpl(LoginCommons.ILoginFragmentUiAction uiAction) {
        mUiAction = uiAction;
    }

    @Override
    public void initView() {
        mUiAction.initView();
    }

    @Override
    public void sendSMS(String phoneNumber) {
        if (TelNumMatch.isValidPhoneNumber(phoneNumber)) {
            TimeCount timeCount = new TimeCount(5000, 1000);
            timeCount.start();
        }else if (phoneNumber.isEmpty()){
            mUiAction.showTips("手机号码不能为空");
        }else {
            mUiAction.showTips("请填写正确的手机号码");
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            mUiAction.codeFinish();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mUiAction.codeChange(millisUntilFinished);
        }
    }


}
