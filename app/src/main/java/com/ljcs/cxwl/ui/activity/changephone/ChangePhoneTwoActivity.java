package com.ljcs.cxwl.ui.activity.changephone;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.changephone.component.DaggerChangePhoneTwoComponent;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneTwoContract;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneTwoModule;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneTwoPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: $description
 * @date 2018/07/09 16:39:17
 */

public class ChangePhoneTwoActivity extends BaseActivity implements ChangePhoneTwoContract.View {

    @Inject
    ChangePhoneTwoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerChangePhoneTwoComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .changePhoneTwoModule(new ChangePhoneTwoModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(ChangePhoneTwoContract.ChangePhoneTwoContractPresenter presenter) {
        mPresenter = (ChangePhoneTwoPresenter) presenter;
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