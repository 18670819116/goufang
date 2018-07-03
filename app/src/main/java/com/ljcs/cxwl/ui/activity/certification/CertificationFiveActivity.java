package com.ljcs.cxwl.ui.activity.certification;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationFiveComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFiveContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationFiveModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationFivePresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.view.CertificationDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: $description
 * @date 2018/06/26 19:29:05
 */

public class CertificationFiveActivity extends BaseActivity implements CertificationFiveContract.View {

    @Inject
    CertificationFivePresenter mPresenter;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_ethnic)
    TextView tvEthnic;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.tv_idcard)
    TextView tvIdcard;
    @BindView(R.id.tv_issueAuthority)
    TextView tvIssueAuthority;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView1)
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_certification_five);
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
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority());
        tvData.setText(Contains.sCertificationInfo.getSignDate() + "-" +Contains.sCertificationInfo.getExpiryDate());
        imageView1.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan()));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationFiveComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).certificationFiveModule(new CertificationFiveModule(this)).build().inject
                (this);
    }

    @Override
    public void setPresenter(CertificationFiveContract.CertificationFiveContractPresenter presenter) {
        mPresenter = (CertificationFivePresenter) presenter;
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
                final CertificationDialog dialog = new CertificationDialog(CertificationFiveActivity.this);
                if ("男".equals(Contains.sCertificationInfo.getSex())) {
                    dialog.getImageView().setImageResource(R.drawable.ic_boy);
                } else {
                    dialog.getImageView().setImageResource(R.drawable.ic_girl);

                }
                dialog.getBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AppManager.getInstance().finishActivity(CertificationOneActivity.class);
                        AppManager.getInstance().finishActivity(CertificationTwoActivity.class);
                        AppManager.getInstance().finishActivity(CertificationThirdActivity.class);
                        AppManager.getInstance().finishActivity(CertificationFourActivity.class);
                        finish();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
}