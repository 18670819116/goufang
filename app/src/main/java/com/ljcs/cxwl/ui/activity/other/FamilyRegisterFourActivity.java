package com.ljcs.cxwl.ui.activity.other;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterFourComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterFourContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterFourModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterFourPresenter;
import com.ljcs.cxwl.util.AppManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/06/27 20:03:29
 */

public class FamilyRegisterFourActivity extends BaseActivity implements FamilyRegisterFourContract.View {

    @Inject
    FamilyRegisterFourPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("核对并提交信息");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterFourComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterFourModule(new FamilyRegisterFourModule(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterFourContract.FamilyRegisterFourContractPresenter presenter) {
        mPresenter = (FamilyRegisterFourPresenter) presenter;
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
        AppManager.getInstance().finishActivity(QualificationExaminationActivity.class);
        AppManager.getInstance().finishActivity(FamilyRegisterActivity.class);
        AppManager.getInstance().finishActivity(FamilyRegisterTwoActivity.class);
        AppManager.getInstance().finishActivity(FamilyRegisterThirdActivity.class);
        //startActivty(FamilyRegisterFourActivity.class);
        finish();
    }
}