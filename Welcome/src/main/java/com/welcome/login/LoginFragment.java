package com.welcome.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

/**
 * Created by ZQiang on 2016/7/25.
 */
public class LoginFragment extends Fragment {

    @Bind(R.id.loginBtn)
    Button mLoginBtn;
    private boolean isQueckLogon = false; //默认普通登录
    private Context mContext;
    private View mView;

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
//        ButterKnife.bind(this, mView);
        mLoginBtn = (Button) mView.findViewById(R.id.loginBtn);

        if (isQueckLogon){
            mLoginBtn.setText("Login true");
        }else{
            mLoginBtn.setText("Login false");
        }

        return mView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
