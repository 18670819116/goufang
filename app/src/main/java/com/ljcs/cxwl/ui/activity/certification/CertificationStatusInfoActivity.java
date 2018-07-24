package com.ljcs.cxwl.ui.activity.certification;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationStatusInfoComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationStatusInfoContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationStatusInfoModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationStatusInfoPresenter;
import com.ljcs.cxwl.view.CerstatusDialog;

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
        //0-待审核，1,待人工审核，2-审核通过，3-审核未通过）
        if (Contains.sAllInfo.getData().getZcyh() != null && Contains.sAllInfo.getData().getZcyh().getRzzt() != null) {
            if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 0) {
                //审核中
                imgStatus1.setVisibility(View.GONE);
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("待审核...");
                Drawable nav_up = getResources().getDrawable(R.mipmap.ic_tvstatus1);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvStatus.setCompoundDrawables(nav_up, null, null, null);
                tvStatus.setCompoundDrawablePadding(10);

            } else if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 1) {
                //审核中
                imgStatus1.setVisibility(View.GONE);
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("待人工审核...");
                Drawable nav_up = getResources().getDrawable(R.mipmap.ic_tvstatus2);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvStatus.setCompoundDrawables(nav_up, null, null, null);
                tvStatus.setCompoundDrawablePadding(10);

            } else if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 2) {
                //通过
                imgStatus1.setImageResource(R.mipmap.ic_cer_status1);
                tvStatus.setText("审核通过");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2aba90));
                Drawable nav_up = getResources().getDrawable(R.mipmap.ic_tvstatus4);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvStatus.setCompoundDrawables(nav_up, null, null, null);
                tvStatus.setCompoundDrawablePadding(10);
            } else if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 3) {
                //未通过
                imgStatus1.setImageResource(R.mipmap.ic_cer_status11);
                tvStatus.setText("审核未通过");
                tvStatus.setTextColor(getResources().getColor(R.color.color_fa6268));
                tvStatus.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
                tvStatus.getPaint().setAntiAlias(true);//抗锯齿
                Drawable nav_up = getResources().getDrawable(R.mipmap.ic_tvstatus3);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvStatus.setCompoundDrawables(nav_up, null, null, null);
                tvStatus.setCompoundDrawablePadding(10);
                tvStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                });
            }
        }
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getGrxx() != null && Contains.sAllInfo
                .getData().getGrxx().getSfz() != null) {
            tvName.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getXm());
            tvSex.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getXb());
            tvAdress.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getZz());
            tvBirthday.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getCsrq());
            tvEthnic.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getMz());
            tvIdcard.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getZjhm());
            tvIssueAuthority.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getQfjg());
            tvData.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getYxq());
        }
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getGrxx() != null && Contains.sAllInfo
                .getData().getGrxx().getZzxx() != null) {
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getSfzzm()).into(imageView);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getSfzfm()).into
                    (imageView1);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CertificationStatusInfoActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getSfzzm());
                    startActivity(intent);
                }
            });
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CertificationStatusInfoActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getSfzfm());
                    startActivity(intent);
                }
            });
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