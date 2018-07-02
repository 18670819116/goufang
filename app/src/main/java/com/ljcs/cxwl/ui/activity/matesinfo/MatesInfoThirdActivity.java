package com.ljcs.cxwl.ui.activity.matesinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.sdk.utils.ImageUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.certification.CertificationFourActivity;
import com.ljcs.cxwl.ui.activity.certification.CertificationThirdActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoThirdComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoThirdContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoThirdModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoThirdPresenter;
import com.ljcs.cxwl.util.FileUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.view.RxToast;

import java.io.File;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA_FAN;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/27 16:33:53
 */

public class MatesInfoThirdActivity extends BaseActivity implements MatesInfoThirdContract.View {

    @Inject
    MatesInfoThirdPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matesinfo_third);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerMatesInfoThirdComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .matesInfoThirdModule(new MatesInfoThirdModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(MatesInfoThirdContract.MatesInfoThirdContractPresenter presenter) {
        mPresenter = (MatesInfoThirdPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }
    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent = new Intent(MatesInfoThirdActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication()).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
        startActivityForResult(intent, REQUEST_CODE_CAMERA_FAN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA_FAN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                showProgressDialog();
                final File tempImage = new File(this.getCacheDir(), "idcard_fan_peiou");
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                        .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
                fileRealPath = tempImage.getAbsolutePath();
                Logger.i(tempImage.getAbsolutePath()+"-------"+tempImage.length());
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }
    }

    private String fileRealPath;

    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                closeProgressDialog();
                if (result != null) {
                    Logger.i(result.toString());
                    Contains.sCertificationInfo.setSignDate_peiou(result.getSignDate() == null ? "" : result.getSignDate()
                            .toString());
                    Contains.sCertificationInfo.setExpiryDate_peiou(result.getExpiryDate() == null ? "" : result
                            .getExpiryDate().toString());
                    Contains.sCertificationInfo.setIssueAuthority_peiou(result.getIssueAuthority() == null ? "" : result
                            .getIssueAuthority().toString());
                    Contains.sCertificationInfo.setPic_path_fan_peiou(fileRealPath);
                    startActivty(MatesInfoFourActivity.class);

                } else {
                    RxToast.normal("扫描识别失败 请重新扫描");
                }
            }

            @Override
            public void onError(OCRError error) {
                closeProgressDialog();
                Logger.e(error.getMessage());
                RxToast.normal("扫描识别失败 请重新扫描");
            }
        });
    }
}