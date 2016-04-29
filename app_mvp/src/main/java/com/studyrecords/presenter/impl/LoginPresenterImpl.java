package com.studyrecords.presenter.impl;


import com.studyrecords.model.LoginModel;
import com.studyrecords.model.entity.User;
import com.studyrecords.model.impl.LoginModelImpl;
import com.studyrecords.presenter.LoginPresenter;
import com.studyrecords.presenter.OnLoginListener;
import com.studyrecords.view.ui.LoginView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginListener {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void login(String userName, String userPwd) {
        mLoginView.showProgressBar();
        if (userName == null || userName.equals("")) {
            mLoginView.dismissProgressBar();
            mLoginView.promptParamNull("账号不能为空，请重新输入...");
            return;
        }
        if (userPwd == null || userPwd.equals("")) {
            mLoginView.dismissProgressBar();
            mLoginView.promptParamNull("密码不能为空，请重新输入...");
            return;
        }

        mLoginModel.login(userName, userPwd, this);
    }

    @Override
    public void initView() {
        mLoginView.initView();
    }

    @Override
    public void onSuccess(User user) {
        mLoginView.dismissProgressBar();
        mLoginView.jumpNewActivity(user);
    }

    @Override
    public void onFaild() {
        mLoginView.dismissProgressBar();
    }
}
