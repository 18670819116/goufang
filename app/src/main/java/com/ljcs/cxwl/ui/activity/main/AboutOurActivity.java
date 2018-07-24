package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.main.component.DaggerAboutOurComponent;
import com.ljcs.cxwl.ui.activity.main.contract.AboutOurContract;
import com.ljcs.cxwl.ui.activity.main.module.AboutOurModule;
import com.ljcs.cxwl.ui.activity.main.presenter.AboutOurPresenter;
import com.vondear.rxtool.RxDeviceTool;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/09 11:07:19
 */

public class AboutOurActivity extends BaseActivity implements AboutOurContract.View {

    @Inject
    AboutOurPresenter mPresenter;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_about_our);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("关于我们");
    }

    @Override
    protected void initData() {
        tvVersion.setText("欣居 V"+ RxDeviceTool.getAppVersionName(this));
        mPresenter.aboutMe();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAboutOurComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .aboutOurModule(new AboutOurModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(AboutOurContract.AboutOurContractPresenter presenter) {
        mPresenter = (AboutOurPresenter) presenter;
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
    public void aboutMeSuccess(CommonBean commonBean) {

    }

}