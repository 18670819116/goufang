package com.ljcs.cxwl.ui.activity.matesinfo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoFiveComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoFiveContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoFiveModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoFivePresenter;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwoActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/28 12:16:34
 */

public class MatesInfoFiveActivity extends BaseActivity implements MatesInfoFiveContract.View {

    @Inject MatesInfoFivePresenter mPresenter;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_sex) TextView tvSex;
    @BindView(R.id.tv_ethnic) TextView tvEthnic;
    @BindView(R.id.tv_birthday) TextView tvBirthday;
    @BindView(R.id.tv_adress) TextView tvAdress;
    @BindView(R.id.tv_idcard) TextView tvIdcard;
    @BindView(R.id.tv_issueAuthority) TextView tvIssueAuthority;
    @BindView(R.id.tv_data) TextView tvData;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.imageView1) ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matesinfo_five);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("提交配偶信息");
    }

    @Override
    protected void initData() {
        tvName.setText(Contains.sCertificationInfo.getName_peiou());
        tvSex.setText(Contains.sCertificationInfo.getSex_peiou());
        tvEthnic.setText(Contains.sCertificationInfo.getEthnic_peiou());
        tvBirthday.setText(Contains.sCertificationInfo.getBirthday_peiou());
        tvAdress.setText(Contains.sCertificationInfo.getAddress_peiou());
        tvIdcard.setText(Contains.sCertificationInfo.getIdcard_peiou());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_zheng_peiou()));
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority_peiou());
        tvData.setText(Contains.sCertificationInfo.getSignDate_peiou() + "-" + Contains.sCertificationInfo
                .getExpiryDate_peiou());
        imageView1.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan_peiou()));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMatesInfoFiveComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .matesInfoFiveModule(new MatesInfoFiveModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MatesInfoFiveContract.MatesInfoFiveContractPresenter presenter) {
        mPresenter = (MatesInfoFivePresenter) presenter;
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
//                final CertificationDialog dialog = new CertificationDialog(CertificationFiveActivity.this);
//                if ("男".equals(Contains.sCertificationInfo.getSex())) {
//                    dialog.getImageView().setImageResource(R.drawable.ic_boy);
//                } else {
//                    dialog.getImageView().setImageResource(R.drawable.ic_girl);
//
//                }
//                dialog.getBtn().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
                startActivty(FamilyRegisterTwoActivity.class);
                break;
            default:
                break;
        }
    }
}