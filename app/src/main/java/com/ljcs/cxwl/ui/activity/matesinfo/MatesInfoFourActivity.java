package com.ljcs.cxwl.ui.activity.matesinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoFourComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoFourContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoFourModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoFourPresenter;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwoActivity;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.qiniu.android.http.ResponseInfo;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.ENTERTYPE_CHANGE;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/27 16:35:11
 */

public class MatesInfoFourActivity extends BaseActivity implements MatesInfoFourContract.View {

    @Inject
    MatesInfoFourPresenter mPresenter;
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
        setContentView(R.layout.activity_matesinfo_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
    }

    @Override
    protected void initData() {
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority_peiou());
        tvData1.setText(Contains.sCertificationInfo.getSignDate_peiou());
        tvData2.setText(Contains.sCertificationInfo.getExpiryDate_peiou());
        Glide.with(this).load(Contains.sCertificationInfo.getPic_path_fan_peiou()).skipMemoryCache(true).diskCacheStrategy
                (DiskCacheStrategy.NONE).into(imageView);
        //imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan_peiou()));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMatesInfoFourComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .matesInfoFourModule(new MatesInfoFourModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MatesInfoFourContract.MatesInfoFourContractPresenter presenter) {
        mPresenter = (MatesInfoFourPresenter) presenter;
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
                if (ENTERTYPE_CHANGE == 1) {
                    startActivty(MatesInfoThirdActivity.class);
                } else {
                    finish();
                }
                break;
            case R.id.next:
                if (checkText()) {
                    Contains.sCertificationInfo.setIssueAuthority_peiou(tvIssueAuthority.getText().toString().trim());
                    Contains.sCertificationInfo.setSignDate_peiou(tvData1.getText().toString().trim());
                    Contains.sCertificationInfo.setExpiryDate_peiou(tvData2.getText().toString().trim());
                    if (ENTERTYPE_CHANGE == 1 && Contains.sCertificationInfo.getPic_path_fan_peiou().startsWith("http")) {
                        //配偶过来取消上传图片
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("token", RxSPTool.getString(MatesInfoFourActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                        map.put("qfjg", Contains.sCertificationInfo.getIssueAuthority_peiou());
                        map.put("yxq", Contains.sCertificationInfo.getSignDate_peiou() + "-" + Contains
                                .sCertificationInfo.getExpiryDate_peiou());
                        map.put("sfzfm", Contains.sAllInfo.getData().getPoxx().getSfzfm());
                        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                                Contains.sAllInfo.getData().getPoxx().getBh() != 0) {
                            map.put("bh", Contains.sAllInfo.getData().getPoxx().getBh() + "");
                        } else {
                            map.put("bh", "");
                        }
                        mPresenter.matesInfo(map);
                    } else {
                        mPresenter.getQiniuToken();
                    }
                } break;
            default:
                break;
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
    public void matesInfoSuccess(MatesInfo baseEntity) {
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
            ToastUtil.showCenterShort(baseEntity.msg);
            startActivty(FamilyRegisterTwoActivity.class);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(Contains.sCertificationInfo.getPic_path_fan_peiou(), qiniuToken.getUptoken(),
                    new UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("token", RxSPTool.getString(MatesInfoFourActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("qfjg", Contains.sCertificationInfo.getIssueAuthority_peiou());
                    map.put("yxq", Contains.sCertificationInfo.getSignDate_peiou() + "-" + Contains
                            .sCertificationInfo.getExpiryDate_peiou());
                    map.put("sfzfm", url);
                    if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                            Contains.sAllInfo.getData().getPoxx().getBh() != 0) {
                        map.put("bh", Contains.sAllInfo.getData().getPoxx().getBh() + "");
                    } else {
                        map.put("bh", "");
                    }
                    mPresenter.matesInfo(map);
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
}