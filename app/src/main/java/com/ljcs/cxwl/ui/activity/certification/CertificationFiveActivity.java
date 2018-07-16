package com.ljcs.cxwl.ui.activity.certification;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationFiveComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFiveContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationFiveModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationFivePresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.view.CertificationDialog;
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
        tvData.setText(Contains.sCertificationInfo.getSignDate() + "-" + Contains.sCertificationInfo.getExpiryDate());
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
        progressDialog.setCancelable(false);
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
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
                if (Contains.sCertificationInfo.isChangeZm() || Contains.sCertificationInfo.isChangeFm()) {
                    //表示扫描的信息已被修改过
                    Logger.i("身份证扫描信息已被手动修改");
                    //1是修改过，2是没修改的
                    map.put("rgsh", "1");
                } else {
                    map.put("rgsh", "2");
                }
                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null && Contains
                        .sAllInfo.getData().getSmyz().getBh() != 0) {
                    map.put("bh", Contains.sAllInfo.getData().getSmyz().getBh() + "");
                } else {
                    map.put("bh", "");
                }
                mPresenter.cerInfoLast(map);

                break;
            default:
                break;
        }
    }

    @Override
    public void cerInfoLastSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
            mPresenter.allInfo(map1);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            Contains.sAllInfo = baseEntity;
            final CertificationDialog dialog = new CertificationDialog(CertificationFiveActivity.this);
            dialog.setCancelable(false);
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
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }
}