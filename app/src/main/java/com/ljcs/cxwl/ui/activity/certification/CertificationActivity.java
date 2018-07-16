package com.ljcs.cxwl.ui.activity.certification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationPresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.GlideUtils;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.view.CertificationDialog;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.FAST_CLICK;
import static com.ljcs.cxwl.contain.Contains.OCR_AK;
import static com.ljcs.cxwl.contain.Contains.OCR_SK;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA_FAN;
import static com.ljcs.cxwl.contain.Contains.sCertificationInfo;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: $description
 * @date 2018/07/11 08:32:05
 */

public class CertificationActivity extends BaseActivity implements CertificationContract.View {

    @Inject
    CertificationPresenter mPresenter;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_sex)
    EditText tvSex;
    @BindView(R.id.tv_ethnic)
    EditText tvEthnic;
    @BindView(R.id.tv_birthday)
    EditText tvBirthday;
    @BindView(R.id.tv_adress)
    EditText tvAdress;
    @BindView(R.id.tv_idcard)
    EditText tvIdcard;
    @BindView(R.id.tv_issueAuthority)
    EditText tvIssueAuthority;
    @BindView(R.id.tv_data1)
    EditText tvData1;
    @BindView(R.id.tv_data2)
    EditText tvData2;
    @BindView(R.id.imageView_zheng)
    ImageView imageViewZheng;
    @BindView(R.id.imageView_fan)
    ImageView imageViewFan;
    @BindView(R.id.layout_content1)
    LinearLayout layoutContent1;
    @BindView(R.id.layout_content2)
    LinearLayout layoutContent2;
    @BindView(R.id.img_chongpai1)
    ImageView imgChongpai1;
    @BindView(R.id.img_chongpai2)
    ImageView imgChongpai2;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private boolean hasGotToken = false;
    private String fileRealPath1;
    private String fileRealPath2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");
        //初始化ocr服务
        initAccessTokenWithAkSk();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .certificationModule(new CertificationModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(CertificationContract.CertificationContractPresenter presenter) {
        mPresenter = (CertificationPresenter) presenter;
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

    @Override
    public void postInfoSuccess(CerInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            AppManager.getInstance().finishActivity(AboutCertificationActivity.class);
            final CertificationDialog dialog = new CertificationDialog(CertificationActivity.this);
            dialog.setCancelable(false);
            if ("男".equals(tvSex.getText().toString())) {
                dialog.getImageView().setImageResource(R.drawable.ic_boy);
            } else {
                dialog.getImageView().setImageResource(R.drawable.ic_girl);

            }
            dialog.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.show();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }


    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            ToastUtil.showCenterShort("token还未成功获取");
        }
        return hasGotToken;
    }

    private void initAccessTokenWithAkSk() {
        showProgressDialog();
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {

                String token = result.getAccessToken();
                Logger.i("ocr获取token" + token);
                hasGotToken = true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        CameraNativeHelper.init(CertificationActivity.this, OCR.getInstance(CertificationActivity
                                .this).getLicense(), new CameraNativeHelper.CameraNativeInitCallback() {
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
        }, getApplicationContext(), OCR_AK, OCR_SK);
    }


    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        if (IDcardQualityProcess.getInstance() != null) {
            CameraNativeHelper.release();
        }
        super.onDestroy();
    }

    @OnClick({R.id.imageView_zheng, R.id.imageView_fan, R.id.btn_login, R.id.img_chongpai1, R.id.img_chongpai2})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_zheng:
                if (!checkTokenStatus()) {
                    return;
                }
                if (RxTool.isFastClick(FAST_CLICK)) {
                    return;
                }
                if (imgChongpai1.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(AppConfig.getInstance()
                    ).getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                    // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                    // 请手动使用CameraNativeHelper初始化和释放模型
                    // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                    intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);

                } else {
                    Logger.i("显示正面大图");
                }
                break;
            case R.id.imageView_fan:
                if (RxTool.isFastClick(FAST_CLICK)) {
                    return;
                }
                if (!checkTokenStatus()) {
                    return;
                }
                if (imgChongpai2.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                    // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                    // 请手动使用CameraNativeHelper初始化和释放模型
                    // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                    intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA_FAN);
                } else {
                    Logger.i("显示反面大图");
                }
                break;
            case R.id.btn_login:
                if (checkText()) {
                    showProgressDialog();
                    List<String> list = new ArrayList<>();
                    list.add(fileRealPath1);
                    list.add(fileRealPath2);
                    mPresenter.uploadPic(list, new UploadFileCallBack() {
                        @Override
                        public void sucess(List<String> url) {

                            Map<String, String> map = new HashMap<>();
                            map.put("token", RxSPTool.getString(CertificationActivity.this, ShareStatic
                                    .APP_LOGIN_TOKEN));
                            map.put("sfzhm", tvIdcard.getText().toString());
                            map.put("mz", tvEthnic.getText().toString());
                            map.put("csrq", tvBirthday.getText().toString());
                            map.put("xm", tvName.getText().toString());
                            map.put("xb", tvSex.getText().toString());
                            map.put("sfzzm", url.get(0));
                            map.put("qfjg", tvIssueAuthority.getText().toString());
                            map.put("yxq", tvData1.getText().toString() + "-" + tvData2.getText().toString());
                            map.put("sfzfm", url.get(1));
                            map.put("dz", tvAdress.getText().toString());
                            if (isChange()) {
                                //1是修改过，2是没修改的
                                Logger.i("手动修改过");
                                map.put("rgsh", "1");
                            } else {
                                map.put("rgsh", "2");
                            }
                            mPresenter.postInfo(map);

                        }

                        @Override
                        public void fail(String msg) {
                            closeProgressDialog();
                            ToastUtil.showCenterShort(msg);
                        }
                    });
                }
                break;
            case R.id.img_chongpai1:
                if (RxTool.isFastClick(FAST_CLICK)) {
                    return;
                }
                if (imgChongpai1.getVisibility() == View.VISIBLE) {
                    intent = new Intent(this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(AppConfig.getInstance()
                    ).getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                    // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                    // 请手动使用CameraNativeHelper初始化和释放模型
                    // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                    intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                }
                break;
            case R.id.img_chongpai2:
                if (RxTool.isFastClick(FAST_CLICK)) {
                    return;
                }
                if (imgChongpai1.getVisibility() == View.VISIBLE) {
                    intent = new Intent(this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                    // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                    // 请手动使用CameraNativeHelper初始化和释放模型
                    // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                    intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA_FAN);
                }
                break;
            default:
                break;
        }
    }

    private boolean checkText() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return false;
        }
        if (RxDataTool.isNullString(tvName.getText().toString())) {
            ToastUtil.showCenterShort("姓名为空");
            return false;
        }

        if (RxDataTool.isNullString(tvSex.getText().toString()) || (!tvSex.getText().toString().equals("男") && !tvSex
                .getText().toString().equals("女"))) {
            ToastUtil.showCenterShort("性别格式不正确");
            return false;
        }

        if (RxDataTool.isNullString(tvEthnic.getText().toString())) {
            ToastUtil.showCenterShort("民族为空");
            return false;
        }
        if (RxDataTool.isNullString(tvBirthday.getText().toString())) {
            ToastUtil.showCenterShort("出生格式不正确");
            return false;
        }

        if (RxDataTool.isNullString(tvAdress.getText().toString())) {
            ToastUtil.showCenterShort("住址为空");
            return false;
        }
        if (!IDcardUtil.IDCardValidate(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("身份证号码格式有误");
            return false;
        }
        if (!tvIdcard.getText().toString().substring(6, 14).equals(tvBirthday.getText().toString())) {
            ToastUtil.showCenterShort("出生日期和身份证的出生日期不一致");
            return false;
        }
        if (!IDcardUtil.ifGrown_up(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("根据政策规定，暂不支持对未成年人的实名认证");
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

    /**
     * 是否手动修改
     */
    private boolean isChange() {
        if (Contains.sCertificationInfo.getName().equals(tvName.getText().toString()) && Contains.sCertificationInfo
                .getAddress().equals(tvAdress.getText().toString()) && Contains.sCertificationInfo.getIdcard().equals
                (tvIdcard.getText().toString()) && Contains.sCertificationInfo.getBirthday().equals(tvBirthday
                .getText().toString()) && Contains.sCertificationInfo.getEthnic().equals(tvEthnic.getText().toString
                ()) && Contains.sCertificationInfo.getSex().equals(tvSex.getText().toString()) && Contains
                .sCertificationInfo.getSignDate().equals(tvData1.getText().toString()) && Contains.sCertificationInfo
                .getExpiryDate().equals(tvData2.getText().toString()) && Contains.sCertificationInfo
                .getIssueAuthority().equals(tvIssueAuthority.getText().toString())) {
            //表示没有修改
            return false;
        } else {
            //表示修改了
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                final File tempImage = new File(this.getCacheDir(), "idcard_zheng.jpg");
                Logger.e("file size1" + tempImage.length());
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                        .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
                fileRealPath1 = tempImage.getAbsolutePath();
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
        if (requestCode == REQUEST_CODE_CAMERA_FAN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                final File tempImage = new File(this.getCacheDir(), "idcard_fan.jpg");
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                        .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
                fileRealPath2 = tempImage.getAbsolutePath();
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


    private void recIDCard(final String idCardSide, String filePath) {
        showProgressDialog();
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
                    if (idCardSide.equals(IDCardParams.ID_CARD_SIDE_FRONT)) {
                        layoutContent1.setVisibility(View.VISIBLE);
                        imgChongpai1.setVisibility(View.VISIBLE);
                        if (layoutContent1.getVisibility() == View.VISIBLE && layoutContent2.getVisibility() == View
                                .VISIBLE) {
                            btnLogin.setEnabled(true);
                        }
                        sCertificationInfo.setName(result.getName() == null ? "" : result.getName().toString());
                        sCertificationInfo.setSex(result.getGender() == null ? "" : result.getGender().toString());
                        sCertificationInfo.setEthnic(result.getEthnic() == null ? "" : result.getEthnic().toString());
                        sCertificationInfo.setAddress(result.getAddress() == null ? "" : result.getAddress().toString
                                ());
                        sCertificationInfo.setBirthday(result.getBirthday() == null ? "" : result.getBirthday()
                                .toString());
                        sCertificationInfo.setIdcard(result.getIdNumber() == null ? "" : result.getIdNumber()
                                .toString());
                        sCertificationInfo.setPic_path_zheng(fileRealPath1);
                        tvName.setText(Contains.sCertificationInfo.getName());
                        tvSex.setText(Contains.sCertificationInfo.getSex());
                        tvEthnic.setText(Contains.sCertificationInfo.getEthnic());
                        tvBirthday.setText(Contains.sCertificationInfo.getBirthday());
                        tvAdress.setText(Contains.sCertificationInfo.getAddress());
                        tvIdcard.setText(Contains.sCertificationInfo.getIdcard());
                        GlideUtils.loadImgNoCach(CertificationActivity.this, Contains.sCertificationInfo
                                .getPic_path_zheng(), imageViewZheng);
                        // imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo
                        // .getPic_path_zheng()));
                    } else if (idCardSide.equals(IDCardParams.ID_CARD_SIDE_BACK)) {
                        layoutContent2.setVisibility(View.VISIBLE);
                        imgChongpai2.setVisibility(View.VISIBLE);
                        if (layoutContent1.getVisibility() == View.VISIBLE && layoutContent2.getVisibility() == View
                                .VISIBLE) {
                            btnLogin.setEnabled(true);
                        }
                        Contains.sCertificationInfo.setSignDate(result.getSignDate() == null ? "" : result
                                .getSignDate().toString());

                        Contains.sCertificationInfo.setExpiryDate(result.getExpiryDate() == null ? "" : result
                                .getExpiryDate().toString());
                        Contains.sCertificationInfo.setIssueAuthority(result.getIssueAuthority() == null ? "" :
                                result.getIssueAuthority().toString());
                        Contains.sCertificationInfo.setPic_path_fan(fileRealPath2);
                        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority());
                        tvData1.setText(Contains.sCertificationInfo.getSignDate());
                        tvData2.setText(Contains.sCertificationInfo.getExpiryDate());
                        GlideUtils.loadImgNoCach(CertificationActivity.this, Contains.sCertificationInfo
                                .getPic_path_fan(), imageViewFan);
                    }
                } else {
                    ToastUtil.showCenterShort("扫描识别失败 请重新扫描");
                }
            }

            @Override
            public void onError(OCRError error) {
                closeProgressDialog();
                Logger.e(error.getMessage());
                ToastUtil.showCenterShort("扫描识别失败 请重新扫描");
            }
        });
    }
}