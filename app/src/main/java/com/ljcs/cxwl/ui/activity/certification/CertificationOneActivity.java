package com.ljcs.cxwl.ui.activity.certification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

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
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationOneComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationOneContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationOneModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationOnePresenter;
import com.ljcs.cxwl.util.FileUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.view.RxToast;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA;
import static com.ljcs.cxwl.contain.Contains.sCertificationInfo;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: $description
 * @date 2018/06/26 19:26:21
 */

public class CertificationOneActivity extends BaseActivity implements CertificationOneContract.View {

    @Inject
    CertificationOnePresenter mPresenter;

    private boolean hasGotToken = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_certification_one);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");
    }

    @Override
    protected void initData() {
        showProgressDialog();
        initAccessTokenWithAkSk();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationOneComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent
                ()).certificationOneModule(new CertificationOneModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(CertificationOneContract.CertificationOneContractPresenter presenter) {
        mPresenter = (CertificationOnePresenter) presenter;
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
        Intent intent = new Intent(CertificationOneActivity.this, CameraActivity.class);
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
                final File tempImage = new File(CertificationOneActivity.this.getCacheDir(), "idcard_zheng");
                Logger.e("file size1" + tempImage.length());
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                        .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
                fileRealPath = tempImage.getAbsolutePath();
                Logger.e(tempImage.getAbsolutePath());
                Logger.e("file size2" + tempImage.length());
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
                    sCertificationInfo.setName(result.getName() == null ? "" : result.getName().toString());
                    sCertificationInfo.setSex(result.getGender() == null ? "" : result.getGender().toString());
                    sCertificationInfo.setEthnic(result.getEthnic() == null ? "" : result.getEthnic().toString());
                    sCertificationInfo.setAddress(result.getAddress() == null ? "" : result.getAddress().toString());
                    sCertificationInfo.setBirthday(result.getBirthday() == null ? "" : result.getBirthday().toString());
                    sCertificationInfo.setIdcard(result.getIdNumber() == null ? "" : result.getIdNumber().toString());
                    sCertificationInfo.setPic_path_zheng(fileRealPath);
                    startActivty(CertificationTwoActivity.class);
//                    try {
//                        Intent intent = new Intent(CertificationOneActivity.this, CertificationTwoActivity.class);
//                        intent.putExtra("name", result.getName().toString());
//                        intent.putExtra("sex", result.getGender().toString());
//                        intent.putExtra("ethnic", result.getEthnic().toString());
//                        intent.putExtra("birthday", result.getBirthday().toString());
//                        intent.putExtra("address", result.getAddress().toString());
//                        intent.putExtra("idcard", result.getIdNumber().toString());
//                        intent.putExtra("file_path", fileRealPath);
//                        startActivity(intent);
//                    } catch (Exception e) {
//                        RxToast.normal("扫描识别失败 请重新扫描");
//                    }


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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        CameraNativeHelper.init(CertificationOneActivity.this, OCR.getInstance(CertificationOneActivity.this)
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
                });

            }

            @Override
            public void onError(OCRError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                    }
                });
                error.printStackTrace();
                Logger.e("OCRtoken获取失败" + error.getMessage());
            }
        }, getApplicationContext(), "gPFYMZ1xIUMZP8C44xdPVbso", "O3ADFGC344Wvrc94gmX9d5gK779P1O9A");
    }


    @Override
    protected void onDestroy() {
        Logger.e("onDestroy");
        // 释放本地质量控制模型
        if (IDcardQualityProcess.getInstance() != null) {
            CameraNativeHelper.release();
        }
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance(this).release();
    }
}