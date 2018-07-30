package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.ProvinceBean;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterTwo1Component;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwo1Contract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwo1Module;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwo1Presenter;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.GlideUtils;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxConstTool;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxKeyboardTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.OCR_AK;
import static com.ljcs.cxwl.contain.Contains.OCR_SK;
import static com.ljcs.cxwl.contain.Contains.REGEX_MOBILE_EXACT;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_CAMERA_FAN;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_BASIC;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_JHZ;
import static com.ljcs.cxwl.contain.Contains.sCertificationInfo;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/07/11 15:00:45
 */

public class FamilyRegisterTwo1Activity extends BaseActivity implements FamilyRegisterTwo1Contract.View {

    @Inject FamilyRegisterTwo1Presenter mPresenter;
    @BindView(R.id.tv_leixing1) TextView tvLeixing1;
    @BindView(R.id.tv_leixing2) TextView tvLeixing2;
    @BindView(R.id.tv_name) EditText tvName;
    @BindView(R.id.tv_sex) EditText tvSex;
    @BindView(R.id.tv_ethnic) EditText tvEthnic;
    @BindView(R.id.tv_birthday) EditText tvBirthday;
    @BindView(R.id.tv_adress) EditText tvAdress;
    @BindView(R.id.tv_idcard) EditText tvIdcard;
    @BindView(R.id.tv_issueAuthority) EditText tvIssueAuthority;
    @BindView(R.id.tv_data1) EditText tvData1;
    @BindView(R.id.tv_data2) EditText tvData2;
    @BindView(R.id.layout_content1) LinearLayout layoutContent1;
    @BindView(R.id.layout_content2) LinearLayout layoutContent2;
    @BindView(R.id.layout_bottom) LinearLayout layoutBottom;
    @BindView(R.id.img_chongpai1) ImageView imgChongpai1;
    @BindView(R.id.img_chongpai2) ImageView imgChongpai2;
    @BindView(R.id.imageView_zheng) ImageView imageViewZheng;
    @BindView(R.id.imageView_fan) ImageView imageViewFan;
    @BindView(R.id.img_upload1) ImageView imgUpload1;
    @BindView(R.id.img_upload2) ImageView imgUpload2;
    @BindView(R.id.imageView5) ImageView imageView5;
    @BindView(R.id.imageView6) ImageView imageView6;
    @BindView(R.id.tv_hjszd) TextView tvHjszd;
    @BindView(R.id.tv_phone) EditText tvPhone;
    List<String> list = new ArrayList<>();
    List<String> listUrl = new ArrayList<>();
    Map<String, String> map = new HashMap<String, String>();
    private TimePickerView pvTime;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean hasGotToken = false;
    private String fileRealPath1;
    private String fileRealPath2;
    private String imgPath1;
    private String imgPath2;
    private OptionsPickerView mOptionsPickerView;
    private List<String> list2 = new ArrayList<>();
    private boolean isHavePic1 = false;
    private boolean isHavePic2 = false;
    private boolean isHavePic3 = false;
    private boolean isHavePic4 = false;
    private int opt1, opt2, opt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_two1);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
        //初始化ocr服务
        initAccessTokenWithAkSk();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();
    }

    @Override
    protected void initData() {
        list2.add("集体户口");
        list2.add("家庭户口");
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null) {
            if (Contains.sAllInfo.getData().getPoxx().getSfz() != null) {
                layoutContent1.setVisibility(View.VISIBLE);
                layoutContent2.setVisibility(View.VISIBLE);
                //身份证等信息
                tvName.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getXm());
                tvSex.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getXb());
                tvEthnic.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getMz());
                tvBirthday.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getCsrq());
                tvAdress.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getZz());
                tvIdcard.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getZjhm());
                tvIssueAuthority.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getQfjg());
                if (Contains.sAllInfo.getData().getPoxx().getSfz().getYxq() != null && Contains.sAllInfo.getData()
                        .getPoxx().getSfz().getYxq().contains("-")) {
                    tvData1.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getYxq().split("-")[0]);
                    tvData2.setText(Contains.sAllInfo.getData().getPoxx().getSfz().getYxq().split("-")[1]);
                }


            }
            if (Contains.sAllInfo.getData().getPoxx().getJtcy() != null) {
                //户籍等信息
                tvLeixing2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getHjfl());
                tvHjszd.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getHjszd());
                tvPhone.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getLxdh());
            }
            //表示有图片 不需要七牛在上传了
            if (Contains.sAllInfo.getData().getPoxx().getZzxx() != null) {
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getHkb()).into
                        (imgUpload1);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getJhz()).into
                        (imgUpload2);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzzm()).into
                        (imageViewZheng);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzfm()).into
                        (imageViewFan);
                imageView5.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.VISIBLE);
                imgChongpai1.setVisibility(View.VISIBLE);
                imgChongpai2.setVisibility(View.VISIBLE);

                imgPath1 = API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getHkb();
                imgPath2 = API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getJhz();
                fileRealPath1 = API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzzm();
                fileRealPath2 = API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzfm();
                isHavePic1 = true;
                isHavePic2 = true;
                isHavePic3 = true;
                isHavePic4 = true;
            }

        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterTwo1Component.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterTwo1Module(new FamilyRegisterTwo1Module(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterTwo1Contract.FamilyRegisterTwo1ContractPresenter presenter) {
        mPresenter = (FamilyRegisterTwo1Presenter) presenter;
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
    public void matesInfoSuccess(MatesInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            //ToastUtil.showCenterShort(baseEntity.msg);
            startActivty(FamilyRegisterThirdActivity.class);
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

    @OnClick({R.id.imageView_zheng, R.id.imageView_fan, R.id.btn_login, R.id.img_chongpai1, R.id.img_chongpai2, R.id
            .img_upload1, R.id.img_upload2, R.id.imageView5, R.id.imageView6, R.id.layout_select1, R.id
            .layout_select2, R.id.layout_select5})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_zheng:
                if (!checkTokenStatus()) {
                    return;
                }
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
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
                    intent = new Intent(FamilyRegisterTwo1Activity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", fileRealPath1);
                    startActivity(intent);
                }
                break;
            case R.id.imageView_fan:
                if (!checkTokenStatus()) {
                    return;
                }
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
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
                    intent = new Intent(FamilyRegisterTwo1Activity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", fileRealPath2);
                    startActivity(intent);
                }
                break;
            case R.id.img_chongpai1:
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
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
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
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
            case R.id.img_upload1:
                //户口本证件照
                if (imageView5.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(FamilyRegisterTwo1Activity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
                } else {
                    intent = new Intent(FamilyRegisterTwo1Activity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath1);
                    startActivity(intent);
                }
                break;
            case R.id.img_upload2:

                //结婚证证件照
                if (imageView6.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(FamilyRegisterTwo1Activity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_JHZ);
                } else {
                    intent = new Intent(FamilyRegisterTwo1Activity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath2);
                    startActivity(intent);
                }
                break;
            case R.id.imageView5:
                imageView5.setVisibility(View.INVISIBLE);
                imgPath1 = "";
                isHavePic1 = false;
                Glide.with(FamilyRegisterTwo1Activity.this).load(R.mipmap.ic_img2).into(imgUpload1);
                break;
            case R.id.imageView6:
                imageView6.setVisibility(View.INVISIBLE);
                isHavePic2 = false;
                imgPath2 = "";
                Glide.with(FamilyRegisterTwo1Activity.this).load(R.mipmap.ic_img3).into(imgUpload2);
                break;
            case R.id.layout_select1:
                //showSelectPickerView(1, list1);
                break;
            case R.id.layout_select2:
                showSelectPickerView(2, list2);
                break;
            case R.id.btn_login:
                if (checkText()) {
                    showProgressDialog();
                    if (isHavePic1 && isHavePic2 && isHavePic3 && isHavePic4) {
                        //表示不需要上传
                        map.put("token", RxSPTool.getString(FamilyRegisterTwo1Activity.this, ShareStatic
                                .APP_LOGIN_TOKEN));
                        map.put("zjhm", tvIdcard.getText().toString());
                        map.put("lxdh", tvPhone.getText().toString());
                        map.put("mz", tvEthnic.getText().toString());
                        map.put("csrq", tvBirthday.getText().toString());
                        map.put("xm", tvName.getText().toString());
                        map.put("xb", tvSex.getText().toString());
                        map.put("zz", tvAdress.getText().toString());
                        map.put("qfjg", tvIssueAuthority.getText().toString());
                        map.put("yxq", tvData1.getText().toString() + "-" + tvData2.getText().toString());
                        map.put("hjfl", tvLeixing2.getText().toString());
                        map.put("hjszd", tvHjszd.getText().toString());
                        map.put("hkb", Contains.sAllInfo.getData().getPoxx().getZzxx().getHkb());
                        map.put("jhz", Contains.sAllInfo.getData().getPoxx().getZzxx().getJhz());
                        map.put("sfzzm", Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzzm());
                        map.put("sfzfm", Contains.sAllInfo.getData().getPoxx().getZzxx().getSfzfm());
                        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                                Contains.sAllInfo.getData().getPoxx().getJtcy() != null && Contains.sAllInfo.getData
                                ().getPoxx().getJtcy().getYhbh() != null) {
                            map.put("yhbh", Contains.sAllInfo.getData().getPoxx().getJtcy().getYhbh());
                        }
                        mPresenter.matesInfo(map);
                    } else {
                        list.clear();
                        checkPics();
                        mPresenter.uploadPic(list, new UploadFileCallBack() {
                            @Override
                            public void sucess(List<String> url) {
                                listUrl.clear();
                                map.clear();
                                listUrl.addAll(url);
                                map.put("token", RxSPTool.getString(FamilyRegisterTwo1Activity.this, ShareStatic
                                        .APP_LOGIN_TOKEN));
                                map.put("zjhm", tvIdcard.getText().toString());
                                map.put("lxdh", tvPhone.getText().toString());
                                map.put("mz", tvEthnic.getText().toString());
                                map.put("csrq", tvBirthday.getText().toString());
                                map.put("xm", tvName.getText().toString());
                                map.put("xb", tvSex.getText().toString());
                                checkMap();
                                map.put("zz", tvAdress.getText().toString());
                                map.put("qfjg", tvIssueAuthority.getText().toString());
                                map.put("yxq", tvData1.getText().toString() + "-" + tvData2.getText().toString());
                                map.put("hjfl", tvLeixing2.getText().toString());
                                map.put("hjszd", tvHjszd.getText().toString());
                                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() !=
                                        null && Contains.sAllInfo.getData().getPoxx().getJtcy() != null && Contains
                                        .sAllInfo.getData().getPoxx().getJtcy().getYhbh() != null) {
                                    map.put("yhbh", Contains.sAllInfo.getData().getPoxx().getJtcy().getYhbh());
                                }
                                mPresenter.matesInfo(map);
                            }

                            @Override
                            public void fail(String msg) {
                                closeProgressDialog();
                                // ToastUtil.showCenterShort(msg);
                            }
                        });
                    }
                }
                break;
            case R.id.layout_select5:
                showPickerView();
                break;
            default:
                break;
        }
    }

    private void checkMap() {


        Logger.e("time3" + System.currentTimeMillis());
        if (!isHavePic1 && !isHavePic2 && !isHavePic3 && !isHavePic4) {

            map.put("hkb", listUrl.get(0));
            map.put("jhz", listUrl.get(1));
            map.put("sfzzm", listUrl.get(2));
            map.put("sfzfm", listUrl.get(3));
        } else if (isHavePic1 && !isHavePic2 && !isHavePic3 && !isHavePic4) {

            map.put("jhz", listUrl.get(0));
            map.put("sfzzm", listUrl.get(1));
            map.put("sfzfm", listUrl.get(2));
        } else if (!isHavePic1 && isHavePic2 && !isHavePic3 && !isHavePic4) {

            map.put("hkb", listUrl.get(0));
            map.put("sfzzm", listUrl.get(1));
            map.put("sfzfm", listUrl.get(2));
        } else if (!isHavePic1 && !isHavePic2 && isHavePic3 && !isHavePic4) {
            map.put("hkb", listUrl.get(0));
            map.put("jhz", listUrl.get(1));
            map.put("sfzfm", listUrl.get(2));
        } else if (!isHavePic1 && !isHavePic2 && !isHavePic3 && isHavePic4) {

            map.put("hkb", listUrl.get(0));
            map.put("jhz", listUrl.get(1));
            map.put("sfzzm", listUrl.get(2));
        } else if (!isHavePic1 && !isHavePic2 && isHavePic3 && isHavePic4) {

            map.put("hkb", listUrl.get(0));
            map.put("jhz", listUrl.get(1));
        } else if (!isHavePic1 && isHavePic2 && !isHavePic3 && isHavePic4) {
            map.put("hkb", listUrl.get(0));
            map.put("sfzzm", listUrl.get(1));
        } else if (!isHavePic1 && isHavePic2 && isHavePic3 && !isHavePic4) {
            list.add(imgPath1);
            list.add(fileRealPath2);
            map.put("hkb", listUrl.get(0));
            map.put("sfzfm", listUrl.get(1));

        } else if (isHavePic1 && !isHavePic2 && !isHavePic3 && isHavePic4) {
            map.put("jhz", listUrl.get(0));
            map.put("sfzzm", listUrl.get(1));

        } else if (isHavePic1 && !isHavePic2 && isHavePic3 && !isHavePic4) {
            map.put("jhz", listUrl.get(0));
            map.put("sfzfm", listUrl.get(1));

        } else if (isHavePic1 && isHavePic2 && !isHavePic3 && !isHavePic4) {
            map.put("sfzzm", listUrl.get(0));
            map.put("sfzfm", listUrl.get(1));

        } else if (!isHavePic1 && isHavePic2 && isHavePic3 && isHavePic4) {
            map.put("hkb", listUrl.get(0));
        } else if (isHavePic1 && !isHavePic2 && isHavePic3 && isHavePic4) {
            map.put("jhz", listUrl.get(0));

        } else if (isHavePic1 && isHavePic2 && !isHavePic3 && isHavePic4) {
            map.put("sfzzm", listUrl.get(0));

        } else if (isHavePic1 && isHavePic2 && isHavePic3 && !isHavePic4) {
            map.put("sfzfm", listUrl.get(0));

        }
        Logger.e("time4" + System.currentTimeMillis());
    }

    private void checkPics() {
        Logger.e("time1" + System.currentTimeMillis());
        if (!isHavePic1 && !isHavePic2 && !isHavePic3 && !isHavePic4) {
            list.add(imgPath1);
            list.add(imgPath2);
            list.add(fileRealPath1);
            list.add(fileRealPath2);
        } else if (isHavePic1 && !isHavePic2 && !isHavePic3 && !isHavePic4) {
            list.add(imgPath2);
            list.add(fileRealPath1);
            list.add(fileRealPath2);
        } else if (!isHavePic1 && isHavePic2 && !isHavePic3 && !isHavePic4) {
            list.add(imgPath1);
            list.add(fileRealPath1);
            list.add(fileRealPath2);
        } else if (!isHavePic1 && !isHavePic2 && isHavePic3 && !isHavePic4) {
            list.add(imgPath1);
            list.add(imgPath2);
            list.add(fileRealPath2);
        } else if (!isHavePic1 && !isHavePic2 && !isHavePic3 && isHavePic4) {
            list.add(imgPath1);
            list.add(imgPath2);
            list.add(fileRealPath1);

        } else if (!isHavePic1 && !isHavePic2 && isHavePic3 && isHavePic4) {

            list.add(imgPath1);
            list.add(imgPath2);
        } else if (!isHavePic1 && isHavePic2 && !isHavePic3 && isHavePic4) {
            list.add(imgPath1);
            list.add(fileRealPath1);

        } else if (!isHavePic1 && isHavePic2 && isHavePic3 && !isHavePic4) {
            list.add(imgPath1);
            list.add(fileRealPath2);

        } else if (isHavePic1 && !isHavePic2 && !isHavePic3 && isHavePic4) {
            list.add(imgPath2);
            list.add(fileRealPath1);


        } else if (isHavePic1 && !isHavePic2 && isHavePic3 && !isHavePic4) {
            list.add(imgPath2);
            list.add(fileRealPath2);

        } else if (isHavePic1 && isHavePic2 && !isHavePic3 && !isHavePic4) {
            list.add(fileRealPath1);
            list.add(fileRealPath2);

        } else if (!isHavePic1 && isHavePic2 && isHavePic3 && isHavePic4) {
            list.add(imgPath1);

        } else if (isHavePic1 && !isHavePic2 && isHavePic3 && isHavePic4) {
            list.add(imgPath2);

        } else if (isHavePic1 && isHavePic2 && !isHavePic3 && isHavePic4) {
            list.add(fileRealPath1);

        } else if (isHavePic1 && isHavePic2 && isHavePic3 && !isHavePic4) {
            list.add(fileRealPath2);

        }
        Logger.e("time2" + System.currentTimeMillis());
    }

    private boolean checkText() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return false;
        }
        if (RxDataTool.isNullString(fileRealPath1)) {
            ToastUtil.showCenterShort("请拍摄身份证正面照");
            return false;
        }
        if (RxDataTool.isNullString(fileRealPath2)) {
            ToastUtil.showCenterShort("请拍摄身份证反面照");
            return false;
        }
        if (RxDataTool.isNullString(tvName.getText().toString())) {
            ToastUtil.showCenterShort("姓名不能为空");
            return false;
        }
        if (RxDataTool.isNullString(tvSex.getText().toString())) {
            ToastUtil.showCenterShort("性别不能为空");
            return false;
        }
        if ((!tvSex.getText().toString().equals("男") && !tvSex.getText().toString().equals("女"))) {
            ToastUtil.showCenterShort("请输入正确的性别");
            return false;
        }

        if (RxDataTool.isNullString(tvEthnic.getText().toString())) {
            ToastUtil.showCenterShort("民族不能为空");
            return false;
        }
        if (RxDataTool.isNullString(tvBirthday.getText().toString())) {
            ToastUtil.showCenterShort("出生格式不正确");
            return false;
        }

        if (RxDataTool.isNullString(tvAdress.getText().toString())) {
            ToastUtil.showCenterShort("住址不能为空");
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
        if ((IDcardUtil.getAge(tvIdcard.getText().toString()) < 22 && tvSex.getText().toString().equals("男")) ||
                (IDcardUtil.getAge(tvIdcard.getText().toString()) < 20 && tvSex.getText().toString().equals("女"))) {
            ToastUtil.showCenterShort("非适婚年龄");
            return false;
        }
        if (RxDataTool.isNullString(tvIssueAuthority.getText().toString())) {
            ToastUtil.showCenterShort("签发机关不能为空");
            return false;
        }
        if (RxDataTool.isNullString(tvData1.getText().toString())) {
            ToastUtil.showCenterShort("有效期限不能为空");
            return false;
        }
        if (RxDataTool.isNullString(tvData2.getText().toString())) {
            ToastUtil.showCenterShort("有效期限不能为空");
            return false;
        }
//        if (RxDataTool.isNullString(tvLeixing1.getText().toString())) {
//            ToastUtil.showCenterShort("请选择户籍类型");
//            return false;
//        }

        if (RxDataTool.isNullString(tvLeixing2.getText().toString())) {
            ToastUtil.showCenterShort("请选择家庭户口类型");
            return false;
        }
        if (RxDataTool.isNullString(tvHjszd.getText().toString())) {
            ToastUtil.showCenterShort("请选择户籍所在地");
            return false;
        }
        if (!StringUitl.isMatch(REGEX_MOBILE_EXACT, tvPhone.getText().toString())) {
            ToastUtil.showCenterShort("手机号码不正确");
            return false;
        }
        if (RxDataTool.isNullString(imgPath1) && !isHavePic1) {
            ToastUtil.showCenterShort("请上传户口本配偶页面");
            return false;
        }
        if (RxDataTool.isNullString(imgPath2) && !isHavePic2) {
            ToastUtil.showCenterShort("请上传结婚证内页");
            return false;
        }
        return true;
    }

    private void showSelectPickerView(final int flag, List<String> list) {
        RxKeyboardTool.hideSoftInput(this);
        mOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Logger.i(options1 + "--" + options2 + "--" + options3);
                switch (flag) {
                    case 1:
                        //户籍类型
//                        tvLeixing1.setText(list1.get(options1));
//                        Contains.sCertificationInfo.setLeixing1_peiou(list1.get(options1));
                        break;
                    case 2:
                        //户口类型
                        tvLeixing2.setText(list2.get(options1));
                        Contains.sCertificationInfo.setLeixing2_peiou(list2.get(options1));

                        break;
                    default:
                        break;
                }
            }
        })
                // .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
