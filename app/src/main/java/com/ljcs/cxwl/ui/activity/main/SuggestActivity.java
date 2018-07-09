package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerSuggestComponent;
import com.ljcs.cxwl.ui.activity.main.contract.SuggestContract;
import com.ljcs.cxwl.ui.activity.main.module.SuggestModule;
import com.ljcs.cxwl.ui.activity.main.presenter.SuggestPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/06 15:46:33
 */

public class SuggestActivity extends BaseActivity implements SuggestContract.View {

    @Inject
    SuggestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("意见反馈");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerSuggestComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .suggestModule(new SuggestModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(SuggestContract.SuggestContractPresenter presenter) {
        mPresenter = (SuggestPresenter) presenter;
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