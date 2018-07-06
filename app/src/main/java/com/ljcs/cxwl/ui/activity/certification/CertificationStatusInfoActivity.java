package com.ljcs.cxwl.ui.activity.certification;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationStatusInfoComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationStatusInfoContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationStatusInfoModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationStatusInfoPresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.view.CerstatusDialog;
import com.ljcs.cxwl.view.CertificationDialog;
import com.vondear.rxtools.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.img_status2)
    ImageView imgStatus2;
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
    @BindView(R.id.img_status1)
    ImageView imgStatus1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_certification_status_info);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");
    }

    @Override
    protected void initData() {
//        Map<String, String> map = new HashMap<>();
//        map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
//        mPresenter.cerInfoDetail(map);
        //1保存2发起申请,审核中3审核通过4审核未通过
        if (Contains.sAllInfo.getData().getSmyz() != null && Contains.sAllInfo.getData().getSmyz() != null &&
                Contains.sAllInfo.getData().getSmyz().getZt().equals("2")) {
            //审核中
            imgStatus1.setVisibility(View.GONE);
            imgStatus2.setVisibility(View.GONE);
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("信息审核中");

        } else if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null && Contains
                .sAllInfo.getData().getSmyz().getZt().equals("3")) {
            imgStatus1.setVisibility(View.VISIBLE);
            imgStatus2.setVisibility(View.GONE);
            tvStatus.setVisibility(View.GONE);
        } else if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null && Contains
                .sAllInfo.getData().getSmyz().getZt().equals("4")) {
            imgStatus1.setVisibility(View.GONE);
            imgStatus2.setVisibility(View.VISIBLE);
            tvStatus.setVisibility(View.GONE);
            final CerstatusDialog dialog = new CerstatusDialog(CertificationStatusInfoActivity.this);
            dialog.setCancelable(false);
            dialog.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null) {
            tvName.setText(Contains.sAllInfo.getData().getSmyz().getXm());
            tvSex.setText(Contains.sAllInfo.getData().getSmyz().getXb());
            tvAdress.setText(Contains.sAllInfo.getData().getSmyz().getDz());
            tvBirthday.setText(Contains.sAllInfo.getData().getSmyz().getCsrq());
            tvEthnic.setText(Contains.sAllInfo.getData().getSmyz().getMz());
            tvIdcard.setText(Contains.sAllInfo.getData().getSmyz().getSfzhm());
            tvIssueAuthority.setText(Contains.sAllInfo.getData().getSmyz().getQfjg());
            tvData.setText(Contains.sAllInfo.getData().getSmyz().getYxq());
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getSmyz().getSfzzm()).into(imageView);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getSmyz().getSfzfm()).into(imageView1);
        }
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