package com.ljcs.cxwl.ui.activity.changephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

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
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.certification.AboutCertificationActivity;
import com.ljcs.cxwl.ui.activity.changephone.component.DaggerChangePhoneTwoComponent;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneTwoContract;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneTwoModule;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneTwoPresenter;
import com.ljcs.cxwl.ui.activity.main.LoginActivity;
import com.ljcs.cxwl.ui.activity.scan.ScanActivity;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ClearUtils;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.view.SureDialog;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxConstTool;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxEncryptTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA;
import static com.ljcs.cxwl.contain.Contains.sCertificationInfo;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: $description
 * @date 2018/07/09 16:39:17
 */

public class ChangePhoneTwoActivity extends BaseActivity implements ChangePhoneTwoContract.View {

    @Inject
    ChangePhoneTwoPresenter mPresenter;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.checkbox_eye)
    CheckBox checkboxEye;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.tv_get_yzm)
    TextView tvGetYzm;
    private CountDownTimer countDownTimer;
    private String code = "";
    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("更换手机号码");

    }

    @Override
    protected void initData() {
        showProgressDialog();
        initAccessTokenWithAkSk();
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetYzm.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvGetYzm.setEnabled(true);
                tvGetYzm.setText("获取验证码");
                tvGetYzm.setTextColor(getResources().getColor(R.color.color_0f77ff));
            }
        };
        checkboxEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    et3.setSelection(et3.getText().length());
                } else {
                    et3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et3.setSelection(et3.getText().length());
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerChangePhoneTwoComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent()
        ).changePhoneTwoModule(new ChangePhoneTwoModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ChangePhoneTwoContract.ChangePhoneTwoContractPresenter presenter) {
        mPresenter = (ChangePhoneTwoPresenter) presenter;
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

    @OnClick({R.id.btn_login, R.id.tv_sfz, R.id.tv_get_yzm})
    public void onViewClicked(View view) {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_login:
               if (RxDataTool.isNullString(et3.getText().toString())){
                   ToastUtil.showCenterShort("登录密码不能为空");
                   return;
               }
                if (!et3.getText().toString().equals(RxSPTool.getString(this, ShareStatic.APP_LOGIN_MM))) {
                    ToastUtil.showCenterShort("登录密码错误");
                    return;
                }
                if (RxDataTool.isNullString(et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不能为空");
                    return;
                }
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_EXACT, et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不正确");
                    return;
                }
                if (RxDataTool.isNullString(et2.getText().toString())) {
                    ToastUtil.showCenterShort("验证码不能为空");
                    return;
                }

                if (!RxDataTool.isNullString(phone) && !phone.equals(et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码改变请重新获取验证码");
                    return;
                }
                if (RxDataTool.isNullString(code) || !(et2.getText().toString().trim().equals(code))) {
                    ToastUtil.showCenterShort("验证码错误");
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
                map.put("sjhm", et1.getText().toString().trim());
                map.put("mm", RxEncryptTool.encryptSHA1ToString(et3.getText().toString().trim() + RxSPTool.getString
                        (this, ShareStatic.APP_LOGIN_SJHM)));
                map.put("newmm", RxEncryptTool.encryptSHA1ToString(et3.getText().toString().trim() + et1.getText()
                        .toString()));
                mPresenter.changePhone(map);
                break;
            case R.id.tv_get_yzm:
                if (RxDataTool.isNullString(et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不能为空");
                    return;
                }
                if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_EXACT, et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码格式错误");
                    return;
                }

                mPresenter.getChangeCode(et1.getText().toString().trim());
                break;
            case R.id.tv_sfz:
                if (Contains.sAllInfo.getData() == null || Contains.sAllInfo.getData().getSmyz() == null || !Contains
                        .sAllInfo.getData().getSmyz().getZt().equals("3")) {
                    showDialog();
                    return;
                }
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(AppConfig.getInstance())
                        .getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            default:
                break;
        }
    }

    private String fileRealPath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                showProgressDialog();
                final File tempImage = new File(this.getCacheDir(), "idcard_zheng1.jpg");
                Logger.e("file size1" + tempImage.length());
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                        .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
                fileRealPath = tempImage.getAbsolutePath();
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
                    startActivty(ChangePhoneThirdActivity.class);
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

    /**
     * 用明文ak，sk初始化
     */
    String token;
    private boolean hasGotToken = false;

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            //Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

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
                        CameraNativeHelper.init(ChangePhoneTwoActivity.this, OCR.getInstance(ChangePhoneTwoActivity
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
        }, getApplicationContext(), Contains.OCR_AK, Contains.OCR_SK);
    }

    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        if (IDcardQualityProcess.getInstance() != null) {
            CameraNativeHelper.release();
        }
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    public void getChangeCodeSuccess(CommonBean baseEntity) {
        if (baseEntity.getCode() == Contains.REQUEST_SUCCESS) {
            if (baseEntity.getData() != null) {
                code = baseEntity.getData();
                phone = et1.getText().toString();
                tvGetYzm.setEnabled(false);
                tvGetYzm.setTextColor(getResources().getColor(R.color.color_939393));
                countDownTimer.start();
            }

        } else {
            onErrorMsg(baseEntity.code, baseEntity.getMsg());
        }
    }

    @Override
    public void changePhoneSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ClearUtils.clearRxSp(this);
            ToastUtil.showCenterShort("手机号码修改成功");
            startActivty(LoginActivity.class);
            AppManager.getInstance().finishAllActivity();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    private void showDialog() {
        final SureDialog dialog = new SureDialog(this);
        dialog.getContentView().setText("您尚未实名认证\n不能通过实名认证更换手机号码");
        dialog.getCancelView().setVisibility(View.GONE);
        dialog.getLine().setVisibility(View.GONE);
        dialog.getCancelView().setTextColor(getResources().getColor(R.color.color_333333));
        dialog.getSureView().setText("确定");
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                AppManager.getInstance().finishActivity(ChangePhoneOneActivity.class);
//                startActivty(AboutCertificationActivity.class);
//                dialog.dismiss();
//                finish();
            }
        });
        dialog.show();
    }

}