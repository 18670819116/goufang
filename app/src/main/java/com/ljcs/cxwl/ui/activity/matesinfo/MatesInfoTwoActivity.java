package com.ljcs.cxwl.ui.activity.matesinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.matesinfo.component.DaggerMatesInfoTwoComponent;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoTwoContract;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoTwoModule;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoTwoPresenter;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.QiniuUploadUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.qiniu.android.http.ResponseInfo;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.ENTERTYPE_CHANGE;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: $description
 * @date 2018/06/27 16:33:03
 */

public class MatesInfoTwoActivity extends BaseActivity implements MatesInfoTwoContract.View {

    @Inject
    MatesInfoTwoPresenter mPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matesinfo_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("添加配偶信息");
    }

    @Override
    protected void initData() {
        tvName.setText(Contains.sCertificationInfo.getName_peiou());
        tvSex.setText(Contains.sCertificationInfo.getSex_peiou());
        tvEthnic.setText(Contains.sCertificationInfo.getEthnic_peiou());
        tvBirthday.setText(Contains.sCertificationInfo.getBirthday_peiou());
        tvAdress.setText(Contains.sCertificationInfo.getAddress_peiou());
        tvIdcard.setText(Contains.sCertificationInfo.getIdcard_peiou());
        Glide.with(this).load(Contains.sCertificationInfo.getPic_path_zheng_peiou()).into(imageView);
        //imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_zheng_peiou()));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMatesInfoTwoComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .matesInfoTwoModule(new MatesInfoTwoModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MatesInfoTwoContract.MatesInfoTwoContractPresenter presenter) {
        mPresenter = (MatesInfoTwoPresenter) presenter;
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
    public void matesInfoSuccess(MatesInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            Map<String, String> map = new HashMap<>();
            map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
            mPresenter.allInfo(map);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            Contains.sAllInfo = baseEntity;
            ToastUtil.showCenterShort(baseEntity.msg);
            if (ENTERTYPE_CHANGE == 1) {
                startActivty(MatesInfoFourActivity.class);
            } else {
                startActivty(MatesInfoThirdActivity.class);
            }

        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(Contains.sCertificationInfo.getPic_path_zheng_peiou(), qiniuToken.getUptoken(),
                    new UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("token", RxSPTool.getString(MatesInfoTwoActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("sfzhm", Contains.sCertificationInfo.getIdcard_peiou());
                    map.put("mz", Contains.sCertificationInfo.getEthnic_peiou());
                    map.put("csrq", Contains.sCertificationInfo.getBirthday_peiou());
                    map.put("xm", Contains.sCertificationInfo.getName_peiou());
                    map.put("xb", Contains.sCertificationInfo.getSex_peiou());
                    map.put("sfzzm", url);
                    map.put("dz", Contains.sCertificationInfo.getAddress_peiou());
                    if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                            Contains.sAllInfo.getData().getPoxx().getBh() != 0) {
                        map.put("bh", Contains.sAllInfo.getData().getPoxx().getBh() + "");
                    } else {
                        map.put("bh", "");
                    }

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

    @OnClick({R.id.back, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (ENTERTYPE_CHANGE == 1) {
                    startActivty(MatesInfoOneActivity.class);
                } else {
                    finish();
                }
                break;
            case R.id.next:
                if (checkText()) {
                    Contains.sCertificationInfo.setName_peiou(tvName.getText().toString());
                    Contains.sCertificationInfo.setAddress_peiou(tvAdress.getText().toString());
                    Contains.sCertificationInfo.setIdcard_peiou(tvIdcard.getText().toString());
                    Contains.sCertificationInfo.setBirthday_peiou(tvBirthday.getText().toString());
                    Contains.sCertificationInfo.setEthnic_peiou(tvEthnic.getText().toString());
                    Contains.sCertificationInfo.setSex_peiou(tvSex.getText().toString());
                    if (ENTERTYPE_CHANGE == 1 && Contains.sCertificationInfo.getPic_path_zheng_peiou().startsWith
                            ("http")) {
                        //配偶修改直接跳过上传图片
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("token", RxSPTool.getString(MatesInfoTwoActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                        map.put("sfzhm", Contains.sCertificationInfo.getIdcard_peiou());
                        map.put("mz", Contains.sCertificationInfo.getEthnic_peiou());
                        map.put("csrq", Contains.sCertificationInfo.getBirthday_peiou());
                        map.put("xm", Contains.sCertificationInfo.getName_peiou());
                        map.put("xb", Contains.sCertificationInfo.getSex_peiou());
                        map.put("dz", Contains.sCertificationInfo.getAddress_peiou());
                        if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getPoxx() != null &&
                                Contains.sAllInfo.getData().getPoxx().getBh() != 0) {
                            map.put("bh", Contains.sAllInfo.getData().getPoxx().getBh() + "");
                        } else {
                            map.put("bh", "");
                        }

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

    private boolean checkText() {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return false;
        }
        if (RxDataTool.isNullString(tvName.getText().toString())) {
            ToastUtil.showCenterShort("姓名为空");
            return false;
        }

        if (RxDataTool.isNullString(tvSex.getText().toString())) {
            ToastUtil.showCenterShort("性别为空");
            return false;
        }
        if (RxDataTool.isNullString(tvEthnic.getText().toString())) {
            ToastUtil.showCenterShort("民族为空");
            return false;
        }
        if (RxDataTool.isNullString(tvBirthday.getText().toString())) {
            ToastUtil.showCenterShort("出生为空");
            return false;
        }
        if (RxDataTool.isNullString(tvAdress.getText().toString())) {
            ToastUtil.showCenterShort("住址为空");
            return false;
        }
        if (RxDataTool.isNullString(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("身份证号码为空");
            return false;
        }
        if (!IDcardUtil.is18IdCard(tvIdcard.getText().toString())) {
            ToastUtil.showCenterShort("身份证号码格式有误");
            return false;
        }
        if (!tvIdcard.getText().toString().substring(6, 14).equals(tvBirthday.getText().toString())) {
            ToastUtil.showCenterShort("出生日期和身份证的出生日期不一致");
            return false;
        }
        if ((IDcardUtil.getAge(tvIdcard.getText().toString()) < 22 && tvSex.getText().toString().equals("男")) ||
                (IDcardUtil.getAge(tvIdcard.getText().toString()) < 20 && tvSex.getText().toString().equals("女"))) {
            ToastUtil.showCenterShort("年龄不合法");
            return false;
        }
        return true;
    }
}