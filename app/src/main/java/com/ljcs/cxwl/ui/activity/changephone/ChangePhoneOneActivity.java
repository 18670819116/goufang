package com.ljcs.cxwl.ui.activity.changephone;

import android.os.Bundle;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.ui.activity.changephone.component.DaggerChangePhoneOneComponent;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneOneContract;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneOneModule;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneOnePresenter;
import com.ljcs.cxwl.util.StringUitl;
import com.vondear.rxtool.RxSPTool;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: $description
 * @date 2018/07/09 16:38:08
 */

public class ChangePhoneOneActivity extends BaseActivity implements ChangePhoneOneContract.View {

    @Inject ChangePhoneOnePresenter mPresenter;
    @BindView(R.id.tv_phone) TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone_one);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("更换手机号码");
    }

    @Override
    protected void initData() {
        tvPhone.setText(StringUitl.settingphone(RxSPTool.getString(this, ShareStatic.APP_LOGIN_SJHM)));

    }

    @Override
    protected void setupActivityComponent() {
        DaggerChangePhoneOneComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent()
        ).changePhoneOneModule(new ChangePhoneOneModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ChangePhoneOneContract.ChangePhoneOneContractPresenter presenter) {
        mPresenter = (ChangePhoneOnePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        startActivty(ChangePhoneTwoActivity.class);
    }
}