package com.module.login.interfaces;

import com.module.login.LoginFragment;
import com.module.login.enums.LoginType;

import java.util.ArrayList;

/**
 * Created by ZQiang on 2016/7/27.
 */

public interface LoginCommons {

    interface LoginUiAction {

        void initView(ArrayList<LoginFragment> fragments, String[] mTabItems);

        void showTips(String tips);

        void showLoading();

        void disLoading();

    }


    interface ILoginFragmentUiAction {

        void initView();

        void codeFinish();

        void codeChange(long time);

        void showTips(String tips);
    }


    interface ILoginManager {
        void initView();

        void login();

        void loginWithThird(LoginType loginType);
    }

    interface ILoginFragmentManager {

        void initView();

        void sendSMS(String phoneNumber);

    }

}
