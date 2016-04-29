package com.studyrecords.model.impl;


import com.studyrecords.model.LoginModel;
import com.studyrecords.model.entity.User;
import com.studyrecords.presenter.OnLoginListener;

/**
 * Created by Administrator on 2016/4/28.
 */
public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final String userName, final String userPwd,
            final OnLoginListener onLoginListener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    if (userName.equals("admin") && userPwd.equals("admin")) {
                        onLoginListener.onSuccess(new User(userName, userPwd));
                    } else {
                        onLoginListener.onFaild();
                    }
                } catch (InterruptedException e) {
                    onLoginListener.onFaild();
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