//                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        mOptionsPickerView.setPicker(list);
        if (flag == 1) {
            mOptionsPickerView.setTitleText("请选择户籍类型");
        } else if (flag == 2) {
            mOptionsPickerView.setTitleText("请选择家庭户口类型");
        }
        mOptionsPickerView.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                isHavePic3 = false;
                final File tempImage = new File(this.getCacheDir(), "idcard_zheng_peiou.jpg");
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
                isHavePic4 = false;
                final File tempImage = new File(this.getCacheDir(), "idcard_fan_peiou.jpg");
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
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterTwo1Activity.this.getCacheDir(), "household_peiou.jpg");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath1 = tempImage.getAbsolutePath();

            imgUpload1.setImageBitmap(BitmapFactory.decodeFile(tempImage.getAbsolutePath()));
            Contains.sCertificationInfo.setPic_path_hk_peiou(tempImage.getAbsolutePath());
            imageView5.setVisibility(View.VISIBLE);

        }
        if (requestCode == REQUEST_CODE_GENERAL_JHZ && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterTwo1Activity.this.getCacheDir(), "jhz.jpg");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath2 = tempImage.getAbsolutePath();
            imgUpload2.setImageBitmap(BitmapFactory.decodeFile(tempImage.getAbsolutePath()));
            Contains.sCertificationInfo.setPic_path_jhz_peiou(tempImage.getAbsolutePath());
            imageView6.setVisibility(View.VISIBLE);
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
                            layoutBottom.setVisibility(View.VISIBLE);
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
                        GlideUtils.loadImgNoCach(FamilyRegisterTwo1Activity.this, Contains.sCertificationInfo
                                .getPic_path_zheng(), imageViewZheng);

                        // imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo
                        // .getPic_path_zheng()));
                    } else if (idCardSide.equals(IDCardParams.ID_CARD_SIDE_BACK)) {
                        layoutContent2.setVisibility(View.VISIBLE);
                        imgChongpai2.setVisibility(View.VISIBLE);
                        if (layoutContent1.getVisibility() == View.VISIBLE && layoutContent2.getVisibility() == View
                                .VISIBLE) {
                            layoutBottom.setVisibility(View.VISIBLE);
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
                        GlideUtils.loadImgNoCach(FamilyRegisterTwo1Activity.this, Contains.sCertificationInfo
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
                        CameraNativeHelper.init(FamilyRegisterTwo1Activity.this, OCR.getInstance
                                (FamilyRegisterTwo1Activity
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
                                Logger.i("CameraNativeHelper.init" + msg);
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

    private void showPickerView() {// 弹出选择器
        RxKeyboardTool.hideSoftInput(this);
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                opt1 = options1;
                opt2 = options2;
                opt3 = options3;
                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get
                        (options2) + options3Items.get(options1).get(options2).get(options3);
                tvHjszd.setText(tx.trim());
            }
        }).setSelectOptions(opt1, opt2, opt3).setTitleText("请选择户籍所在地").build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String wuyeString = "";
        AssetManager assetManager = AppConfig.getInstance().getAssets();
        try {
            InputStream is = assetManager.open("province.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            wuyeString = null;
            while ((wuyeString = br.readLine()) != null) {
                stringBuffer.append(wuyeString);
            }
            wuyeString = stringBuffer.toString();
            if (wuyeString != null) {
                Logger.i(wuyeString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ProvinceBean> jsonBean = parseData(wuyeString);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null || jsonBean.get(i).getCityList().get(c)
                        .getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }


    }


    public ArrayList<ProvinceBean> parseData(String result) {//Gson 解析
        ArrayList<ProvinceBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeProgressDialog();
    }
}