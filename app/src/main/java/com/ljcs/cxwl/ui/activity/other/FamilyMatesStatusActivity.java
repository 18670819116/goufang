package com.ljcs.cxwl.ui.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoOneActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoTwoActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyMatesStatusComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyMatesStatusContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyMatesStatusModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyMatesStatusPresenter;
import com.ljcs.cxwl.view.ZinvInfoLayout;
import com.vondear.rxtools.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/07/05 13:56:02
 */

public class FamilyMatesStatusActivity extends BaseActivity implements FamilyMatesStatusContract.View {

    @Inject
    FamilyMatesStatusPresenter mPresenter;
    @BindView(R.id.ic_family_status)
    ImageView icFamilyStatus;
    @BindView(R.id.tv_head_stutus1)
    TextView tvHeadStutus1;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_sex2)
    TextView tvSex2;
    @BindView(R.id.tv_hklx2)
    TextView tvHklx2;
    @BindView(R.id.tv_hkxz2)
    TextView tvHkxz2;
    @BindView(R.id.tv_hyzk2)
    TextView tvHyzk2;
    @BindView(R.id.tv_card2)
    TextView tvCard2;
    @BindView(R.id.tv_gx)
    TextView tvGx;
    @BindView(R.id.img1_peiou)
    ImageView img1Peiou;
    @BindView(R.id.img2_peiou)
    ImageView img2Peiou;
    @BindView(R.id.img3_peiou)
    ImageView img3Peiou;
    @BindView(R.id.img4_peiou)
    ImageView img4Peiou;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.layout_zinv_content)
    LinearLayout layoutZinvContent;
    @BindView(R.id.tv_change_peiou)
    TextView tvChangePeiou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_mates_status);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("家庭成员");
        Contains.ENTERTYPE = 1;
    }

    @Override
    protected void initData() {

    }
    @Override
    public void intiViews() {
        if (Contains.sAllInfo.getData().getHjxx() != null) {
            if (Contains.sAllInfo.getData().getHjxx().getZt().equals("2")) {
                //审核中
                Glide.with(this).load(R.mipmap.ic_mates_status2).into(icFamilyStatus);
                tvHeadStutus1.setText("家庭成员信息已保存");
                tvHeadStutus1.setTextColor(getResources().getColor(R.color.color_0ebc90));
            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("3")) {
                //审核中
                Glide.with(this).load(R.mipmap.ic_mates_status1).into(icFamilyStatus);
                tvHeadStutus1.setText("家庭成员信息已保存");
                tvHeadStutus1.setTextColor(getResources().getColor(R.color.main_color));

            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("4")) {
                Glide.with(this).load(R.mipmap.ic_mates_status1).into(icFamilyStatus);
                tvHeadStutus1.setText("家庭成员信息已保存");
                tvHeadStutus1.setTextColor(getResources().getColor(R.color.main_color));
            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("5")) {
                Glide.with(this).load(R.mipmap.ic_mates_status1).into(icFamilyStatus);
                tvHeadStutus1.setText("家庭成员信息已添加");
                tvHeadStutus1.setTextColor(getResources().getColor(R.color.main_color));
            }
        }

        if (Contains.sAllInfo.getData().getPoxx() != null) {
            tvName2.setText(Contains.sAllInfo.getData().getPoxx().getXm());
            tvSex2.setText(Contains.sAllInfo.getData().getPoxx().getXb());
            tvCard2.setText(Contains.sAllInfo.getData().getPoxx().getSfzhm());
            tvHklx2.setText(Contains.sAllInfo.getData().getPoxx().getHklx());
            tvHkxz2.setText(Contains.sAllInfo.getData().getPoxx().getHkxz());
            tvHyzk2.setText(Contains.sAllInfo.getData().getPoxx().getHyzt());
            tvGx.setText(Contains.sAllInfo.getData().getPoxx().getGx());
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getSfzzm()).into(img1Peiou);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getSfzfm()).into(img2Peiou);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getHkzp()).into(img3Peiou);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp()).into(img4Peiou);
            img1Peiou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FamilyMatesStatusActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getPoxx().getSfzzm());
                    startActivity(intent);
                }
            });
            img2Peiou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FamilyMatesStatusActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getPoxx().getSfzfm());
                    startActivity(intent);
                }
            });
            img3Peiou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FamilyMatesStatusActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getPoxx().getHkzp());
                    startActivity(intent);
                }
            });
            img4Peiou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FamilyMatesStatusActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp());
                    startActivity(intent);
                }
            });
            if (Contains.sAllInfo.getData().getHjxx().getZt().equals("5")) {
                tvChangePeiou.setVisibility(View.VISIBLE);
                tvChangePeiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Contains.sCertificationInfo.setName_peiou(Contains.sAllInfo.getData().getPoxx().getXm());
                        Contains.sCertificationInfo.setAddress_peiou(Contains.sAllInfo.getData().getPoxx().getDz());
                        Contains.sCertificationInfo.setIdcard_peiou(Contains.sAllInfo.getData().getPoxx().getSfzhm());
                        Contains.sCertificationInfo.setBirthday_peiou(Contains.sAllInfo.getData().getPoxx().getCsrq());
                        Contains.sCertificationInfo.setEthnic_peiou(Contains.sAllInfo.getData().getPoxx().getMz());
                        Contains.sCertificationInfo.setSex_peiou(Contains.sAllInfo.getData().getPoxx().getXb());
                        if (Contains.sAllInfo.getData().getPoxx().getYxq().contains("-")){
                            Contains.sCertificationInfo.setSignDate_peiou( Contains.sAllInfo.getData().getPoxx().getYxq().split("-")[0]);
                            Contains.sCertificationInfo.setExpiryDate_peiou( Contains.sAllInfo.getData().getPoxx().getYxq().split("-")[1]);
                        }
                        Contains.sCertificationInfo.setIssueAuthority_peiou(Contains.sAllInfo.getData().getPoxx().getQfjg());
                        Contains.sCertificationInfo.setPic_path_zheng_peiou(API.PIC+Contains.sAllInfo.getData().getPoxx().getSfzzm());
                        Contains.sCertificationInfo.setPic_path_fan_peiou(API.PIC+Contains.sAllInfo.getData().getPoxx().getSfzfm());
                        Contains.ENTERTYPE_CHANGE=1;
                        startActivty(MatesInfoTwoActivity.class);
                    }
                });
            }
        } else {
            layout3.setVisibility(View.GONE);
        }
        layoutZinvContent.removeAllViews();
        if (Contains.sAllInfo.getData().getJtcyList() != null && Contains.sAllInfo.getData().getJtcyList().size() > 0) {

            for (int i = 0; i < Contains.sAllInfo.getData().getJtcyList().size(); i++) {
                ZinvInfoLayout zinvInfoLayout = new ZinvInfoLayout(this, Contains.sAllInfo.getData().getJtcyList()
                        .get(i));
                final int finalI = i;
                if (Contains.sAllInfo.getData().getHjxx().getZt().equals("5")) {
                    zinvInfoLayout.getTv_change().setVisibility(View.VISIBLE);
                    zinvInfoLayout.getTv_change().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(FamilyMatesStatusActivity.this, FamilyAddActivity.class);
                            intent.putExtra("type", 2);
                            intent.putExtra("position", finalI);
                            startActivityForResult(intent, 101);
                        }
                    });
                }
                zinvInfoLayout.getImg1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FamilyMatesStatusActivity.this, ShowImgActivity.class);
                        intent.putExtra("img_path", API.PIC + Contains.sAllInfo.getData().getJtcyList().get(finalI)
                                .getHkzp());
                        startActivity(intent);
                    }
                });
                layoutZinvContent.addView(zinvInfoLayout);
            }

        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyMatesStatusComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyMatesStatusModule(new FamilyMatesStatusModule(this)).build().inject
                (this);
    }

    @Override
    public void setPresenter(FamilyMatesStatusContract.FamilyMatesStatusContractPresenter presenter) {
        mPresenter = (FamilyMatesStatusPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            //购房资格申请
            Contains.sAllInfo = baseEntity;

            intiViews();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 101) {
            //loadData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.allInfo(map1);

    }

}