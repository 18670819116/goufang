package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.ljcs.cxwl.ui.activity.main.component.DaggerChangePwdComponent;
import com.ljcs.cxwl.ui.activity.main.contract.ChangePwdContract;
import com.ljcs.cxwl.ui.activity.main.module.ChangePwdModule;
import com.ljcs.cxwl.ui.activity.main.presenter.ChangePwdPresenter;
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

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/03 13:37:53
 */

public class ChangePwdActivity extends BaseActivity implements ChangePwdContract.View, TextWatcher {

    @Inject
    ChangePwdPresenter mPresenter;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.checkbox_eye)
    CheckBox checkboxEye;
    @BindView(R.id.checkbox_eye1)
    CheckBox checkboxEye1;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("修改密码");
        et1.addTextChangedListener(this);
        et2.addTextChangedListener(this);
    }

    @Override
    protected void initData() {
        checkboxEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    et1.setInputType(TYPE_CLASS_TEXT);
                    et1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    et1.setSelection(et1.getText().length());
                } else {
                    et1.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    et1.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    et1.setSelection(et1.getText().length());
                }
            }
        });
        checkboxEye1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    et2.setInputType(TYPE_CLASS_TEXT);
                    et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    et2.setSelection(et1.getText().length());
                } else {
                    et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    et2.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    et2.setSelection(et1.getText().length());
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerChangePwdComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .changePwdModule(new ChangePwdModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ChangePwdContract.ChangePwdContractPresenter presenter) {
        mPresenter = (ChangePwdPresenter) presenter;
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
    public void changePwdSuccess(RegisterBean baseEntity) {
        if (baseEntity.getCode() == Contains.REQUEST_SUCCESS) {
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
                finish();
            }
        } else {
            onErrorMsg(0, baseEntity.getMsg());
        }
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            Logger.i("点击过快");
            return;
        }
        if (et1.getText().toString().length() < 6) {
            ToastUtil.showCenterShort("原密码最少6位");
            return;
        }
        if (et1.getText().toString().length() > 16) {
            ToastUtil.showCenterShort("密码最多16位");
            return;
        }
        if (et2.getText().toString().length() < 6) {
            ToastUtil.showCenterShort("新密码最少6位");
            return;
        }
        if (et2.getText().toString().length() > 16) {
            ToastUtil.showCenterShort("新密码最多16位");
            return;
        }
        if (!et1.getText().toString().trim().equals(RxSPTool.getString(this, ShareStatic.APP_LOGIN_MM))) {
            ToastUtil.showCenterShort("原密码验证失败");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("sjhm", RxSPTool.getString(this, ShareStatic.APP_LOGIN_SJHM));
        map.put("mm", RxSPTool.getString(this, ShareStatic.APP_LOGIN_MM));
        map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        map.put("newmm", et2.getText().toString().trim());
        mPresenter.changePwd(map);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(et1.getText().toString()) || TextUtils.isEmpty(et2.getText().toString())) {
            btnRegister.setEnabled(false);
        } else {
            btnRegister.setEnabled(true);
        }
    }
}