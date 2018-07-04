package com.ljcs.cxwl.ui.activity.certification;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationStatusInfoComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationStatusInfoContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationStatusInfoModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationStatusInfoPresenter;
import com.vondear.rxtools.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: $description
 * @date 2018/07/03 21:25:58
 */

public class CertificationStatusInfoActivity extends BaseActivity implements CertificationStatusInfoContract.View {

    @Inject
    CertificationStatusInfoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_certification_status_info);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.cerInfoDetail(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationStatusInfoComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).certificationStatusInfoModule(new CertificationStatusInfoModule(this))
                .build().inject(this);
    }

    @Override
    public void setPresenter(CertificationStatusInfoContract.CertificationStatusInfoContractPresenter presenter) {
        mPresenter = (CertificationStatusInfoPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void cerInfoDetailSuccess(CerInfo baseEntity) {

    }

}