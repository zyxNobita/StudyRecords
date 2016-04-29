package com.studyrecords.model;


import com.studyrecords.presenter.OnLoginListener;

/**
 * Created by Administrator on 2016/4/28.
 */
public interface LoginModel {
    void login(String userName, String userPwd, OnLoginListener onLoginListener);
}
