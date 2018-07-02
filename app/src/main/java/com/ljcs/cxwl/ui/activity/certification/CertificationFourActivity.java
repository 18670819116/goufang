package com.ljcs.cxwl.ui.activity.certification;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationFourComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFourContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationFourModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationFourPresenter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: $description
 * @date 2018/06/26 19:28:19
 */

public class CertificationFourActivity extends BaseActivity implements CertificationFourContract.View {

    @Inject
    CertificationFourPresenter mPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_issueAuthority)
    EditText tvIssueAuthority;
    @BindView(R.id.tv_data1)
    EditText tvData1;
    @BindView(R.id.tv_data2)
    EditText tvData2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_certification_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");
    }

    @Override
    protected void initData() {
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority());
        tvData1.setText(Contains.sCertificationInfo.getSignDate());
        tvData2.setText(Contains.sCertificationInfo.getExpiryDate());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan()));

    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationFourComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).certificationFourModule(new CertificationFourModule(this)).build().inject
                (this);
    }

    @Override
    public void setPresenter(CertificationFourContract.CertificationFourContractPresenter presenter) {
        mPresenter = (CertificationFourPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.back, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.next:
                Contains.sCertificationInfo.setSignDate(tvData1.getText().toString().trim());
                Contains.sCertificationInfo.setExpiryDate(tvData2.getText().toString().trim());
                Contains.sCertificationInfo.setIssueAuthority(tvIssueAuthority.getText().toString().trim());
                startActivty(CertificationFiveActivity.class);
                break;
            default:
                break;
        }
    }
}