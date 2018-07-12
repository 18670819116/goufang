package com.ljcs.cxwl.ui.activity.other;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.main.MainActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterFourComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterFourContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterFourModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterFourPresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ToastUtil;
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
 * @date 2018/06/27 20:03:29
 */

public class FamilyRegisterFourActivity extends BaseActivity implements FamilyRegisterFourContract.View {

    @Inject
    FamilyRegisterFourPresenter mPresenter;
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

    @BindView(R.id.tv_idcard)
    TextView tvIdcard;
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
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_hjszd)
    TextView tvHjszd;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.tv_lysj)
    TextView tvLysj;
    @BindView(R.id.tv_card3)
    TextView tvCard3;
    @BindView(R.id.img5_peiou)
    ImageView img5Peiou;
    @BindView(R.id.layout4)
    LinearLayout layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("核对并提交信息");
        btnLogin.setText("确认提交");
        intiViews();

    }

    @Override
    protected void initData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.allInfo(map1);
    }

    private void intiViews() {
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
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData().getSmyz()
                        .getSfzzm());
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData().getSmyz()
                        .getSfzfm());
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData().getHjxx()
                        .getHkzp());
            }
        });

        if (Contains.sAllInfo.getData().getPoxx() != null) {
            if (Contains.sAllInfo.getData().getHjxx().getHyzt().equals("已婚")) {
                //表示已婚 现配偶
                layout4.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
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
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getSfzzm());
                    }
                });
                img2Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getSfzfm());
                    }
                });
                img3Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getHkzp());
                    }
                });
                img4Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getJhzzp());
                    }
                });

            } else {
                //表示离异 前配偶
                layout4.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.GONE);
                tvName3.setText(Contains.sAllInfo.getData().getPoxx().getXm());
                tvLysj.setText(Contains.sAllInfo.getData().getPoxx().getLhrq());
                tvCard3.setText(Contains.sAllInfo.getData().getPoxx().getSfzhm());
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp()).into(img5Peiou);
                img5Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getJhzzp());
                    }
                });
            }

        } else {
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
        }
        layoutZinvContent.removeAllViews();
        if (Contains.sAllInfo.getData().getJtcyList() != null && Contains.sAllInfo.getData().getJtcyList().size() > 0) {
            for (int i = 0; i < Contains.sAllInfo.getData().getJtcyList().size(); i++) {
                ZinvInfoLayout zinvInfoLayout = new ZinvInfoLayout(this, Contains.sAllInfo.getData().getJtcyList()
                        .get(i));
                final int finalI = i;
                zinvInfoLayout.getImg1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC+Contains.sAllInfo.getData().getJtcyList()
                                .get(finalI).getHkzp());
                    }
                });
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
    public void commitInfoSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort(baseEntity.msg);
//            AppManager.getInstance().finishActivity(QualificationExaminationActivity.class);
//            AppManager.getInstance().finishActivity(FamilyAddActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoFourActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoThirdActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoTwoActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoOneActivity.class);
//            AppManager.getInstance().finishActivity(FamilyRegisterActivity.class);
//            AppManager.getInstance().finishActivity(FamilyRegisterTwoActivity.class);
//            AppManager.getInstance().finishActivity(FamilyRegisterThirdActivity.class);
//            //startActivty(FamilyRegisterFourActivity.class);
//            finish();
            AppManager.getInstance().finishAllActivity();
            startActivty(MainActivity.class);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void matesInfoSaveSuccess(BaseEntity baseEntity) {

        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort("保存成功");
//            AppManager.getInstance().finishActivity(QualificationExaminationActivity.class);
//            AppManager.getInstance().finishActivity(FamilyAddActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoFourActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoThirdActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoTwoActivity.class);
//            AppManager.getInstance().finishActivity(MatesInfoOneActivity.class);
//            AppManager.getInstance().finishActivity(FamilyRegisterActivity.class);
//            AppManager.getInstance().finishActivity(FamilyRegisterTwoActivity.class);
//            AppManager.getInstance().finishActivity(FamilyRegisterThirdActivity.class);
//            //startActivty(FamilyRegisterFourActivity.class);
//            finish();
            AppManager.getInstance().finishAllActivity();
            startActivty(MainActivity.class);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterFourComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterFourModule(new FamilyRegisterFourModule(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterFourContract.FamilyRegisterFourContractPresenter presenter) {
        mPresenter = (FamilyRegisterFourPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.commitInfo(map);
    }
}