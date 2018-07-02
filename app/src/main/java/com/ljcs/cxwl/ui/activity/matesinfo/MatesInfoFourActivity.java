package com.ljcs.cxwl.ui.activity.matesinfo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoFourComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoFourContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoFourModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoFourPresenter;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwoActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/27 16:35:11
 */

public class MatesInfoFourActivity extends BaseActivity implements MatesInfoFourContract.View {

    @Inject
    MatesInfoFourPresenter mPresenter;
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
        setContentView(R.layout.activity_matesinfo_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
    }

    @Override
    protected void initData() {
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority_peiou());
        tvData1.setText(Contains.sCertificationInfo.getSignDate_peiou());
        tvData2.setText(Contains.sCertificationInfo.getExpiryDate_peiou());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan_peiou()));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMatesInfoFourComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .matesInfoFourModule(new MatesInfoFourModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MatesInfoFourContract.MatesInfoFourContractPresenter presenter) {
        mPresenter = (MatesInfoFourPresenter) presenter;
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
                Contains.sCertificationInfo.setIssueAuthority_peiou(tvIssueAuthority.getText().toString().trim());
                Contains.sCertificationInfo.setSignDate_peiou(tvData1.getText().toString().trim());
                Contains.sCertificationInfo.setExpiryDate_peiou(tvData2.getText().toString().trim());
                startActivty(FamilyRegisterTwoActivity.class);
                finish();

                break;
            default:
                break;
        }
    }

}