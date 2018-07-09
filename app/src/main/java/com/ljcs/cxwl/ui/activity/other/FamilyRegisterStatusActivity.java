package com.ljcs.cxwl.ui.activity.other;

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
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterStatusComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterStatusContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterStatusModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterStatusPresenter;
import com.ljcs.cxwl.view.CertificationDialog;
import com.ljcs.cxwl.view.ZinvInfoLayout;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.RxTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/07/05 10:00:42
 */

public class FamilyRegisterStatusActivity extends BaseActivity implements FamilyRegisterStatusContract.View {

    @Inject
    FamilyRegisterStatusPresenter mPresenter;
    @BindView(R.id.layout_zinv_content)
    LinearLayout layoutZinvContent;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_sex1)
    TextView tvSex1;
    @BindView(R.id.tv_hklx1)
    TextView tvHklx1;
    @BindView(R.id.tv_hkxz1)
    TextView tvHkxz1;
    @BindView(R.id.tv_hyzk1)
    TextView tvHyzk1;
    @BindView(R.id.tv_card1)
    TextView tvCard1;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.layout1)
    LinearLayout layout1;
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
    @BindView(R.id.ic_family_status)
    ImageView icFamilyStatus;
    @BindView(R.id.tv_head_stutus1)
    TextView tvHeadStutus1;
    @BindView(R.id.tv_head_stutus2)
    TextView tvHeadStutus2;
    @BindView(R.id.tv_hjszd)
    TextView tvHjszd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_status);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("购房资格审查");
    }

    @Override
    protected void initData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.allInfo(map1);

    }

    @Override
    public void intiViews() {
        if (Contains.sAllInfo.getData().getHjxx() != null) {
            if (Contains.sAllInfo.getData().getHjxx().getZt().equals("2")) {
                //审核中
                Glide.with(this).load(R.mipmap.ic_family_status1).into(icFamilyStatus);
                tvHeadStutus1.setText("您的购房资格审查信息已提交\n正在审查中");
                tvHeadStutus2.setVisibility(View.GONE);
            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("3")) {
                //审核中
                Glide.with(this).load(R.mipmap.ic_family_status2).into(icFamilyStatus);
                tvHeadStutus1.setText("已审核通过");
                tvHeadStutus2.setText("查看审核反馈");

            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("4")) {
                Glide.with(this).load(R.mipmap.ic_family_status3).into(icFamilyStatus);
                tvHeadStutus1.setText("审核未通过！");
                tvHeadStutus2.setText("查看原因");
            }
        }
        tvName1.setText(Contains.sAllInfo.getData().getSmyz().getXm());
        tvSex1.setText(Contains.sAllInfo.getData().getSmyz().getXb());
        tvCard1.setText(Contains.sAllInfo.getData().getSmyz().getSfzhm());
        tvHklx1.setText(Contains.sAllInfo.getData().getHjxx().getHklx());
        tvHkxz1.setText(Contains.sAllInfo.getData().getHjxx().getHkxz());
        tvHyzk1.setText(Contains.sAllInfo.getData().getHjxx().getHyzt());
        tvHjszd.setText(Contains.sAllInfo.getData().getHjxx().getHjszd());
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getSmyz().getSfzzm()).into(img1);
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getSmyz().getSfzfm()).into(img2);
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getHjxx().getHkzp()).into(img3);
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getHjxx().getJhzzp()).into(img4);
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

        } else {
            layout3.setVisibility(View.GONE);
        }
        layoutZinvContent.removeAllViews();
        if (Contains.sAllInfo.getData().getJtcyList() != null && Contains.sAllInfo.getData().getJtcyList().size() > 0) {
            for (int i = 0; i < Contains.sAllInfo.getData().getJtcyList().size(); i++) {
                ZinvInfoLayout zinvInfoLayout = new ZinvInfoLayout(this, Contains.sAllInfo.getData().getJtcyList()
                        .get(i));
                layoutZinvContent.addView(zinvInfoLayout);
            }

        }
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
    protected void setupActivityComponent() {
        DaggerFamilyRegisterStatusComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterStatusModule(new FamilyRegisterStatusModule(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterStatusContract.FamilyRegisterStatusContractPresenter presenter) {
        mPresenter = (FamilyRegisterStatusPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.tv_head_stutus2)
    public void onViewClicked() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return;

        }
        final CertificationDialog dialog = new CertificationDialog(this);
        dialog.setCancelable(false);
        ImageView imageView = dialog.getImageView();
        TextView tv1 = dialog.getTv1();

        tv1.setTextSize(14);
        TextView tv2 = dialog.getTv2();
        tv2.setTextSize(14);

        if (Contains.sAllInfo.getData().getHjxx().getZt().equals("3")) {
            //审核已通过
            if (Contains.sAllInfo.getData().getGfzgyj() != null) {
                tv1.setText(Contains.sAllInfo.getData().getGfzgyj());
                tv2.setText("");
            } else {
                tv1.setText("社保已缴纳24个月");
                tv2.setText("个税已缴纳24个月");
            }

            Glide.with(this).load(R.mipmap.ic_status_smile1).into(imageView);
        } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("4")) {
            if (Contains.sAllInfo.getData().getGfzgyj() != null) {
                tv1.setText(Contains.sAllInfo.getData().getGfzgyj());
                tv2.setText("");
            } else {
                tv1.setText("原因未知");
                tv2.setText("");
            }
            Glide.with(this).load(R.mipmap.ic_status_smile2).into(imageView);
        }
        dialog.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}