package com.studyrecords.presenter;


import com.studyrecords.model.entity.User;

/**
 * Created by Administrator on 2016/4/28.
 */
public interface OnLoginListener {

    void onSuccess(User user);
    void onFaild();

}
