package com.studyrecords.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studyrecords.R;
import com.studyrecords.model.entity.User;
import com.studyrecords.presenter.LoginPresenter;
import com.studyrecords.presenter.impl.LoginPresenterImpl;
import com.studyrecords.view.ui.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String PROMPTKEY = "LoginActivity.PROMPTKEY";
    @Bind(R.id.loginName)
    EditText loginName;
    @Bind(R.id.loginPwd)
    EditText loginPwd;
    @Bind(R.id.loginBtn)
    Button loginBtn;
    private ProgressDialog mProgressDialog;
    private LoginPresenter mLoginPresenter;
    private User mUser;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USER",mUser);
                startActivity(intent);
                finish();
            }
            if (msg.getData().getString(PROMPTKEY)!= null) {
                Toast.makeText(LoginActivity.this, msg.getData().getString(PROMPTKEY), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenterImpl(this);
        mLoginPresenter.initView();
    }

    @Override
    public void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("正在登录,请稍等...");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.login(loginName.getText().toString(), loginPwd.getText().toString());
            }
        });
    }

    @Override
    public void promptParamNull(String string) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(PROMPTKEY, string);
        msg.setData(bundle);
        mHandler.handleMessage(msg);
    }

    @Override
    public void showProgressBar() {
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressBar() {
        mProgressDialog.dismiss();
    }

    @Override
    public void jumpNewActivity(User user) {
        mUser = user;
        Message msg = new Message();
        msg.what = 2;
        mHandler.handleMessage(msg);
    }
}
