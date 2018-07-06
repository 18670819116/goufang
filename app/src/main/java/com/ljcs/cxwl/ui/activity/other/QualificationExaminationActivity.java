package com.ljcs.cxwl.ui.activity.other;

import android.os.Bundle;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.ui.activity.main.LoginActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerQualificationExaminationComponent;
import com.ljcs.cxwl.ui.activity.other.contract.QualificationExaminationContract;
import com.ljcs.cxwl.ui.activity.other.module.QualificationExaminationModule;
import com.ljcs.cxwl.ui.activity.other.presenter.QualificationExaminationPresenter;
import com.vondear.rxtools.RxActivityTool;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/06/27 16:49:13
 */

public class QualificationExaminationActivity extends BaseActivity implements QualificationExaminationContract.View {

    @Inject
    QualificationExaminationPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_qualification_examination);
        ButterKnife.bind(this);
        toolbarTitle.setText("购房资格审查说明");
        Contains.ENTERTYPE = 2;
        if (Contains.sAllInfo.getData().getHjxx() != null && Contains.sAllInfo.getData().getHjxx().getZt().equals
                ("5") && Contains.sAllInfo.getData().getPoxx() != null) {
            Contains.sCertificationInfo.setName_peiou(Contains.sAllInfo.getData().getPoxx().getXm());
            Contains.sCertificationInfo.setAddress_peiou(Contains.sAllInfo.getData().getPoxx().getDz());
            Contains.sCertificationInfo.setIdcard_peiou(Contains.sAllInfo.getData().getPoxx().getSfzhm());
            Contains.sCertificationInfo.setBirthday_peiou(Contains.sAllInfo.getData().getPoxx().getCsrq());
            Contains.sCertificationInfo.setEthnic_peiou(Contains.sAllInfo.getData().getPoxx().getMz());
            Contains.sCertificationInfo.setSex_peiou(Contains.sAllInfo.getData().getPoxx().getXb());
            if (Contains.sAllInfo.getData().getPoxx().getYxq().contains("-")) {
                Contains.sCertificationInfo.setSignDate_peiou(Contains.sAllInfo.getData().getPoxx().getYxq().split
                        ("-")[0]);
                Contains.sCertificationInfo.setExpiryDate_peiou(Contains.sAllInfo.getData().getPoxx().getYxq().split
                        ("-")[1]);
            }
            Contains.sCertificationInfo.setIssueAuthority_peiou(Contains.sAllInfo.getData().getPoxx().getQfjg());
            Contains.sCertificationInfo.setPic_path_zheng_peiou(API.PIC + Contains.sAllInfo.getData().getPoxx()
                    .getSfzzm());
            Contains.sCertificationInfo.setPic_path_fan_peiou(API.PIC + Contains.sAllInfo.getData().getPoxx()
                    .getSfzfm());
            Contains.ENTERTYPE_CHANGE = 1;

        } else {

            Contains.ENTERTYPE_CHANGE = 2;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerQualificationExaminationComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).qualificationExaminationModule(new QualificationExaminationModule(this))
                .build().inject(this);
    }

    @Override
    public void setPresenter(QualificationExaminationContract.QualificationExaminationContractPresenter presenter) {
        mPresenter = (QualificationExaminationPresenter) presenter;
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