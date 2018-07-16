package com.ljcs.cxwl.ui.activity.matesinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.baidu.idcardquality.IDcardQualityProcess;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.sdk.utils.ImageUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoOneComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoOneContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoOneModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoOnePresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.FileUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.view.RxToast;

import java.io.File;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.ENTERTYPE_CHANGE;
import static com.ljcs.cxwl.contain.Contains.sCertificationInfo;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/27 16:32:06
 */

public class MatesInfoOneActivity extends BaseActivity implements MatesInfoOneContract.View {

    @Inject
    MatesInfoOnePresenter mPresenter;
    private static final int REQUEST_CODE_CAMERA = 101;
    private boolean hasGotToken = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matesinfo_one);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
    }

    @Override
    protected void initData() {
        initAccessTokenWithAkSk();
    }

    @Override
    protected void setupActivityComponent() {
       DaggerMatesInfoOneComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .matesInfoOneModule(new MatesInfoOneModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(MatesInfoOneContract.MatesInfoOneContractPresenter presenter) {
        mPresenter = (MatesInfoOnePresenter) presenter;
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
        if (!checkTokenStatus()) {
            return;
        }
        Intent intent = new Intent(MatesInfoOneActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(AppConfig.getInstance())
                .getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }
    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            //Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                showProgressDialog();
                final File tempImage = new File(MatesInfoOneActivity.this.getCacheDir(), "idcard_zheng_peiou");
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                        .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
                fileRealPath = tempImage.getAbsolutePath();
                Logger.e(tempImage.getAbsolutePath()+"-----"+tempImage.length());
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

    private String fileRealPath = "";

    private void recIDCard(String idCardSide, final String filePath) {
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
                    sCertificationInfo.setName_peiou(result.getName() == null ? "" : result.getName().toString());
                    sCertificationInfo.setSex_peiou(result.getGender() == null ? "" : result.getGender().toString());
                    sCertificationInfo.setEthnic_peiou(result.getEthnic() == null ? "" : result.getEthnic().toString());
                    sCertificationInfo.setAddress_peiou(result.getAddress() == null ? "" : result.getAddress().toString());
                    sCertificationInfo.setBirthday_peiou(result.getBirthday() == null ? "" : result.getBirthday().toString());
                    sCertificationInfo.setIdcard_peiou(result.getIdNumber() == null ? "" : result.getIdNumber().toString());
                    sCertificationInfo.setPic_path_zheng_peiou(fileRealPath);
                    AppManager.getInstance().finishActivity(MatesInfoTwoActivity.class);
                    startActivty(MatesInfoTwoActivity.class);
                    if (ENTERTYPE_CHANGE==1){
                        finish();
                    }


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

    /**
     * 用明文ak，sk初始化
     */
    String token;

    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                token = result.getAccessToken();
                Logger.i("token" + token);
                hasGotToken = true;
                CameraNativeHelper.init(MatesInfoOneActivity.this, OCR.getInstance(MatesInfoOneActivity.this)
                        .getLicense(), new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }
                        Logger.e("CameraNativeHelper.init" + msg);
                    }
                });
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                Logger.e("OCRtoken获取失败" + error.getMessage());
            }
        }, getApplicationContext(), "gPFYMZ1xIUMZP8C44xdPVbso", "O3ADFGC344Wvrc94gmX9d5gK779P1O9A");
    }


    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        if (IDcardQualityProcess.getInstance() != null) {
            CameraNativeHelper.release();
        }
        super.onDestroy();
        // 释放内存资源
//        OCR.getInstance(this).release();
    }
}