package com.ljcs.cxwl.ui.activity.changephone;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.changephone.component.DaggerChangePhoneFourComponent;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneFourContract;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneFourModule;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneFourPresenter;
import com.ljcs.cxwl.ui.activity.main.LoginActivity;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ClearUtils;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.ToastUtil;
import com.vondear.rxtool.RxConstTool;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxEncryptTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REGEX_MOBILE_EXACT;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: $description
 * @date 2018/07/19 20:33:44
 */

public class ChangePhoneFourActivity extends BaseActivity implements ChangePhoneFourContract.View {

    @Inject ChangePhoneFourPresenter mPresenter;
    @BindView(R.id.et1) EditText et1;
    @BindView(R.id.et2) EditText et2;
    @BindView(R.id.tv_get_yzm) TextView tvGetYzm;
    private CountDownTimer countDownTimer;
    private String code = "";
    private String phone = "";
    private String sfzhm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone_four);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("更换手机号码");
    }

    @Override
    protected void initData() {
        sfzhm = getIntent().getStringExtra("sfzhm");
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetYzm.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvGetYzm.setEnabled(true);
                tvGetYzm.setText("获取验证码");
                tvGetYzm.setTextColor(getResources().getColor(R.color.color_0f77ff));
            }
        };
    }

    @Override
    protected void setupActivityComponent() {
        DaggerChangePhoneFourComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent
                ()).changePhoneFourModule(new ChangePhoneFourModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ChangePhoneFourContract.ChangePhoneFourContractPresenter presenter) {
        mPresenter = (ChangePhoneFourPresenter) presenter;
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
    public void getChangeCodeSuccess(CommonBean baseEntity) {
        if (baseEntity.getCode() == Contains.REQUEST_SUCCESS) {
            if (baseEntity.getData() != null) {
                code = baseEntity.getData();
                phone = et1.getText().toString();
                tvGetYzm.setEnabled(false);
                tvGetYzm.setTextColor(getResources().getColor(R.color.color_939393));
                countDownTimer.start();
            }

        } else {
            onErrorMsg(baseEntity.code, baseEntity.getMsg());
        }
    }

    @Override
    public void changePhoneSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ClearUtils.clearRxSp(this);
            ToastUtil.showCenterShort("手机号码修改成功");
            startActivty(LoginActivity.class);
            AppManager.getInstance().finishAllActivity();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @OnClick({R.id.tv_get_yzm, R.id.btn_login})
    public void onViewClicked(View view) {
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_get_yzm:
                if (RxDataTool.isNullString(et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机格式错误");
                    return;
                }
                if (!StringUitl.isMatch(REGEX_MOBILE_EXACT, et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机格式错误");
                    return;
                }

                mPresenter.getChangeCode(et1.getText().toString().trim());
                break;
            case R.id.btn_login:
                if (!StringUitl.isMatch(REGEX_MOBILE_EXACT, et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码不正确");
                    return;
                }
                if (et2.getText().toString().length() != 6) {
                    ToastUtil.showCenterShort("验证码至少6位");
                    return;
                }
                if (!RxDataTool.isNullString(phone) && !phone.equals(et1.getText().toString())) {
                    ToastUtil.showCenterShort("手机号码改变请重新获取验证码");
                    return;
                }
                if (RxDataTool.isNullString(code) || !(et2.getText().toString().trim().equals(code))) {
                    ToastUtil.showCenterShort("验证码错误");
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
                map.put("sjhm", et1.getText().toString().trim());
                map.put("sfzhm", sfzhm);
                map.put("mm", RxEncryptTool.encryptSHA1ToString(RxSPTool.getString(this, ShareStatic.APP_LOGIN_MM) +
                        RxSPTool.getString(this, ShareStatic.APP_LOGIN_SJHM)));
                map.put("newmm", RxEncryptTool.encryptSHA1ToString(RxSPTool.getString(this, ShareStatic.APP_LOGIN_MM)
                        + et1.getText().toString()));
                mPresenter.changePhone(map);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}