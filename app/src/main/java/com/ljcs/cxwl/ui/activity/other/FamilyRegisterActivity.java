package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoOneActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterPresenter;
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
 * @date 2018/06/27 17:51:07
 */

public class FamilyRegisterActivity extends BaseActivity implements FamilyRegisterContract.View {

    @Inject
    FamilyRegisterPresenter mPresenter;
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
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.tv_idcard)
    TextView tvIdcard;
    @BindView(R.id.tv_issueAuthority)
    TextView tvIssueAuthority;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.img_upload)
    ImageView imgUpload;
    @BindView(R.id.imageView_zheng)
    ImageView imageViewZheng;
    @BindView(R.id.imageView_fan)
    ImageView imageViewFan;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    private OptionsPickerView mOptionsPickerView;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("wh", "图片设置onCreate1");
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("添加申购人户口本信息");
    }

    @Override
    protected void initData() {
        list1.add("城市");
        list1.add("农村");
        list2.add("集体户口");
        list2.add("家庭户口");
        list3.add("已婚");
        list3.add("未婚");
        list3.add("离异");
        list3.add("丧偶");
        tvName.setText(Contains.sCertificationInfo.getName());
        tvSex.setText(Contains.sCertificationInfo.getSex());
        tvEthnic.setText(Contains.sCertificationInfo.getEthnic());
        tvBirthday.setText(Contains.sCertificationInfo.getBirthday());
        tvAdress.setText(Contains.sCertificationInfo.getAddress());
        tvIdcard.setText(Contains.sCertificationInfo.getIdcard());
        imageViewZheng.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_zheng()));
        tvIssueAuthority.setText(Contains.sCertificationInfo.getIssueAuthority());
        tvData.setText(Contains.sCertificationInfo.getSignDate() + "-" + Contains.sCertificationInfo.getExpiryDate());
        imageViewFan.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_fan()));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent()
        ).familyRegisterModule(new FamilyRegisterModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterContract.FamilyRegisterContractPresenter presenter) {
        mPresenter = (FamilyRegisterPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.btn_login, R.id.img_upload, R.id.imageView5, R.id.layout_select1, R.id.layout_select2, R.id
            .layout_select3})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login:
//                startActivty(MatesInfoOneActivity.class);
                startActivty(FamilyRegisterThirdActivity.class);
                break;
            case R.id.img_upload:
                if (imageView5.getVisibility()==View.INVISIBLE) {
                    intent = new Intent(FamilyRegisterActivity.this, CameraActivity.class);
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication())
                            .getAbsolutePath());
                    intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
                } else {
                    intent = new Intent(FamilyRegisterActivity.this, ShowImgActivity.class);
                    intent.putExtra("img_path", imgPath);
                    startActivity(intent);
                }
                break;
            case R.id.imageView5:
                imageView5.setVisibility(View.INVISIBLE);
                Glide.with(FamilyRegisterActivity.this).load(R.mipmap.ic_img1).into(imgUpload);
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

    private String imgPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterActivity.this.getCacheDir(), "household");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath = tempImage.getAbsolutePath();
            imageView5.setVisibility(View.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeFile(tempImage.getAbsolutePath());
            imgUpload.setImageBitmap(bitmap);
            Contains.sCertificationInfo.setPic_path_hk(tempImage.getAbsolutePath());

        }

    }

    private void showSelectPickerView(final int flag, List<String> list) {
        mOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Logger.i(options1 + "--" + options2 + "--" + options3);
                switch (flag) {
                    case 1:
                        //户籍类型
                        tvLeixing1.setText(list1.get(options1));
                        Contains.sCertificationInfo.setLeixing1(list1.get(options1));
                        break;
                    case 2:
                        //户口类型
                        tvLeixing2.setText(list2.get(options1));
                        Contains.sCertificationInfo.setLeixing2(list2.get(options1));

                        break;
                    case 3:
                        //婚姻状况
                        tvLeixing3.setText(list3.get(options1));
                        Contains.sCertificationInfo.setLeixing3(list3.get(options1));
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

}