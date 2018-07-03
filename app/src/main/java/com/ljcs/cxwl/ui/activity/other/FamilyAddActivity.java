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
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.entity.CertificationInfo;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyAddComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyAddContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyAddModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyAddPresenter;
import com.ljcs.cxwl.util.FileUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxDataTool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private OptionsPickerView mOptionsPickerView;
    private String imgPath;
    private CertificationInfo certificationInfo = new CertificationInfo();
    private List<CertificationInfo> certificationInfoList = new ArrayList<>();
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
    List<String> list5 = new ArrayList<String>() {{
        add("父子");
        add("父女");
        add("母子");
        add("母女");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_add);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加子女信息");
    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.layout_select1, R.id.layout_select2, R.id.layout_select3, R.id.layout_select4, R.id
            .layout_select5, R.id.img_upload, R.id.imageView5, R.id.next})
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
                showSelectPickerView(5, list5);
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
                Glide.with(FamilyAddActivity.this).load(R.mipmap.ic_img4).into(imgUpload);
                break;
            case R.id.next:
                if (!RxDataTool.isNullString(tvName.getText().toString().trim())) {
                    Contains.sCertificationInfo.setName_zinv(tvName.getText().toString().trim());
                    certificationInfo.setName_zinv(tvName.getText().toString().trim());
                }
                if (!RxDataTool.isNullString(tvIdcard.getText().toString().trim())) {
                    Contains.sCertificationInfo.setIdcard_zinv(tvIdcard.getText().toString().trim());
                    certificationInfo.setIdcard_zinv(tvIdcard.getText().toString().trim());
                }
                Contains.sCertificationInfoList.add(certificationInfo);
                setResult(101);
                finish();
                break;
            default:
                break;
        }
    }

    private void showSelectPickerView(final int flag, List<String> list) {
        mOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Logger.i(options1 + "--" + options2 + "--" + options3);
                switch (flag) {
                    case 1:
                        //性别
                        tvLeixing1.setText(list1.get(options1));
                        Contains.sCertificationInfo.setSex_zinv(list1.get(options1));
                        certificationInfo.setSex_zinv(list1.get(options1));
                        break;
                    case 2:
                        //户籍类型
                        tvLeixing2.setText(list2.get(options1));
                        Contains.sCertificationInfo.setLeixing1_zinv(list2.get(options1));
                        certificationInfo.setLeixing1_zinv(list2.get(options1));

                        break;
                    case 3:
                        //户口类型
                        tvLeixing3.setText(list3.get(options1));
                        Contains.sCertificationInfo.setLeixing2_zinv(list3.get(options1));
                        certificationInfo.setLeixing2_zinv(list3.get(options1));
                        break;
                    case 4:
                        //婚姻状况
                        tvLeixing4.setText(list4.get(options1));
                        Contains.sCertificationInfo.setLeixing3_zinv(list4.get(options1));
                        certificationInfo.setLeixing3_zinv(list4.get(options1));
                        break;
                    case 5:
                        //与申请人关系
                        tvLeixing5.setText(list5.get(options1));
                        Contains.sCertificationInfo.setGuangxi_zinv(list5.get(options1));
                        certificationInfo.setGuangxi_zinv(list5.get(options1));
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
            mOptionsPickerView.setTitleText("性别");

        } else if (flag == 2) {
            mOptionsPickerView.setTitleText("户籍类型");

        } else if (flag == 3) {
            mOptionsPickerView.setTitleText("家庭户口类型");

        } else if (flag == 4) {
            mOptionsPickerView.setTitleText("婚姻状况");
        } else {
            mOptionsPickerView.setTitleText("与申请人关系");
        }
        mOptionsPickerView.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyAddActivity.this.getCacheDir(), "household_zinv_"+System.currentTimeMillis());
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath = tempImage.getAbsolutePath();
            imageView5.setVisibility(View.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeFile(tempImage.getAbsolutePath());
            imgUpload.setImageBitmap(bitmap);
            Contains.sCertificationInfo.setPic_path_hk_zinv(tempImage.getAbsolutePath());
           certificationInfo.setPic_path_hk_zinv(tempImage.getAbsolutePath());

        }

    }

}