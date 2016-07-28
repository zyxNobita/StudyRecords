package com.module.register.interfaces;

/**
 * Created by ZQiang on 2016/7/28.
 */

public interface IRegisterManager {

    void register(String phoneNumber, String password, String code);

    void sendSMS(String phoneNumber);

}
