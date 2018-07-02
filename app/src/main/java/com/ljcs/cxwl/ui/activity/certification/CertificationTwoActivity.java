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
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationTwoComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationTwoContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationTwoModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationTwoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: $description
 * @date 2018/06/26 19:26:58
 */

public class CertificationTwoActivity extends BaseActivity implements CertificationTwoContract.View {

    @Inject
    CertificationTwoPresenter mPresenter;
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
        setContentView(R.layout.activity_certification_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");


    }

    @Override
    protected void initData() {
        tvName.setText(Contains.sCertificationInfo.getName());
        tvSex.setText(Contains.sCertificationInfo.getSex());
        tvEthnic.setText(Contains.sCertificationInfo.getEthnic());
        tvBirthday.setText(Contains.sCertificationInfo.getBirthday());
        tvAdress.setText(Contains.sCertificationInfo.getAddress());
        tvIdcard.setText(Contains.sCertificationInfo.getIdcard());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_zheng()));

    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationTwoComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent
                ()).certificationTwoModule(new CertificationTwoModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(CertificationTwoContract.CertificationTwoContractPresenter presenter) {
        mPresenter = (CertificationTwoPresenter) presenter;
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
                Contains.sCertificationInfo.setName(tvName.getText().toString());
                Contains.sCertificationInfo.setAddress(tvAdress.getText().toString());
                Contains.sCertificationInfo.setIdcard(tvIdcard.getText().toString());
                Contains.sCertificationInfo.setBirthday(tvBirthday.getText().toString());
                Contains.sCertificationInfo.setEthnic(tvEthnic.getText().toString());
                Contains.sCertificationInfo.setSex(tvSex.getText().toString());
                startActivty(CertificationThirdActivity.class);
                break;
            default:
                break;
        }
    }
}