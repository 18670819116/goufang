package com.ljcs.cxwl.ui.activity.certification;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationFourComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFourContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationFourModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationFourPresenter;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.RxTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: $description
 * @date 2018/06/26 19:28:19
 */

public class CertificationFourActivity extends BaseActivity implements CertificationFourContract.View {

    @Inject
    CertificationFourPresenter mPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_issueAuthority)
    EditText tvIssueAuthority;
    @BindView(R.id.tv_data1)
    EditText tvData1;
    @BindView(R.id.tv_data2)
    EditText tvData2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_certification_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");
    }

    @Override
    protected void initData() {
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority());
        tvData1.setText(Contains.sCertificationInfo.getSignDate());
        tvData2.setText(Contains.sCertificationInfo.getExpiryDate());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan()));

    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationFourComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).certificationFourModule(new CertificationFourModule(this)).build().inject
                (this);
    }

    @Override
    public void setPresenter(CertificationFourContract.CertificationFourContractPresenter presenter) {
        mPresenter = (CertificationFourPresenter) presenter;
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
                if (checkText()) {
                    checkChange();
                    Contains.sCertificationInfo.setSignDate(tvData1.getText().toString().trim());
                    Contains.sCertificationInfo.setExpiryDate(tvData2.getText().toString().trim());
                    Contains.sCertificationInfo.setIssueAuthority(tvIssueAuthority.getText().toString().trim());
                    mPresenter.getQiniuToken();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 判断身份证反面是否被修改过
     */
    private void checkChange() {
        if (Contains.sCertificationInfo.getSignDate().equals(tvData1.getText().toString().trim()) && Contains
                .sCertificationInfo.getExpiryDate().equals(tvData2.getText().toString().trim()) && Contains
                .sCertificationInfo.getIssueAuthority().equals(tvIssueAuthority.getText().toString().trim())) {
            //表示反面信息没有被修改过
            Contains.sCertificationInfo.setChangeFm(false);
        } else {
            //表示反面信息已被手动修改
            Contains.sCertificationInfo.setChangeFm(true);
        }
    }

    private boolean checkText() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return false;
        }
        if (RxDataTool.isNullString(tvIssueAuthority.getText().toString())) {
            ToastUtil.showCenterShort("签发机关为空");
            return false;
        }
        if (RxDataTool.isNullString(tvData1.getText().toString())) {
            ToastUtil.showCenterShort("有效期为空");
            return false;
        }
        if (RxDataTool.isNullString(tvData2.getText().toString())) {
            ToastUtil.showCenterShort("有效期为空");
            return false;
        }
        return true;
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(Contains.sCertificationInfo.getPic_path_fan(), qiniuToken.getUptoken(), new
                    UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("token", RxSPTool.getString(CertificationFourActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("qfjg", Contains.sCertificationInfo.getIssueAuthority());
                    map.put("yxq", Contains.sCertificationInfo.getSignDate() + "-" + Contains.sCertificationInfo
                            .getExpiryDate());
                    map.put("sfzfm", url);
                    if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null &&
                            Contains.sAllInfo.getData().getSmyz().getBh() != 0) {
                        map.put("bh", Contains.sAllInfo.getData().getSmyz().getBh() + "");
                    } else {
                        map.put("bh", "");
                    }
                    mPresenter.postInfo(map);
                }

                @Override
                public void sucess(List<String> url) {

                }

                @Override
                public void fail(String key, ResponseInfo info) {
                    closeProgressDialog();
                }
            });
        }

    }

    @Override
    public void postInfoSuccess(CerInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            Map<String, String> map = new HashMap<>();
            map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
            mPresenter.allInfo(map);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }

    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            Contains.sAllInfo = baseEntity;
//            ToastUtil.showCenterShort(baseEntity.msg);
            startActivty(CertificationFiveActivity.class);

        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }
}