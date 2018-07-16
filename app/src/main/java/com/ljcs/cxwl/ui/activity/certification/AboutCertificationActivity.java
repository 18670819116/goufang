package com.ljcs.cxwl.ui.activity.certification;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerAboutCertificationComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.AboutCertificationContract;
import com.ljcs.cxwl.ui.activity.certification.module.AboutCertificationModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.AboutCertificationPresenter;
import com.vondear.rxtool.RxTool;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: $description
 * @date 2018/07/10 10:26:14
 */

public class AboutCertificationActivity extends BaseActivity implements AboutCertificationContract.View {

    @Inject
    AboutCertificationPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront=true;
        setContentView(R.layout.activity_about_certification);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("关于实名认证");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerAboutCertificationComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).aboutCertificationModule(new AboutCertificationModule(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(AboutCertificationContract.AboutCertificationContractPresenter presenter) {
        mPresenter = (AboutCertificationPresenter) presenter;
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
        if (RxTool.isFastClick(Contains.FAST_CLICK)){
            return;
        }
        startActivty(CertificationActivity.class);
    }
}