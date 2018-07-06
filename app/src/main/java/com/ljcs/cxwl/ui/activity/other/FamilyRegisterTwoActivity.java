package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.ocr.sdk.utils.ImageUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterTwoComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwoContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwoModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwoPresenter;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxKeyboardTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.RxTool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_BASIC;
import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_JHZ;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/06/27 19:05:45
 */

public class FamilyRegisterTwoActivity extends BaseActivity implements FamilyRegisterTwoContract.View {

    @Inject
    FamilyRegisterTwoPresenter mPresenter;
    @BindView(R.id.tv_leixing1)
    TextView tvLeixing1;
    @BindView(R.id.tv_leixing2)
    TextView tvLeixing2;
    @BindView(R.id.tv_leixing3)
    TextView tvLeixing3;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_ethnic)
    TextView tvEthnic;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.tv_idcard)
    TextView tvIdcard;
    @BindView(R.id.tv_issueAuthority)
    TextView tvIssueAuthority;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.imageView_zheng)
    ImageView imageViewZheng;
    @BindView(R.id.imageView_fan)
    ImageView imageViewFan;
    @BindView(R.id.img_upload1)
    ImageView imgUpload1;
    @BindView(R.id.img_upload2)
    ImageView imgUpload2;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;


    private String imgPath1;
    private String imgPath2;
    private OptionsPickerView mOptionsPickerView;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private boolean isHavePic1 = false;
    private boolean isHavePic2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("添加配偶信息");
    }

    @Override
    protected void initData() {
        list1.add("城市");
        list1.add("农村");
        list2.add("集体户口");
        list2.add("家庭户口");
        list3.add("已婚");
//        list3.add("未婚");
        list3.add("离异");
        list3.add("丧偶");
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null) {
            //身份证等信息
            tvName.setText(Contains.sAllInfo.getData().getPoxx().getXm());
            tvSex.setText(Contains.sAllInfo.getData().getPoxx().getXb());
            tvEthnic.setText(Contains.sAllInfo.getData().getPoxx().getMz());
            tvBirthday.setText(Contains.sAllInfo.getData().getPoxx().getCsrq());
            tvAdress.setText(Contains.sAllInfo.getData().getPoxx().getDz());
            tvIdcard.setText(Contains.sAllInfo.getData().getPoxx().getSfzhm());
            tvIssueAuthority.setText(Contains.sAllInfo.getData().getPoxx().getQfjg());
            tvData.setText(Contains.sAllInfo.getData().getPoxx().getYxq());
            //户籍等信息
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getSfzzm()).into(imageViewZheng);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getSfzfm()).into(imageViewFan);
            tvLeixing1.setText(Contains.sAllInfo.getData().getPoxx().getHklx());
            tvLeixing2.setText(Contains.sAllInfo.getData().getPoxx().getHkxz());
            tvLeixing3.setText(Contains.sAllInfo.getData().getPoxx().getHyzt());
            //表示有图片 不需要七牛在上传了
            if (!RxDataTool.isNullString(Contains.sAllInfo.getData().getPoxx().getHkzp())) {
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getHkzp()).into(imgUpload1);
                imageView5.setVisibility(View.VISIBLE);
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp()).into(imgUpload2);
                imageView6.setVisibility(View.VISIBLE);
                imgPath1 = API.PIC + Contains.sAllInfo.getData().getPoxx().getHkzp();
                imgPath2 = API.PIC + Contains.sAllInfo.getData().getPoxx().getJhzzp();
                isHavePic1 = true;
                isHavePic2 = true;
            }

        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterTwoComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterTwoModule(new FamilyRegisterTwoModule(this)).build().inject
                (this);
    }

    @Override
    public void setPresenter(FamilyRegisterTwoContract.FamilyRegisterTwoContractPresenter presenter) {
        mPresenter = (FamilyRegisterTwoPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.btn_login, R.id.img_upload1, R.id.img_upload2, R.id.imageView5, R.id.imageView6, R.id
            .layout_select1, R.id.layout_select2, R.id.layout_select3})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkText()) {
                    if (isHavePic1 && isHavePic2) {
                        //表示两张照片都不需要上传
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("hklx", tvLeixing1.getText().toString());
                        map.put("token", RxSPTool.getString(FamilyRegisterTwoActivity.this, ShareStatic
                                .APP_LOGIN_TOKEN));
                        map.put("hkxz", tvLeixing2.getText().toString());
                        map.put("hyzt", tvLeixing3.getText().toString());
                        mPresenter.matesInfo(map);
                    } else {
                        mPresenter.getQiniuToken();
                    }
                }
                break;
            case R.id.img_upload1:
                //户口本证件照
                if (imageView5.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(FamilyRegisterTwoActivity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
                } else {
                    intent = new Intent(FamilyRegisterTwoActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath1);
                    startActivity(intent);
                }
                break;
            case R.id.img_upload2:

                //结婚证证件照
                if (imageView6.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(FamilyRegisterTwoActivity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_JHZ);
                } else {
                    intent = new Intent(FamilyRegisterTwoActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath2);
                    startActivity(intent);
                }
                break;
            case R.id.imageView5:
                imageView5.setVisibility(View.INVISIBLE);
                imgPath1 = "";
                isHavePic1 = false;
                Glide.with(FamilyRegisterTwoActivity.this).load(R.mipmap.ic_img2).into(imgUpload1);
                break;
            case R.id.imageView6:
                imageView6.setVisibility(View.INVISIBLE);
                isHavePic2 = false;
                imgPath2 = "";
                Glide.with(FamilyRegisterTwoActivity.this).load(R.mipmap.ic_img3).into(imgUpload2);
                break;
            case R.id.layout_select1:
                showSelectPickerView(1, list1);
                break;
            case R.id.layout_select2:
                showSelectPickerView(2, list2);
                break;
            case R.id.layout_select3:
                showSelectPickerView(3, list3);
                break;
            default:
                break;
        }

    }

    /**
     * 检查字段
     *
     * @return
     */
    private boolean checkText() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return false;
        }
        if (RxDataTool.isNullString(tvLeixing1.getText().toString())) {
            ToastUtil.showCenterShort("请选择户口类型");
            return false;
        }

        if (RxDataTool.isNullString(tvLeixing2.getText().toString())) {
            ToastUtil.showCenterShort("请选择户口性质");
            return false;
        }
        if (RxDataTool.isNullString(tvLeixing3.getText().toString())) {
            ToastUtil.showCenterShort("请选择婚姻状况");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterTwoActivity.this.getCacheDir(), "household_peiou");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath1 = tempImage.getAbsolutePath();
            imgUpload1.setImageBitmap(BitmapFactory.decodeFile(tempImage.getAbsolutePath()));
            Contains.sCertificationInfo.setPic_path_hk_peiou(tempImage.getAbsolutePath());
            imageView5.setVisibility(View.VISIBLE);

        }
        if (requestCode == REQUEST_CODE_GENERAL_JHZ && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterTwoActivity.this.getCacheDir(), "jhz");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath2 = tempImage.getAbsolutePath();
            imgUpload2.setImageBitmap(BitmapFactory.decodeFile(tempImage.getAbsolutePath()));
            Contains.sCertificationInfo.setPic_path_jhz_peiou(tempImage.getAbsolutePath());
            imageView6.setVisibility(View.VISIBLE);
        }

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
                        tvLeixing1.setText(list1.get(options1));
                        Contains.sCertificationInfo.setLeixing1_peiou(list1.get(options1));
                        break;
                    case 2:
                        //户口类型
                        tvLeixing2.setText(list2.get(options1));
                        Contains.sCertificationInfo.setLeixing2_peiou(list2.get(options1));

                        break;
                    case 3:
                        //婚姻状况
                        tvLeixing3.setText(list3.get(options1));
                        Contains.sCertificationInfo.setLeixing3_peiou(list3.get(options1));
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
            mOptionsPickerView.setTitleText("户籍类型");
        } else if (flag == 2) {
            mOptionsPickerView.setTitleText("家庭户口类型");
        } else {
            mOptionsPickerView.setTitleText("婚姻状况");
        }

        mOptionsPickerView.show();
    }

    @Override
    public void matesInfoSuccess(MatesInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort(baseEntity.msg);
            startActivty(FamilyRegisterThirdActivity.class);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            List<String> list = new ArrayList<>();
            if (!isHavePic1) {
                list.add(imgPath1);
            }
            if (!isHavePic2) {
                list.add(imgPath2);
            }
            QiniuUploadUtil.uploadPics(list, qiniuToken.getUptoken(), new UploadCallback() {

                @Override
                public void sucess(String url) {

                }

                @Override
                public void sucess(List<String> url) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("hklx", tvLeixing1.getText().toString());
                    map.put("token", RxSPTool.getString(FamilyRegisterTwoActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("hkxz", tvLeixing2.getText().toString());
                    map.put("hyzt", tvLeixing3.getText().toString());
                    if (!isHavePic1 && isHavePic2) {
                        map.put("hkzp", url.get(0));
                    }
                    if (isHavePic1 && !isHavePic2) {
                        map.put("jhzzp", url.get(0));
                    }
                    if (url != null && url.size() > 1) {
                        map.put("hkzp", url.get(0));
                        map.put("jhzzp", url.get(1));
                    }

                    mPresenter.matesInfo(map);
                }

                @Override
                public void fail(String key, ResponseInfo info) {
                    closeProgressDialog();
                }
            });
        }

    }
}