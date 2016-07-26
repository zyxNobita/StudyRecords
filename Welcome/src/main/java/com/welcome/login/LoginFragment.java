package com.welcome.login;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

import static wechatedit.com.myapplication.R.id.cb_password_status;

/**
 * Created by ZQiang on 2016/7/25.
 */
public class LoginFragment extends Fragment {

    private boolean isQueckLogon = false; //默认普通登录
    private Context mContext;
    private View mView;
    private Button submit;
    private TimeCount mTimeCount = new TimeCount(5000, 1000);

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
        submit = (Button) mView.findViewById(cb_password_status);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeCount.start();
            }
        });

        if (isQueckLogon) {
            submit.setVisibility(View.VISIBLE);
        } else {
            submit.setVisibility(View.GONE);
        }

        return mView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            submit.setText("重新获取");
            submit.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            submit.setClickable(false);//防止重复点击
            submit.setText("倒计时"+millisUntilFinished / 1000 + "s");
        }
    }
}
