package com.module.register.impl;

import com.module.register.interfaces.IRegisterManager;
import com.module.register.interfaces.IRegisterUIAction;
import com.utils.TelNumMatch;

/**
 * Created by ZQiang on 2016/7/28.
 */

public class RegisterManager implements IRegisterManager {
    private IRegisterUIAction mUIAction;

    public RegisterManager(IRegisterUIAction uiAction) {
        mUIAction = uiAction;
    }


    @Override
    public void register(String phoneNumber, String password, String code) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            mUIAction.showTips("手机号码或密码不能为空");
            return;
        } else if (code.isEmpty()) {
            mUIAction.showTips("短信验证码不能为空");
            return;
        } else if (!TelNumMatch.isValidPhoneNumber(phoneNumber)) {
            mUIAction.showTips("请填写正确的手机号码");
            return;
        } else {
            //TODO 执行注册接口
            mUIAction.showLoding();

        }


    }

    @Override
    public void sendSMS(String phoneNumber) {

    }
}
