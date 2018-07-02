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
import com.ljcs.cxwl.ui.activity.certification.CertificationThirdActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoTwoComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoTwoContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoTwoModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoTwoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/27 16:33:03
 */

public class MatesInfoTwoActivity extends BaseActivity implements MatesInfoTwoContract.View {

    @Inject
    MatesInfoTwoPresenter mPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_sex)
    EditText tvSex;
    @BindView(R.id.tv_ethnic)
    EditText tvEthnic;
    @BindView(R.id.tv_birthday)
    EditText tvBirthday;
    @BindView(R.id.tv_adress)
    EditText tvAdress;
    @BindView(R.id.tv_idcard)
    EditText tvIdcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matesinfo_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
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
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMatesInfoTwoComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .matesInfoTwoModule(new MatesInfoTwoModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MatesInfoTwoContract.MatesInfoTwoContractPresenter presenter) {
        mPresenter = (MatesInfoTwoPresenter) presenter;
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
                Contains.sCertificationInfo.setName_peiou(tvName.getText().toString());
                Contains.sCertificationInfo.setAddress_peiou(tvAdress.getText().toString());
                Contains.sCertificationInfo.setIdcard_peiou(tvIdcard.getText().toString());
                Contains.sCertificationInfo.setBirthday_peiou(tvBirthday.getText().toString());
                Contains.sCertificationInfo.setEthnic_peiou(tvEthnic.getText().toString());
                Contains.sCertificationInfo.setSex_peiou(tvSex.getText().toString());
                startActivty(MatesInfoThirdActivity.class);
                break;
            default:
                break;
        }
    }
}