package com.ljcs.cxwl.ui.activity.other;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.ljcs.cxwl.ui.activity.scan.ScanActivity;
import com.ljcs.cxwl.view.CertificationDialog;
import com.ljcs.cxwl.view.ZinvInfoLayout;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

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
    @BindView(R.id.bg_head1)
    LinearLayout bgHead1;
    @BindView(R.id.tv_yuanyin)
    TextView tvYuanyin;
    @BindView(R.id.bg_head2)
    LinearLayout bgHead2;
    @BindView(R.id.tv_fankui)
    TextView tvFankui;
    @BindView(R.id.tv_tijiaozige)
    TextView tvTijiaozige;
    @BindView(R.id.bg_head3)
    LinearLayout bgHead3;
    @BindView(R.id.bg_head)
    RelativeLayout bgHead;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    private CertificationDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_family_register_status);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void initData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.allInfo(map1);
        initDialog();
        final int height = 63;
        Logger.e("height"+height);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // 将透明度声明成局部变量用于判断
                int alpha = 0;
                int count = 0;
                float scale = 0;
                Logger.e("scrollY"+scrollY+"oldScrollY"+oldScrollY);
                if (scrollY >= height) {
                    scale = (float) scrollY / height;
                    alpha = (int) (255 * scale);
                    // 随着滑动距离改变透明度
                    // Log.e("al=","="+alpha);
                    toolbarTitle.setText("购房资格审核");
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.main_color));
                    autolayout.setBackgroundColor(getResources().getColor(R.color.main_color));
                } else {
                    if (alpha < 255) {
                        Log.e("执行次数", "=" + (++count));
                        // 防止频繁重复设置相同的值影响性能
                        toolbarTitle.setText("");
                        autolayout.setBackgroundColor(getResources().getColor(R.color.color_00000000));
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.color_00000000));

                    }
                }

            }
        });
    }

    @Override
    public void intiViews() {
        if (Contains.sAllInfo.getData().getHjxx() != null) {
            if (Contains.sAllInfo.getData().getHjxx().getZt().equals("2")) {
                //审核中
                bgHead1.setVisibility(View.VISIBLE);
                bgHead2.setVisibility(View.GONE);
                bgHead3.setVisibility(View.GONE);
            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("3")) {
                //审完成
                bgHead1.setVisibility(View.GONE);
                bgHead2.setVisibility(View.GONE);
                bgHead3.setVisibility(View.VISIBLE);
            } else if (Contains.sAllInfo.getData().getHjxx().getZt().equals("4")) {
                //未通过
                bgHead1.setVisibility(View.GONE);
                bgHead2.setVisibility(View.VISIBLE);
                bgHead3.setVisibility(View.GONE);

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
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData().getSmyz()
                        .getSfzzm());
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData().getSmyz()
                        .getSfzfm());
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData().getHjxx()
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
                        startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getSfzzm());
                    }
                });
                img2Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getSfzfm());
                    }
                });
                img3Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getHkzp());
                    }
                });
                img4Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData()
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
                        startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData()
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
                        startToImgActivity(FamilyRegisterStatusActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getJtcyList().get(finalI).getHkzp());
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


    @OnClick({R.id.tv_yuanyin, R.id.tv_fankui, R.id.tv_tijiaozige})
    public void onViewClicked(View view) {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return;
        }
        ImageView imageView = dialog.getImageView();
        TextView tv1 = dialog.getTv1();
        TextView tv2 = dialog.getTv2();
        switch (view.getId()) {
            case R.id.tv_yuanyin:
                if (Contains.sAllInfo.getData().getGfzgyj() != null) {
                    tv1.setText(Contains.sAllInfo.getData().getGfzgyj());
                    tv2.setText("");
                } else {
                    tv1.setText("未通过审核");
                    tv2.setText("提交的信息未满足购房资格");
                }
                Glide.with(this).load(R.mipmap.ic_status_smile2).into(imageView);
                dialog.show();
                break;
            case R.id.tv_fankui:
                //审核已通过
                if (Contains.sAllInfo.getData().getGfzgyj() != null) {
                    tv1.setText(Contains.sAllInfo.getData().getGfzgyj());
                    tv2.setText("");
                } else {
                    tv1.setText("恭喜您通过审核");
                    tv2.setText("所有信息已满足购房资格");
                }

                Glide.with(this).load(R.mipmap.ic_status_smile1).into(imageView);
                dialog.show();
                break;
            case R.id.tv_tijiaozige:
                startActivty(ScanActivity.class);
                break;
        }
    }

    public void initDialog() {

        dialog = new CertificationDialog(this);
        dialog.setCancelable(false);
        dialog.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}