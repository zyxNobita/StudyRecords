package com.studyrecords.view.ui;


import com.studyrecords.model.entity.User;

/**
 * Created by Administrator on 2016/4/28.
 */
public interface LoginView {

    void initView();

    void promptParamNull(String string);

    void showProgressBar();

    void dismissProgressBar();

    void jumpNewActivity(User user);

}
