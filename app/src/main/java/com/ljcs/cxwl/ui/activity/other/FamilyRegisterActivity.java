package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.utils.ImageUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.HujiInfo;
import com.ljcs.cxwl.entity.ProvinceBean;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterPresenter;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
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

import static com.ljcs.cxwl.contain.Contains.REQUEST_CODE_GENERAL_BASIC;
import static com.ljcs.cxwl.contain.Contains.REQUEST_SUCCESS;

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
    @BindView(R.id.et_suozaidi)
    TextView etSuozaidi;
    private OptionsPickerView mOptionsPickerView;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isHavePic = false;//七牛上是否存在图片

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
        initJsonData();
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null) {
            tvName.setText(Contains.sAllInfo.getData().getSmyz().getXm());
            tvSex.setText(Contains.sAllInfo.getData().getSmyz().getXb());
            tvEthnic.setText(Contains.sAllInfo.getData().getSmyz().getMz());
            tvBirthday.setText(Contains.sAllInfo.getData().getSmyz().getCsrq());
            tvAdress.setText(Contains.sAllInfo.getData().getSmyz().getDz());
            tvIdcard.setText(Contains.sAllInfo.getData().getSmyz().getSfzhm());
            tvIssueAuthority.setText(Contains.sAllInfo.getData().getSmyz().getQfjg());
            tvData.setText(Contains.sAllInfo.getData().getSmyz().getYxq());
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getSmyz().getSfzzm()).into(imageViewZheng);
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getSmyz().getSfzfm()).into(imageViewFan);
            imageViewZheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startToImgActivity(FamilyRegisterActivity.this, API.PIC + Contains.sAllInfo.getData().getSmyz()
                            .getSfzzm());
                }
            });
            imageViewFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startToImgActivity(FamilyRegisterActivity.this, API.PIC + Contains.sAllInfo.getData().getSmyz()
                            .getSfzfm());
                }
            });
        }
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getHjxx() != null) {
            tvLeixing1.setText(Contains.sAllInfo.getData().getHjxx().getHklx());
            etSuozaidi.setText(Contains.sAllInfo.getData().getHjxx().getHjszd());
            tvLeixing2.setText(Contains.sAllInfo.getData().getHjxx().getHkxz());
            tvLeixing3.setText(Contains.sAllInfo.getData().getHjxx().getHyzt());
            //表示有图片 不需要七牛在上传了
            Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getHjxx().getHkzp()).into(imgUpload);
            imageView5.setVisibility(View.VISIBLE);
            imgPath = API.PIC + Contains.sAllInfo.getData().getHjxx().getHkzp();
            isHavePic = true;

        }
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
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }


    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(imgPath, qiniuToken.getUptoken(), new UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<>();
                    map.put("hklx", tvLeixing1.getText().toString());
                    map.put("token", RxSPTool.getString(FamilyRegisterActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("hkxz", tvLeixing2.getText().toString());
                    map.put("hyzt", tvLeixing3.getText().toString());
                    map.put("hjszd", etSuozaidi.getText().toString());
                    map.put("hkzp", url);
                    mPresenter.hukouInfo(map);
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
    public void hukouInfoSuccess(HujiInfo baseEntity) {
        if (baseEntity.code == REQUEST_SUCCESS) {

            //提交数据成功 重新拉取数据赋值
            Map<String, String> map1 = new HashMap<>();
            map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
            mPresenter.allInfo(map1);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }


    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            //重新赋值
            Contains.sAllInfo = baseEntity;
            if (tvLeixing3.getText().toString().equals("未婚") || tvLeixing3.getText().toString().equals("丧偶") ) {
                startActivty(FamilyRegisterThirdActivity.class);
            } else if (tvLeixing3.getText().toString().equals("已婚")) {
                startActivty(FamilyRegisterTwo1Activity.class);
            } else if (tvLeixing3.getText().toString().equals("离异")) {
                startActivty(FamilyRegisterTwo2Activity.class);
            }
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @OnClick({R.id.btn_login, R.id.img_upload, R.id.imageView5, R.id.layout_select1, R.id.layout_select2, R.id
            .layout_select3, R.id.layout_select4})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login:
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    return;
                }
                if (RxDataTool.isNullString(tvLeixing1.getText().toString())) {
                    ToastUtil.showCenterShort("请选择户籍类型");
                    return;
                }

                if (RxDataTool.isNullString(tvLeixing2.getText().toString())) {
                    ToastUtil.showCenterShort("请选择家庭户口类型");
                    return;
                }
                if (RxDataTool.isNullString(tvLeixing3.getText().toString())) {
                    ToastUtil.showCenterShort("请选择婚姻状况");
                    return;
                }
                if (RxDataTool.isNullString(etSuozaidi.getText().toString())) {
                    ToastUtil.showCenterShort("请选择户籍所在地");
                    return;
                }
                if (RxDataTool.isNullString(imgPath) && !isHavePic) {
                    ToastUtil.showCenterShort("请上传户口本本人页面");
                    return;
                }
                if (isHavePic) {
                    //表示不需要上传图片了
                    Map<String, String> map = new HashMap<>();
                    map.put("hklx", tvLeixing1.getText().toString());
                    map.put("token", RxSPTool.getString(FamilyRegisterActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("hkxz", tvLeixing2.getText().toString());
                    map.put("hyzt", tvLeixing3.getText().toString());
                    map.put("hjszd", etSuozaidi.getText().toString());
                    showProgressDialog();
                    mPresenter.hukouInfo(map);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(imgPath);
                    mPresenter.uploadPic(list, new UploadFileCallBack() {
                        @Override
                        public void sucess(List<String> url) {
                            Map<String, String> map = new HashMap<>();
                            map.put("hklx", tvLeixing1.getText().toString());
                            map.put("token", RxSPTool.getString(FamilyRegisterActivity.this, ShareStatic
                                    .APP_LOGIN_TOKEN));
                            map.put("hkxz", tvLeixing2.getText().toString());
                            map.put("hyzt", tvLeixing3.getText().toString());
                            map.put("hjszd", etSuozaidi.getText().toString());
                            map.put("hkzp", url.get(0));
                            mPresenter.hukouInfo(map);
                        }

                        @Override
                        public void fail(String msg) {
                            closeProgressDialog();
                        }
                    });
                }

                break;
            case R.id.img_upload:
                if (imageView5.getVisibility() == View.INVISIBLE) {
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
                isHavePic = false;
                imageView5.setVisibility(View.INVISIBLE);
                imgPath = "";
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
                showPickerView();
                break;
        }

    }

    private String imgPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterActivity.this.getCacheDir(), "household.jpg");
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
        RxKeyboardTool.hideSoftInput(this);
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
            mOptionsPickerView.setTitleText("请选择户籍类型");
        } else if (flag == 2) {
            mOptionsPickerView.setTitleText("请选择家庭户口类型");
        } else {
            mOptionsPickerView.setTitleText("请选择婚姻状况");
        }
        mOptionsPickerView.show();
    }

    private int opt1, opt2, opt3;

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                opt1=options1;
                opt2=options2;
                opt3=options3;
                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get
                        (options2) + options3Items.get(options1).get(options2).get(options3);
                etSuozaidi.setText(tx.trim());
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

}