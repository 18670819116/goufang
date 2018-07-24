package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.AgreementActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerRegisterComponent;
import com.ljcs.cxwl.ui.activity.main.contract.RegisterContract;
import com.ljcs.cxwl.ui.activity.main.module.RegisterModule;
import com.ljcs.cxwl.ui.activity.main.presenter.RegisterPresenter;
import com.ljcs.cxwl.util.PhoneUtils;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxConstTool;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxEncryptTool;
import com.vondear.rxtool.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: $description
 * @date 2018/06/26 14:58:24
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View, TextWatcher {

    @Inject
    RegisterPresenter mPresenter;
    @BindView(R.id.et1)
    EditText mEt1;
    @BindView(R.id.et2)
    EditText mEt2;
    @BindView(R.id.tv_get_yzm)
    TextView mTvGetYzm;
    @BindView(R.id.et3)
    EditText mEt3;
    @BindView(R.id.tv_xieyi)
    TextView mTvXieyi;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.checkbox_eye)
    CheckBox mCheckboxEye;


    private CountDownTimer countDownTimer;
    private String code = "";
    private String phone = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("新用户注册");
        mEt1.addTextChangedListener(this);
        mEt2.addTextChangedListener(this);
        mEt3.addTextChangedListener(this);
    }

    @Override
    protected void initData() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //  Logger.i("倒计时" + millisUntilFinished);
                mTvGetYzm.setText( millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                mTvGetYzm.setEnabled(true);
                mTvGetYzm.setText("获取验证码");
                mTvGetYzm.setTextColor(getResources().getColor(R.color.color_0f77ff));
            }
        };
        mCheckboxEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEt3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    mEt3.setSelection(mEt3.getText().length());
                } else {
                    mEt3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEt3.setSelection(mEt3.getText().length());
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRegisterComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .registerModule(new RegisterModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(RegisterContract.RegisterContractPresenter presenter) {
        mPresenter = (RegisterPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void getCode(CommonBean baseEntity) {
        if (baseEntity.getCode() == Contains.REQUEST_SUCCESS) {
            if (baseEntity.getData() != null) {
                code = baseEntity.getData();
                phone = mEt1.getText().toString();
                mTvGetYzm.setEnabled(false);
                mTvGetYzm.setTextColor(getResources().getColor(R.color.color_939393));
                countDownTimer.start();
            }

        } else {
            onErrorMsg(baseEntity.code, baseEntity.getMsg());
        }

    }

    @Override
    public void register(RegisterBean baseEntity) {
        if (baseEntity.getCode() == Contains.REQUEST_SUCCESS) {
            if (baseEntity.token != null) {
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_TOKEN, baseEntity.token);
            }
            if (baseEntity.getData() != null) {
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_SJHM, baseEntity.getData().getSjhm());
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_MM, mEt3.getText().toString());
                startActivty(MainActivity.class);
                finish();
            }
        } else {
            onErrorMsg(baseEntity.code, baseEntity.getMsg());
        }

    }


    @OnClick({R.id.tv_get_yzm, R.id.btn_register, R.id.tv_xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_yzm:
                if (RxDataTool.isNullString(mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机格式错误");
                    return;
                }
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_EXACT, mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机格式错误");
                    return;
                }

                mPresenter.getCode(mEt1.getText().toString().trim());
                break;
            case R.id.btn_register:
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_EXACT, mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不正确");
                    return;
                }
                if (mEt2.getText().toString().length() != 6) {
                    ToastUtil.showCenterShort("验证码至少6位");
                    return;
                }
                if (mEt3.getText().toString().length() < 6 || mEt3.getText().toString().length() > 16) {
                    ToastUtil.showCenterShort("密码长度应为6-16位字符");
                    return;
                }
                if (!RxDataTool.isNullString(phone) && !phone.equals(mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码改变请重新获取验证码");
                    return;
                }
                if (RxDataTool.isNullString(code) || !(mEt2.getText().toString().trim().equals(code))) {
                    ToastUtil.showCenterShort("验证码错误");
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("sjxh", PhoneUtils.getDeviceBrand() + PhoneUtils.getSystemModel());//手机型号
                map.put("czxtbb", PhoneUtils.getSystemVersion());//操作系统版本
                map.put("scwl", PhoneUtils.getCurrentNetType(this));//所处网络
                map.put("jzxx", PhoneUtils.getjizhaninfo(this));//基站信息
                map.put("uuid", PhoneUtils.getDeviceId(this));//手机唯一标识
                map.put("yys", PhoneUtils.getOperators(this));//移动运营商
                map.put("gps", PhoneUtils.getLngAndLat(this));//GPS
                Logger.i( RxEncryptTool.encryptSHA1ToString(mEt3.getText().toString().trim()+mEt1.getText().toString().trim()));
                map.put("yhsjhm", mEt1.getText().toString().trim());
                map.put("sjhm", mEt1.getText().toString().trim());
                map.put("yhmm", RxEncryptTool.encryptSHA1ToString(mEt3.getText().toString().trim()+mEt1.getText().toString().trim()));
                mPresenter.register(map);
                break;
            case R.id.tv_xieyi:
                //ToastUtil.showCenterShort("跳转至协议");
                startActivty(AgreementActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(mEt1.getText().toString()) || TextUtils.isEmpty(mEt2.getText().toString()) || TextUtils
                .isEmpty(mEt3.getText().toString())) {
            mBtnRegister.setEnabled(false);
        } else {
            mBtnRegister.setEnabled(true);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}