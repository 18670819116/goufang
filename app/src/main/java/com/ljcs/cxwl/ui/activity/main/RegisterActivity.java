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
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.AgreementActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerRegisterComponent;
import com.ljcs.cxwl.ui.activity.main.contract.RegisterContract;
import com.ljcs.cxwl.ui.activity.main.module.RegisterModule;
import com.ljcs.cxwl.ui.activity.main.presenter.RegisterPresenter;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxConstTool;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

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
                mTvGetYzm.setText("倒计时" + millisUntilFinished / 1000 + "s");
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

//                    mEt3.setInputType(TYPE_CLASS_TEXT);
                    mEt3.setSelection(mEt3.getText().length());
                } else {
                    mEt3.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    mEt3.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
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
        if (baseEntity.getCode() == 200) {
            if (baseEntity.getData() != null) {
                code = baseEntity.getData();
            } else {
                onErrorMsg(0, baseEntity.getMsg());
            }

        }

    }

    @Override
    public void register(RegisterBean baseEntity) {
        if (baseEntity.getCode() == 200) {
            if (baseEntity.token != null) {
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_TOKEN, baseEntity.token);
            }
            if (baseEntity.getData() != null) {
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_SJHM, baseEntity.getData().getSjhm());
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_MM, baseEntity.getData().getMm());
                RxSPTool.putString(this, ShareStatic.APP_LOGIN_ZT, baseEntity.getData().getZt());
                RxSPTool.putInt(this, ShareStatic.APP_LOGIN_BH, baseEntity.getData().getBh());
                if (baseEntity.msg != null) {
                    ToastUtil.showCenterShort(baseEntity.msg);
                }
                startActivty(MainActivity.class);
                finish();
            }
        } else {
            onErrorMsg(0, baseEntity.getMsg());
        }

    }


    @OnClick({R.id.tv_get_yzm, R.id.btn_register, R.id.tv_xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_yzm:
                if (RxDataTool.isNullString(mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不能为空");
                    return;
                }
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_SIMPLE, mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不正确");
                    return;
                }
                mTvGetYzm.setEnabled(false);
                mTvGetYzm.setTextColor(getResources().getColor(R.color.color_939393));
                countDownTimer.start();
                mPresenter.getCode(mEt1.getText().toString().trim());
                break;
            case R.id.btn_register:
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_SIMPLE, mEt1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不正确");
                    return;
                }
                if (mEt2.getText().toString().length() != 6) {
                    ToastUtil.showCenterShort("验证码至少6位");
                    return;
                }
                if (mEt3.getText().toString().length() < 6||mEt3.getText().toString().length() > 16) {
                    ToastUtil.showCenterShort("密码长度应为6-16位字符");
                    return;
                }

                if (RxDataTool.isNullString(code) || !(mEt2.getText().toString().trim().equals(code))) {
                    ToastUtil.showCenterShort("验证码错误");
                    return;
                }
                Map<String, String> map = new HashMap<>();

                map.put("sjhm", mEt1.getText().toString().trim());
                map.put("mm", mEt3.getText().toString().trim());
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