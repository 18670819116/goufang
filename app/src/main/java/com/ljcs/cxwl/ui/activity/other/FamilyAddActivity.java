package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyAddComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyAddContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyAddModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyAddPresenter;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxKeyboardTool;
import com.vondear.rxtools.RxRegTool;
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

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/07/02 10:34:10
 */

public class FamilyAddActivity extends BaseActivity implements FamilyAddContract.View {

    @Inject
    FamilyAddPresenter mPresenter;


    @BindView(R.id.tv_leixing1)
    TextView tvLeixing1;
    @BindView(R.id.tv_leixing2)
    TextView tvLeixing2;
    @BindView(R.id.tv_leixing3)
    TextView tvLeixing3;
    @BindView(R.id.tv_leixing4)
    TextView tvLeixing4;
    @BindView(R.id.tv_leixing5)
    TextView tvLeixing5;
    @BindView(R.id.img_upload)
    ImageView imgUpload;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_idcard)
    EditText tvIdcard;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    private int position = -1;
    private OptionsPickerView mOptionsPickerView;
    private String imgPath;
    private boolean isHavePic = false;
    List<String> list1 = new ArrayList<String>() {{
        add("男");
        add("女");
    }};
    List<String> list2 = new ArrayList<String>() {{
        add("城市");
        add("农村");
    }};
    List<String> list3 = new ArrayList<String>() {{
        add("集体户口");
        add("家庭户口");
    }};
    List<String> list4 = new ArrayList<String>() {{
        add("已婚");
        add("未婚");
        add("离异");
        add("丧偶");
    }};
    List<String> list5 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_add);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加家庭成员");
    }

    @Override
    protected void initData() {
        if (Contains.sAllInfo.getData().getSmyz().getXb().equals("男")) {
            list5.add("父子");
            list5.add("父女");
        } else {
            list5.add("母子");
            list5.add("母女");
        }
        if (getIntent().getIntExtra("type", 1) == 1) {
            //添加进来
        } else {
            //修改进来
            position = getIntent().getIntExtra("position", -1);
            tvName.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getXm());
            tvLeixing1.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getXb());
            tvLeixing2.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getHklx());
            tvLeixing3.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getHkxz());
            tvLeixing4.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getHyzt());
            tvLeixing5.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getGx());
            tvIdcard.setText(Contains.sAllInfo.getData().getJtcyList().get(position).getSfzhm());
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getJtcyList().get(position).getHkzp()).into
                    (imgUpload);
            imageView5.setVisibility(View.VISIBLE);
            imgPath = API.PIC + Contains.sAllInfo.getData().getJtcyList().get(position).getHkzp();
            isHavePic = true;
            tvDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(imgPath, qiniuToken.getUptoken(), new UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<>();
                    map.put("hklx", tvLeixing2.getText().toString());
                    map.put("token", RxSPTool.getString(FamilyAddActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("hkxz", tvLeixing3.getText().toString());
                    map.put("hyzt", tvLeixing4.getText().toString());
                    map.put("xm", tvName.getText().toString());
                    map.put("xb", tvLeixing1.getText().toString());
                    map.put("gx", tvLeixing5.getText().toString());
                    map.put("sfzhm", tvIdcard.getText().toString());
                    map.put("hkzp", url);
                    if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getJtcyList() != null &&
                            position != -1 && Contains.sAllInfo.getData().getJtcyList().get(position).getBh() != 0) {
                        map.put("bh", Contains.sAllInfo.getData().getJtcyList().get(position).getBh() + "");
                    } else {
                        map.put("bh", "");
                    }
                    mPresenter.matesInfoZinv(map);
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
    public void matesInfoDeleteSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort(baseEntity.msg);
            setResult(101);
            finish();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyAddComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .familyAddModule(new FamilyAddModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(FamilyAddContract.FamilyAddContractPresenter presenter) {
        mPresenter = (FamilyAddPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void matesInfoZinvSuccess(MatesInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort(baseEntity.msg);
            setResult(101);
            finish();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }

    }

    @OnClick({R.id.layout_select1, R.id.layout_select2, R.id.layout_select3, R.id.layout_select4, R.id
            .layout_select5, R.id.img_upload, R.id.imageView5, R.id.next, R.id.tv_delete})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.layout_select1:
                showSelectPickerView(1, list1);
                break;
            case R.id.layout_select2:
                showSelectPickerView(2, list2);
                break;
            case R.id.layout_select3:
                showSelectPickerView(3, list3);
                break;
            case R.id.layout_select4:
                showSelectPickerView(4, list4);
                break;
            case R.id.layout_select5:
                // showSelectPickerView(5, list5);
                break;
            case R.id.img_upload:
                if (imageView5.getVisibility() == View.INVISIBLE) {
                    intent = new Intent(FamilyAddActivity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
                } else {
                    intent = new Intent(FamilyAddActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath);
                    startActivity(intent);
                }
                break;
            case R.id.imageView5:
                imageView5.setVisibility(View.INVISIBLE);
                imgPath = "";
                isHavePic = false;
                Glide.with(FamilyAddActivity.this).load(R.mipmap.ic_img4).into(imgUpload);
                break;
            case R.id.next:
                if (checkText()) {
                    if (isHavePic) {
                        showProgressDialog();
                        Map<String, String> map = new HashMap<>();
                        map.put("hklx", tvLeixing2.getText().toString());
                        map.put("token", RxSPTool.getString(FamilyAddActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                        map.put("hkxz", tvLeixing3.getText().toString());
                        map.put("hyzt", tvLeixing4.getText().toString());
                        map.put("xm", tvName.getText().toString());
                        map.put("xb", tvLeixing1.getText().toString());
                        map.put("gx", tvLeixing5.getText().toString());
                        map.put("sfzhm", tvIdcard.getText().toString());
                        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getJtcyList() != null
                                && position != -1 && Contains.sAllInfo.getData().getJtcyList().get(position).getBh()
                                != 0) {
                            map.put("bh", Contains.sAllInfo.getData().getJtcyList().get(position).getBh() + "");
                        } else {
                            map.put("bh", "");
                        }
                        mPresenter.matesInfoZinv(map);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(imgPath);
                        mPresenter.uploadPic(list, new UploadFileCallBack() {
                            @Override
                            public void sucess(List<String> url) {
                                Map<String, String> map = new HashMap<>();
                                map.put("hklx", tvLeixing2.getText().toString());
                                map.put("token", RxSPTool.getString(FamilyAddActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                                map.put("hkxz", tvLeixing3.getText().toString());
                                map.put("hyzt", tvLeixing4.getText().toString());
                                map.put("xm", tvName.getText().toString());
                                map.put("xb", tvLeixing1.getText().toString());
                                map.put("gx", tvLeixing5.getText().toString());
                                map.put("sfzhm", tvIdcard.getText().toString());
                                map.put("hkzp", url.get(0));
                                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getJtcyList() != null &&
                                        position != -1 && Contains.sAllInfo.getData().getJtcyList().get(position).getBh() != 0) {
                                    map.put("bh", Contains.sAllInfo.getData().getJtcyList().get(position).getBh() + "");
                                } else {
                                    map.put("bh", "");
                                }
                                mPresenter.matesInfoZinv(map);
                            }

                            @Override
                            public void fail(String msg) {
                                closeProgressDialog();
                            }
                        });
                    }
                }
                break;
            case R.id.tv_delete:
                Map<String, String> map1 = new HashMap<>();
                map1.put("token", RxSPTool.getString(FamilyAddActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getJtcyList() != null &&
                        position != -1 && Contains.sAllInfo.getData().getJtcyList().get(position).getBh() != 0) {
                    map1.put("bh", Contains.sAllInfo.getData().getJtcyList().get(position).getBh() + "");
                } else {
                    map1.put("bh", "");
                }
                mPresenter.matesInfoDelete(map1);
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
            ToastUtil.showCenterShort("请输入姓名");
            return false;
        }
        if (RxDataTool.isNullString(tvLeixing1.getText().toString())) {
            ToastUtil.showCenterShort("请选择性别");
            return false;
        }

        if (RxDataTool.isNullString(tvLeixing2.getText().toString())) {
            ToastUtil.showCenterShort("请选择户籍类型");
            return false;
        }
        if (RxDataTool.isNullString(tvLeixing3.getText().toString())) {
            ToastUtil.showCenterShort("请选择家庭户口类型");
            return false;
        }
        if (RxDataTool.isNullString(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("请输入身份证号码");
            return false;
        }
        if (!IDcardUtil.IDCardValidate(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("身份证号码格式有误");
            return false;
        }
        if (IDcardUtil.getAge(tvIdcard.getText().toString()) >= 18) {
            ToastUtil.showCenterShort("子女年龄应小于18岁");
            return false;
        }
        if (RxDataTool.isNullString(tvLeixing5.getText().toString())) {
            ToastUtil.showCenterShort("请选择关系");
            return false;
        }
        if (RxDataTool.isNullString(imgPath) && !isHavePic) {
            ToastUtil.showCenterShort("请上传户口本子女页面");
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
                        //性别
                        tvLeixing1.setText(list1.get(options1));
                        if (tvLeixing1.getText().equals("男") && Contains.sAllInfo.getData().getSmyz().getXb().equals
                                ("男")) {
                            tvLeixing5.setText("父子");
                        } else if (tvLeixing1.getText().equals("男") && Contains.sAllInfo.getData().getSmyz().getXb()
                                .equals("女")) {
                            tvLeixing5.setText("母子");
                        } else if (tvLeixing1.getText().equals("女") && Contains.sAllInfo.getData().getSmyz().getXb()
                                .equals("女")) {
                            tvLeixing5.setText("母女");
                        } else if (tvLeixing1.getText().equals("女") && Contains.sAllInfo.getData().getSmyz().getXb()
                                .equals("男")) {
                            tvLeixing5.setText("父女");
                        }
                        break;
                    case 2:
                        //户籍类型
                        tvLeixing2.setText(list2.get(options1));

                        break;
                    case 3:
                        //户口类型
                        tvLeixing3.setText(list3.get(options1));
                        break;
                    case 4:
                        //婚姻状况
                        tvLeixing4.setText(list4.get(options1));
                        break;
                    case 5:
                        //与申请人关系
                        tvLeixing5.setText(list5.get(options1));
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
            mOptionsPickerView.setTitleText("请选择性别");

        } else if (flag == 2) {
            mOptionsPickerView.setTitleText("请选择户籍类型");

        } else if (flag == 3) {
            mOptionsPickerView.setTitleText("请选择家庭户口类型");

        } else if (flag == 4) {
            mOptionsPickerView.setTitleText("请选择婚姻状况");
        } else {
            mOptionsPickerView.setTitleText("请选择与申请人关系");
        }
        mOptionsPickerView.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyAddActivity.this.getCacheDir(), "household_zinv.jpg");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath = tempImage.getAbsolutePath();
            imageView5.setVisibility(View.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeFile(tempImage.getAbsolutePath());
            imgUpload.setImageBitmap(bitmap);

        }

    }

}