package com.ljcs.cxwl.ui.activity.certification;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.certification.component.DaggerCertificationTwoComponent;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationTwoContract;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationTwoModule;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationTwoPresenter;
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

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: $description
 * @date 2018/06/26 19:26:58
 */

public class CertificationTwoActivity extends BaseActivity implements CertificationTwoContract.View {

    @Inject
    CertificationTwoPresenter mPresenter;
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
        setContentView(R.layout.activity_certification_two);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("实名认证");
    }

    @Override
    protected void initData() {
        tvName.setText(Contains.sCertificationInfo.getName());
        tvSex.setText(Contains.sCertificationInfo.getSex());
        tvEthnic.setText(Contains.sCertificationInfo.getEthnic());
        tvBirthday.setText(Contains.sCertificationInfo.getBirthday());
        tvAdress.setText(Contains.sCertificationInfo.getAddress());
        tvIdcard.setText(Contains.sCertificationInfo.getIdcard());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_zheng()));

    }

    @Override
    protected void setupActivityComponent() {
        DaggerCertificationTwoComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent
                ()).certificationTwoModule(new CertificationTwoModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(CertificationTwoContract.CertificationTwoContractPresenter presenter) {
        mPresenter = (CertificationTwoPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
        progressDialog.setCancelable(false);
    }

    @Override
    public void getQiniuTokenSuccess(QiniuToken qiniuToken) {
        if (qiniuToken.getUptoken() != null) {
            showProgressDialog();
            QiniuUploadUtil.uploadPic(Contains.sCertificationInfo.getPic_path_zheng(), qiniuToken.getUptoken(), new
                    UploadCallback() {

                @Override
                public void sucess(String url) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("token", RxSPTool.getString(CertificationTwoActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                    map.put("sfzhm", Contains.sCertificationInfo.getIdcard());
                    map.put("mz", Contains.sCertificationInfo.getEthnic());
                    map.put("csrq", Contains.sCertificationInfo.getBirthday());
                    map.put("xm", Contains.sCertificationInfo.getName());
                    map.put("xb", Contains.sCertificationInfo.getSex());
                    map.put("sfzzm", url);
                    if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null &&
                            Contains.sAllInfo.getData().getSmyz().getBh() != 0) {
                        map.put("bh", Contains.sAllInfo.getData().getSmyz().getBh() + "");
                    } else {
                        map.put("bh", "");
                    }
                    map.put("dz", Contains.sCertificationInfo.getAddress());
                    mPresenter.postInfo(map);
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
    public void postInfoSuccess(CerInfo baseEntity) {
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
//            ToastUtil.showCenterShort(baseEntity.msg);
            startActivty(CertificationThirdActivity.class);
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @OnClick({R.id.back, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                // TODO: 2018/7/10 测试文件上传
//                Map<String, String> map = new HashMap<>();
//                Environment.getExternalStorageDirectory().getPath();
//                Logger.e(Environment.getExternalStorageDirectory().getPath());
//
//                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/temp.jpg");
//                if (file.exists()){
//                    Logger.e("文件存在"+file.length());
//                    Glide.with(this).load(file).into(imageView);
//                }
//                mPresenter.uploadFile(map,Environment.getExternalStorageDirectory().getPath()+"/temp.jpg");
                finish();
                break;
            case R.id.next:
                if (checkText()) {

                    checkChange();
                    Contains.sCertificationInfo.setName(tvName.getText().toString());
                    Contains.sCertificationInfo.setAddress(tvAdress.getText().toString());
                    Contains.sCertificationInfo.setIdcard(tvIdcard.getText().toString());
                    Contains.sCertificationInfo.setBirthday(tvBirthday.getText().toString());
                    Contains.sCertificationInfo.setEthnic(tvEthnic.getText().toString());
                    Contains.sCertificationInfo.setSex(tvSex.getText().toString());
                    // startActivty(CertificationThirdActivity.class);
                    mPresenter.getQiniuToken();
                }

                break;
            default:
                break;
        }
    }

    /**
     * 判断扫描出来的信息是否被修改过
     */
    private void checkChange() {
        if (Contains.sCertificationInfo.getName().equals(tvName.getText().toString()) && Contains.sCertificationInfo
                .getAddress().equals(tvAdress.getText().toString()) && Contains.sCertificationInfo.getIdcard().equals
                (tvIdcard.getText().toString()) && Contains.sCertificationInfo.getBirthday().equals(tvBirthday
                .getText().toString()) && Contains.sCertificationInfo.getEthnic().equals(tvEthnic.getText().toString
                ()) && Contains.sCertificationInfo.getSex().equals(tvSex.getText().toString())) {
            //表示没有修改
                Contains.sCertificationInfo.setChangeZm(false);
        }else {
            //表示修改了
            Contains.sCertificationInfo.setChangeZm(true);
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

        if (RxDataTool.isNullString(tvSex.getText().toString()) || (!tvSex.getText().toString().equals("男") && !tvSex
                .getText().toString().equals("女"))) {
            ToastUtil.showCenterShort("性别格式不正确");
            return false;
        }

        if (RxDataTool.isNullString(tvEthnic.getText().toString())) {
            ToastUtil.showCenterShort("民族为空");
            return false;
        }
        if (RxDataTool.isNullString(tvBirthday.getText().toString())) {
            ToastUtil.showCenterShort("出生格式不正确");
            return false;
        }

        if (RxDataTool.isNullString(tvAdress.getText().toString())) {
            ToastUtil.showCenterShort("住址为空");
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
        if (IDcardUtil.getAge(tvIdcard.getText().toString()) < 22) {
            ToastUtil.showCenterShort("根据政策规定，暂不支持对未成年人的实名认证");
            return false;
        }
        return true;
    }
}