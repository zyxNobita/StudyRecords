package com.module.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.BaseActivity;
import com.module.register.impl.RegisterManager;
import com.module.register.interfaces.IRegisterUIAction;

import butterknife.Bind;
import butterknife.ButterKnife;
import wechatedit.com.myapplication.R;

public class RegisterActivity extends BaseActivity implements IRegisterUIAction {

    @Bind(R.id.registerBackImg)
    ImageView registerBackImg;
    @Bind(R.id.registerPhoneNumberET)
    EditText registerPhoneNumberET;
    @Bind(R.id.registerPasswordET)
    EditText registerPasswordET;
    @Bind(R.id.registerCode)
    EditText registerCode;
    @Bind(R.id.registerSendSMSBtn)
    TextView registerSendSMSBtn;
    @Bind(R.id.registerBtn)
    Button registerBtn;
    @Bind(R.id.registerAgreement)
    TextView registerAgreement;

    private RegisterManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mManager = new RegisterManager(this);
        setOnClick();
    }

    private void setOnClick() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mManager.register(registerPhoneNumberET.getText().toString(),
                        registerPasswordET.getText().toString(), registerCode.getText().toString());
            }
        });

        registerSendSMSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mManager.sendSMS(registerPhoneNumberET.getText().toString());
            }
        });

        registerAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 跳转服务协议web页
            }
        });

    }

    @Override
    public void showLoding() {
        showLoadingDialog();
    }

    @Override
    public void disLoading() {
        showLoadingDialog();
    }

    @Override
    public void showTips(String warring) {
        showWarring(warring);
    }

}
