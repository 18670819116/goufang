package com.ljcs.cxwl.ui.activity.other;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
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

import com.baidu.ocr.sdk.utils.ImageUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.ProvinceBean;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.ShowImgActivity;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterTwo2Component;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwo2Contract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwo2Module;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwo2Presenter;
import com.ljcs.cxwl.util.FileUtil;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
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
    @BindView(R.id.tv_hjszd)
    TextView tvHjszd;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    private TimePickerView pvTime;

    private boolean isHavePic1;
    private String imgPath1;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();
    }

    @Override
    protected void initData() {
        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null) {
            if (Contains.sAllInfo.getData().getPoxx().getJtcy() != null) {
                //身份证等信息
                etName.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getXm());
                tvIdcard.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getZjhm());
                tvLeixing2.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getLysj());
                tvHjszd.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getHjszd());
                tvPhone.setText(Contains.sAllInfo.getData().getPoxx().getJtcy().getLxdh());
            }

            //户籍等信息
            //表示有图片 不需要七牛在上传了
            if (Contains.sAllInfo.getData().getPoxx().getZzxx() != null) {
                Glide.with(this).load(API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getLhz()).into
                        (imgUpload1);
                imageView5.setVisibility(View.VISIBLE);
                imgPath1 = API.PIC + Contains.sAllInfo.getData().getPoxx().getZzxx().getLhz();
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
        if (RxDataTool.isNullString(tvHjszd.getText().toString())) {
            ToastUtil.showCenterShort("请选择户籍所在地");
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
        if (!StringUitl.isMatch(RxConstTool.REGEX_MOBILE_EXACT, tvPhone.getText().toString())) {
            ToastUtil.showCenterShort("手机号码不正确");
            return false;
        }
        if (RxDataTool.isNullString(imgPath1) && !isHavePic1) {
            ToastUtil.showCenterShort("请上传离婚证");
            return false;
        }
        if ((IDcardUtil.getAge(tvIdcard.getText().toString()) < 22 && IDcardUtil.getSex(tvIdcard.getText().toString()
        ).equals("男")) || (IDcardUtil.getAge(tvIdcard.getText().toString()) < 20 && IDcardUtil.getSex(tvIdcard
                .getText().toString()).equals("女"))) {
            ToastUtil.showCenterShort("非适婚年龄");
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
                    map.put("lysj", tvLeixing2.getText().toString());
                    map.put("zjhm", tvIdcard.getText().toString());
                    map.put("lhz", url);
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
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).setRangDate(startDate,
                endDate).setDate(endDate).build();

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

    @OnClick({R.id.img_upload1, R.id.imageView5, R.id.btn_login, R.id.layout_select2, R.id.layout_select5})
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
                        map.put("lysj", tvLeixing2.getText().toString());
                        map.put("zjhm", tvIdcard.getText().toString());
                        map.put("hjszd", tvHjszd.getText().toString());
                        map.put("lxdh", tvPhone.getText().toString());
                        map.put("lhz", Contains.sAllInfo.getData().getPoxx().getZzxx().getLhz());
                        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                                Contains.sAllInfo.getData().getPoxx().getJtcy() != null && Contains.sAllInfo.getData
                                ().getPoxx().getJtcy().getYhbh() != null) {
                            map.put("yhbh", Contains.sAllInfo.getData().getPoxx().getJtcy().getYhbh());
                        }
                        mPresenter.matesInfo(map);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(imgPath1);
                        mPresenter.uploadPic(list, new UploadFileCallBack() {
                            @Override
                            public void sucess(List<String> url) {
                                Map<String, String> map = new HashMap<String, String>();

                                map.put("xm", etName.getText().toString());
                                map.put("token", RxSPTool.getString(FamilyRegisterTwo2Activity.this, ShareStatic
                                        .APP_LOGIN_TOKEN));
                                map.put("lysj", tvLeixing2.getText().toString());
                                map.put("zjhm", tvIdcard.getText().toString());
                                map.put("hjszd", tvHjszd.getText().toString());
                                map.put("lxdh", tvPhone.getText().toString());
                                map.put("lhz", url.get(0));
                                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                                        Contains.sAllInfo.getData().getPoxx().getJtcy() != null && Contains.sAllInfo.getData
                                        ().getPoxx().getJtcy().getYhbh() != null) {
                                    map.put("yhbh", Contains.sAllInfo.getData().getPoxx().getJtcy().getYhbh());
                                }
                                mPresenter.matesInfo(map);
                            }

                            @Override
                            public void fail(String msg) {
                                closeProgressDialog();
                                Logger.e(msg);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_LHZ && resultCode == Activity.RESULT_OK) {
            final File tempImage = new File(FamilyRegisterTwo2Activity.this.getCacheDir(), "household_lhz.jpg");
            ImageUtil.resize(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath())
                    .getAbsolutePath(), tempImage.getAbsolutePath(), 1280, 1280);
            Logger.e(tempImage.getAbsolutePath() + "-----" + tempImage.length());
            imgPath1 = tempImage.getAbsolutePath();
            imgUpload1.setImageBitmap(BitmapFactory.decodeFile(tempImage.getAbsolutePath()));
            imageView5.setVisibility(View.VISIBLE);

        }

    }

    private int opt1, opt2, opt3;

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
}