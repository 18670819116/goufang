package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.utils.ImageUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterTwo2Component;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwo2Contract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwo2Module;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwo2Presenter;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxKeyboardTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.RxTool;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_BASIC;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_JHZ;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_LHZ;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/07/10 10:43:02
 */

public class FamilyRegisterTwo2Activity extends BaseActivity implements FamilyRegisterTwo2Contract.View {

    @Inject
    FamilyRegisterTwo2Presenter mPresenter;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.layout_select1)
    LinearLayout layoutSelect1;
    @BindView(R.id.tv_leixing2)
    TextView tvLeixing2;
    @BindView(R.id.tv_idcard)
    EditText tvIdcard;
    @BindView(R.id.layout_select3)
    LinearLayout layoutSelect3;
    @BindView(R.id.img_upload1)
    ImageView imgUpload1;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.layout_select2)
    LinearLayout layoutSelect2;
    private TimePickerView pvTime;

    private boolean isHavePic1;
    private String imgPath1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_two2);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加前配偶信息");
        showTimeSelect();
    }

    @Override
    protected void initData() {
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null) {
            //身份证等信息
            etName.setText(Contains.sAllInfo.getData().getPoxx().getXm());
            tvIdcard.setText(Contains.sAllInfo.getData().getPoxx().getSfzhm());
            tvLeixing2.setText(Contains.sAllInfo.getData().getPoxx().getLhrq());
            //户籍等信息
            //表示有图片 不需要七牛在上传了
            if (!RxDataTool.isNullString(Contains.sAllInfo.getData().getPoxx().getJhzzp())) {
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp()).into(imgUpload1);
                imageView5.setVisibility(View.VISIBLE);
                imgPath1 = API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp();
                isHavePic1 = true;
            }

        }

    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterTwo2Component.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterTwo2Module(new FamilyRegisterTwo2Module(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterTwo2Contract.FamilyRegisterTwo2ContractPresenter presenter) {
        mPresenter = (FamilyRegisterTwo2Presenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }


    private boolean checkText() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return false;
        }
        if (RxDataTool.isNullString(etName.getText().toString())) {
            ToastUtil.showCenterShort("请输入前配偶姓名");
            return false;
        }

        if (RxDataTool.isNullString(tvLeixing2.getText().toString())) {
            ToastUtil.showCenterShort("请选择离异时间");
            return false;
        }
        if (RxDataTool.isNullString(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("请输入身份证号码");
            return false;
        }
        if (!IDcardUtil.is18IdCard(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("身份证号码格式有误");
            return false;
        }
        if (RxDataTool.isNullString(imgPath1) && !isHavePic1) {
            ToastUtil.showCenterShort("请上传离婚证");
            return false;
        }
        return true;
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(imgPath1, qiniuToken.getUptoken(), new UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("xm", etName.getText().toString());
                    map.put("token", RxSPTool.getString(FamilyRegisterTwo2Activity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("lhrq", tvLeixing2.getText().toString());
                    map.put("sfzhm", tvIdcard.getText().toString());
                    map.put("jhzzp", url);
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

    @Override
    public void matesInfoSuccess(MatesInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            //ToastUtil.showCenterShort(baseEntity.msg);
            startActivty(FamilyRegisterThirdActivity.class);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    private void showTimeSelect() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                Toast.makeText(FamilyRegisterTwo2Activity.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");
                tvLeixing2.setText(getTime(date));

            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTime", "onTimeSelectChanged");
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).setRangDate(startDate,endDate).setDate(endDate).build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvTime.setTitleText("请选择离异时间");

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    @OnClick({R.id.img_upload1, R.id.imageView5, R.id.btn_login, R.id.layout_select2})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_upload1:
                if (imageView5.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(FamilyRegisterTwo2Activity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_LHZ);
                } else {
                    intent = new Intent(FamilyRegisterTwo2Activity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath1);
                    startActivity(intent);
                }
                break;
            case R.id.imageView5:
                imageView5.setVisibility(View.INVISIBLE);
                imgPath1 = "";
                isHavePic1 = false;
                Glide.with(FamilyRegisterTwo2Activity.this).load(R.mipmap.ic_img5).into(imgUpload1);
                break;
            case R.id.layout_select2:
                RxKeyboardTool.hideSoftInput(this);
                pvTime.show();
                break;
            case R.id.btn_login:
                    if (checkText()) {
                        if (isHavePic1) {
                            //表示两张照片都不需要上传
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("xm", etName.getText().toString());
                            map.put("token", RxSPTool.getString(FamilyRegisterTwo2Activity.this, ShareStatic
                                    .APP_LOGIN_TOKEN));
                            map.put("lhrq", tvLeixing2.getText().toString());
                            map.put("sfzhm", tvIdcard.getText().toString());
                            mPresenter.matesInfo(map);
                        } else {
                            mPresenter.getQiniuToken();
                        }
                    }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_LHZ && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterTwo2Activity.this.getCacheDir(), "household_lhz");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath1 = tempImage.getAbsolutePath();
            imgUpload1.setImageBitmap(BitmapFactory.decodeFile(tempImage.getAbsolutePath()));
            imageView5.setVisibility(View.VISIBLE);

        }

    }
}