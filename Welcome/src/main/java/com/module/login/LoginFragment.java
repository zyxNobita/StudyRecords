package com.module.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.module.login.impl.LoginFragmentImpl;
import com.module.login.interfaces.LoginCommons;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;


/**
 * Created by ZQiang on 2016/7/25.
 */
public class LoginFragment extends Fragment implements LoginCommons.ILoginFragmentUiAction {

    @Bind(R.id.loginPhoneNumber)
    EditText loginPhoneNumber;
    @Bind(R.id.loginClearBtn)
    Button loginClearBtn;
    @Bind(R.id.loginPwd)
    EditText loginPwd;
    @Bind(R.id.loginSendSMSBtn)
    Button loginSendSMSBtn;
    private boolean isQueckLogon = false; //默认普通登录
    private Context mContext;

    private View mView;
    private LoginFragmentImpl mLoginFragment;

    public LoginFragment(boolean isQueckLogon) {
        this.isQueckLogon = isQueckLogon;
    }

    public static LoginFragment newInstance(boolean isQueckLogon) {
        LoginFragment newFragment = new LoginFragment(isQueckLogon);
        return newFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, mView);
        mLoginFragment = new LoginFragmentImpl(this);
        mLoginFragment.initView();

        return mView;
    }

    public String getPhoneNumber() {
        return loginPhoneNumber.getText().toString();
    }

    public String getPassword() {
        return loginPwd.getText().toString();
    }

    private void setOnClick() {

        loginSendSMSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginFragment.sendSMS(getPhoneNumber());
            }
        });

        loginClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPhoneNumber.setText("");
            }
        });

    }


    @Override
    public void initView() {
        if (isQueckLogon)
            loginSendSMSBtn.setVisibility(View.VISIBLE);
        else
            loginSendSMSBtn.setVisibility(View.GONE);
        setOnClick();
    }

    @Override
    public void codeFinish() {
        loginSendSMSBtn.setText("重新获取");
        loginSendSMSBtn.setClickable(true);
    }

    @Override
    public void codeChange(long time) {
        loginSendSMSBtn.setClickable(false);//防止重复点击
        loginSendSMSBtn.setText("倒计时" + time / 1000 + "s");
    }

    @Override
    public void showTips(String tips) {
        Toast.makeText(mContext, tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
