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
import com.ljcs.cxwl.view.CertificationDialog;
import com.ljcs.cxwl.view.ZinvInfoLayout;
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
    @BindView(R.id.tv_hjszd2)
    TextView tvHjszd2;
    @BindView(R.id.tv_phone2)
    TextView tvPhone2;
    @BindView(R.id.tv_hjszd3)
    TextView tvHjszd3;
    @BindView(R.id.tv_phone3)
    TextView tvPhone3;

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
        tvName1.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getXm());
        tvSex1.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getXb());
        tvCard1.setText(Contains.sAllInfo.getData().getGrxx().getSfz().getZjhm());
//        tvHklx1.setText(Contains.sAllInfo.getData().getHjxx().getHklx());
        tvHkxz1.setText(Contains.sAllInfo.getData().getGrxx().getJtcy().getHjfl());
        tvHyzk1.setText(Contains.sAllInfo.getData().getGrxx().getJtcy().getHyzk());
        tvHjszd.setText(Contains.sAllInfo.getData().getGrxx().getJtcy().getHjszd());
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getSfzzm()).into(img1);
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getSfzfm()).into(img2);
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getHkb()).into(img3);
        Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getGrxx().getZzxx().getJhz()).into(img4);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData().getGrxx()
                        .getZzxx().getSfzzm());
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData().getGrxx()
                        .getZzxx().getSfzfm());
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData().getGrxx()
                        .getZzxx().getHkb());
            }
        });

        if (Contains.sAllInfo.getData().getPoxx() != null&&Contains.sAllInfo.getData().getPoxx().getJtcy()!=null) {
            if (Contains.sAllInfo.getData().getGrxx().getJtcy().getHyzk().equals("已婚")) {
                //表示已婚 现配偶
                layout4.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
                tvName2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getXm());
                tvSex2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getXb());
                tvCard2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getZjhm());
//                tvHklx2.setText(Contains.sAllInfo.getData().getPoxx().getHklx());
                tvHkxz2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getHjfl());
                tvHjszd2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getHjszd());
                tvPhone2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getLxdh());
//                tvHyzk2.setText(Contains.sAllInfo.getData().getPoxx().getHyzt());
//                tvGx.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().geh);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzzm()).into
                        (img1Peiou);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzfm()).into
                        (img2Peiou);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getHkb()).into
                        (img3Peiou);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getJhz()).into
                        (img4Peiou);
                img1Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getZzxx().getSfzzm());
                    }
                });
                img2Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getZzxx().getSfzfm());
                    }
                });
                img3Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getZzxx().getHkb());
                    }
                });
                img4Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getZzxx().getJhz());
                    }
                });

            } else if (Contains.sAllInfo.getData().getGrxx().getJtcy().getHyzk().equals("离异")){
                //表示离异 前配偶
                layout4.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.GONE);
                tvName3.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getXm());
                tvLysj.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getLysj());
                tvCard3.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getZjhm());
                tvHjszd3.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getHjszd());
                tvPhone3.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getLxdh());
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getLhz()).into
                        (img5Peiou);
                img5Peiou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getPoxx().getZzxx().getLhz());
                    }
                });
            }

        } else {
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
        }
        layoutZinvContent.removeAllViews();
        if (Contains.sAllInfo.getData().getZnxxlist() != null && Contains.sAllInfo.getData().getZnxxlist().size() > 0) {
            for (int i = 0; i < Contains.sAllInfo.getData().getZnxxlist().size(); i++) {
                ZinvInfoLayout zinvInfoLayout = new ZinvInfoLayout(this, Contains.sAllInfo.getData().getZnxxlist()
                        .get(i));
                final int finalI = i;
                zinvInfoLayout.getImg1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startToImgActivity(FamilyRegisterFourActivity.this, API.PIC + Contains.sAllInfo.getData()
                                .getZnxxlist().get(finalI).getZzxx().getHkb());
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
            final CertificationDialog dialog = new CertificationDialog(this);
            dialog.setCancelable(false);
            dialog.getImageView().setImageResource(R.mipmap.ic_tijiao_shq);
            dialog.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    startActivty(MainActivity.class);
                    AppManager.getInstance().finishAllActivity();
                }
            });
            dialog.getTv1().setText("购房资格信息已提交");
            dialog.getTv2().setText("审查中，预计1个工作日完成审核");
            dialog.show();

        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void matesInfoSaveSuccess(BaseEntity baseEntity) {

        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort("保存成功");
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