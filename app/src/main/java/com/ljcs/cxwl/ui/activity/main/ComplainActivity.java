package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerComplainComponent;
import com.ljcs.cxwl.ui.activity.main.contract.ComplainContract;
import com.ljcs.cxwl.ui.activity.main.module.ComplainModule;
import com.ljcs.cxwl.ui.activity.main.presenter.ComplainPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/09 10:58:14
 */

public class ComplainActivity extends BaseActivity implements ComplainContract.View {

    @Inject
    ComplainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("资格审查修改申诉");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerComplainComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .complainModule(new ComplainModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(ComplainContract.ComplainContractPresenter presenter) {
        mPresenter = (ComplainPresenter) presenter;
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