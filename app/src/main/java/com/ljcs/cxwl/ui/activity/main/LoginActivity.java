package com.ljcs.cxwl.ui.activity.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.main.component.DaggerLoginComponent;
import com.ljcs.cxwl.ui.activity.main.contract.LoginContract;
import com.ljcs.cxwl.ui.activity.main.module.LoginModule;
import com.ljcs.cxwl.ui.activity.main.presenter.LoginPresenter;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxConstTool;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.RxTool;

import java.util.HashMap;
import java.util.Map;

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


    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }

    @Override
    protected void initData() {
        mLoginTel.setText(RxSPTool.getString(this, ShareStatic.APP_LOGIN_SJHM));
        mLoginPwd.setText(RxSPTool.getString(this, ShareStatic.APP_LOGIN_MM));
        //设置密码可不可见
        mIvShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    mLoginPwd.setInputType(TYPE_CLASS_TEXT);
                    mLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mLoginPwd.setSelection(mLoginPwd.getText().length());
                } else {
                    mLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

//                    mLoginPwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    mLoginPwd.setSelection(mLoginPwd.getText().length());
                }
            }
        });
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
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.dismiss();
    }

    /**
     * 登录成功回调
     *
     * @param baseEntity
     */
    @Override
    public void loginSuccess(RegisterBean baseEntity) {
        if (baseEntity.getCode() == 200) {
            if (baseEntity.token != null) {
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_TOKEN, baseEntity.token);
            }
            if (baseEntity.getData() != null) {
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_SJHM, baseEntity.getData().getSjhm());
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_MM, baseEntity.getData().getMm());
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_ZT, baseEntity.getData().getZt());
                RxSPTool.putInt(this, ShareStatic.APP_LOGIN_BH, baseEntity.getData().getBh());
//                if (baseEntity.msg != null) {
//                    ToastUtil.showCenterShort(baseEntity.msg);
//                }
                ToastUtil.showCenterShort("登录成功");
                startActivty(MainActivity.class);
                finish();
            }
        } else {
            onErrorMsg(0, baseEntity.getMsg());
        }


    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
//                startActivty(QualificationExaminationActivity.class);
                startActivty(RegisterActivity.class);
                break;
            case R.id.tv_forget_pwd:
                startActivty(ForgetPwdActivity.class);
                break;
            case R.id.btn_login:
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    Logger.i("点击过快");
                    return;
                }
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
                Map<String, String> map = new HashMap<>();
                map.put("sjhm", mAccount);
                map.put("mm", mPassWord);
                mPresenter.login(map);
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