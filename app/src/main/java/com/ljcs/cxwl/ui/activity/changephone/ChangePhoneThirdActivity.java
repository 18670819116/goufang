package com.ljcs.cxwl.ui.activity.changephone;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.changephone.component.DaggerChangePhoneThirdComponent;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneThirdContract;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneThirdModule;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneThirdPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: $description
 * @date 2018/07/09 16:39:43
 */

public class ChangePhoneThirdActivity extends BaseActivity implements ChangePhoneThirdContract.View {

    @Inject
    ChangePhoneThirdPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone_third);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerChangePhoneThirdComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .changePhoneThirdModule(new ChangePhoneThirdModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(ChangePhoneThirdContract.ChangePhoneThirdContractPresenter presenter) {
        mPresenter = (ChangePhoneThirdPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}