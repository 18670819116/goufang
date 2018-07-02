package com.ljcs.cxwl.ui.activity.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AppLogin;
import com.ljcs.cxwl.ui.activity.certification.CertificationOneActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerLoginComponent;
import com.ljcs.cxwl.ui.activity.main.contract.LoginContract;
import com.ljcs.cxwl.ui.activity.main.module.LoginModule;
import com.ljcs.cxwl.ui.activity.main.presenter.LoginPresenter;
import com.ljcs.cxwl.ui.activity.other.QualificationExaminationActivity;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.vondear.rxtools.RxConstTool;
import com.vondear.rxtools.RxDataTool;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tyrantgit.explosionfield.ExplosionField;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.login
 * @Description: $description
 * @date 2017/10/17 08:38:03
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter mPresenter;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.login_tel)
    EditText mLoginTel;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.iv_show_password)
    CheckBox mIvShowPassword;

    private String mAccount;
    private String mPassWord;
    private ExplosionField explosionField;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mSharedPreferences = this.getSharedPreferences("Login_Sp", MODE_PRIVATE);
        //粒子爆炸效果
        explosionField = ExplosionField.attach2Window(LoginActivity.this);
        if (!"".equals(mSharedPreferences.getString(ShareStatic.APP_LOGIN_ACCOUNT, "")) && !"".equals
                (mSharedPreferences.getString(ShareStatic.APP_LOGIN_PASSWORD, ""))) {
            mLoginTel.setText(mSharedPreferences.getString(ShareStatic.APP_LOGIN_ACCOUNT, ""));
            mLoginPwd.setText(mSharedPreferences.getString(ShareStatic.APP_LOGIN_PASSWORD, ""));
            onViewClicked(mBtnLogin);
        }
        mIvShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLoginPwd.setInputType(TYPE_CLASS_TEXT);
                    mLoginPwd.setSelection(mLoginPwd.getText().length());
                } else {
                    mLoginPwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    mLoginPwd.setSelection(mLoginPwd.getText().length());
                }
            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerLoginComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(LoginContract.LoginContractPresenter presenter) {
        mPresenter = (LoginPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(AppLogin appLogin) {
        Contains.appLogin = appLogin;
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(ShareStatic.APP_LOGIN_ACCOUNT, mAccount);
        edit.putString(ShareStatic.APP_LOGIN_PASSWORD, mPassWord);
        edit.apply();
        explosionField.explode(mBtnLogin);


    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                startActivty(QualificationExaminationActivity.class);
//                startActivty(RegisterActivity.class);
                break;
            case R.id.tv_forget_pwd:
                startActivty(ForgetPwdActivity.class);
                break;
            case R.id.btn_login:
                mAccount = mLoginTel.getText().toString().trim();
                mPassWord = mLoginPwd.getText().toString().trim();
                if (RxDataTool.isNullString(mAccount) || RxDataTool.isNullString(mPassWord)) {
                    ToastUtil.showCenterShort("手机号码或密码不能为空");
                    return;
                }
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_SIMPLE, mAccount)) {
                    ToastUtil.showCenterShort("手机号码不正确");
                    return;
                }
                if (mPassWord.length() < 6) {
                    ToastUtil.showCenterShort("密码最少6位");
                    return;
                }
                if (mPassWord.length() > 16) {
                    ToastUtil.showCenterShort("密码最多16位");
                    return;
                }
                startActivty(CertificationOneActivity.class);

//                //queryShipperInfo();
//                StringBuilder deviceId = new StringBuilder();
//                // 渠道标志
//                deviceId.append("a");
//                try {
//                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//                    String imei = tm.getDeviceId();
//                    if (!hasEmptyItem(imei)) {
//                        deviceId.append("m");
//                        deviceId.append(imei);
//                    } else {
//                        imei = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings
//                                .Secure.ANDROID_ID);
//                        deviceId.append("m");
//                        deviceId.append(imei);
//                    }
//                    Logger.i("imei  --->" + imei);
//
//                    //序列号（sn）
//                    String sn = tm.getSimSerialNumber();
//                    if (!hasEmptyItem(sn)) {
//                        deviceId.append("s");
//                        deviceId.append(sn);
//                        Log.e("geek : ", "序列号（sn）=" + deviceId.toString());
//                    }
//
//                } catch (Exception e) {
//                    Log.d("geek", "getDeviceId: e");
//                    deviceId.append("e" + deviceId.toString());
//                }
//                Map<String, String> map = new HashMap<>(16);
//                map.put("userName", mAccount);
//                map.put("passWord", StringUitls.getMD5(mPassWord));
//                map.put("macAddr", deviceId.toString());
//                mPresenter.login(map);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}