package com.ljcs.cxwl.ui.activity.other;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyAddNowComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyAddNowContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyAddNowModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyAddNowPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/07/03 11:09:00
 */

public class FamilyAddNowActivity extends BaseActivity implements FamilyAddNowContract.View {

    @Inject
    FamilyAddNowPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_add_now);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加家庭成员");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyAddNowComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .familyAddNowModule(new FamilyAddNowModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(FamilyAddNowContract.FamilyAddNowContractPresenter presenter) {
        mPresenter = (FamilyAddNowPresenter) presenter;
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
        startActivty(FamilyRegisterActivity.class);
    }
}