package com.ljcs.cxwl.ui.activity.changephone;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.ui.activity.changephone.component.DaggerChangePhoneThirdComponent;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneThirdContract;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneThirdModule;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneThirdPresenter;
import com.ljcs.cxwl.util.IDcardUtil;
import com.ljcs.cxwl.util.ToastUtil;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxTool;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: $description
 * @date 2018/07/09 16:39:43
 */

public class ChangePhoneThirdActivity extends BaseActivity implements ChangePhoneThirdContract.View {

    @Inject
    ChangePhoneThirdPresenter mPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone_third);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("更换手机号码");
        tvName.setText(Contains.sCertificationInfo.getName());
        tvSex.setText(Contains.sCertificationInfo.getSex());
        tvEthnic.setText(Contains.sCertificationInfo.getEthnic());
        tvBirthday.setText(Contains.sCertificationInfo.getBirthday());
        tvAdress.setText(Contains.sCertificationInfo.getAddress());
        tvIdcard.setText(Contains.sCertificationInfo.getIdcard());
        imageView.setImageBitmap(BitmapFactory.decodeFile(Contains.sCertificationInfo.getPic_path_zheng()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerChangePhoneThirdComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent
                ()).changePhoneThirdModule(new ChangePhoneThirdModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ChangePhoneThirdContract.ChangePhoneThirdContractPresenter presenter) {
        mPresenter = (ChangePhoneThirdPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (checkText()) {
            Intent intent = new Intent(this, ChangePhoneFourActivity.class);
            intent.putExtra("sfzhm", tvIdcard.getText().toString());
            startActivity(intent);
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