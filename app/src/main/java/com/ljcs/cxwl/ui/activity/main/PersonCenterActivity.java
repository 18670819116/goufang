package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneOneActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerPersonCenterComponent;
import com.ljcs.cxwl.ui.activity.main.contract.PersonCenterContract;
import com.ljcs.cxwl.ui.activity.main.module.PersonCenterModule;
import com.ljcs.cxwl.ui.activity.main.presenter.PersonCenterPresenter;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ClearUtils;
import com.ljcs.cxwl.util.StringUitl;
import com.ljcs.cxwl.util.UIUtils;
import com.ljcs.cxwl.view.SureDialog;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ljcs.cxwl.contain.Contains.REQUEST_SUCCESS;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/03 08:33:38
 */

public class PersonCenterActivity extends BaseActivity implements PersonCenterContract.View {

    @Inject
    PersonCenterPresenter mPresenter;
    @BindView(R.id.layout_head)
    LinearLayout layoutHead;
    @BindView(R.id.tv_account)
    TextView tvAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        autolayout.setVisibility(View.GONE);
        setContentView(R.layout.activity_person_center);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layoutHead.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initData() {

        tvAccount.setText(StringUitl.settingphone(RxSPTool.getString(this, ShareStatic.APP_LOGIN_SJHM)));

    }

    @Override
    protected void setupActivityComponent() {
        DaggerPersonCenterComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .personCenterModule(new PersonCenterModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(PersonCenterContract.PersonCenterContractPresenter presenter) {
        mPresenter = (PersonCenterPresenter) presenter;
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
    public void loginOutSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == REQUEST_SUCCESS) {
            ClearUtils.clearRxSp(this);
            startActivty(LoginActivity.class);
            AppManager.getInstance().finishAllActivity();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @OnClick({R.id.layout_head, R.id.layout_item1, R.id.layout_item2, R.id.layout_item3, R.id.layout_item4, R.id
            .btn_login_out, R.id.img_tomain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_head:
                //头像点击
                startActivty(ChangePhoneOneActivity.class);
                break;
            case R.id.layout_item1:
                //修改密码
                startActivty(ChangePwdActivity.class);
                break;
            case R.id.layout_item2:
                //资格审查申诉
//                ToastUtil.showCenterShort("敬请期待");
                startActivty(ComplainActivity.class);

                break;
            case R.id.layout_item3:
                //意见反馈
//                ToastUtil.showCenterShort("敬请期待");
                startActivty(SuggestActivity.class);
                break;
            case R.id.layout_item4:
                //关于我们
//                ToastUtil.showCenterShort("敬请期待");
                startActivty(AboutOurActivity.class);
                break;
            case R.id.btn_login_out:
                //退出登录
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    Logger.i("点击过快");
                    return;
                }
                final SureDialog dialog = new SureDialog(this);
                dialog.setCancelable(false);
                dialog.setSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, String> map = new HashMap<>();
                        map.put("sjhm", RxSPTool.getString(PersonCenterActivity.this, ShareStatic.APP_LOGIN_SJHM));
                        map.put("mm", RxSPTool.getString(PersonCenterActivity.this, ShareStatic.APP_LOGIN_MM));
                        map.put("token", RxSPTool.getString(PersonCenterActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                        mPresenter.loginOut(map);
                        dialog.dismiss();
                    }
                });
                dialog.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;
            case R.id.img_tomain:
                //去首页
                finish();
                overridePendingTransition(0, R.anim.activtiy_close_down);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(0, R.anim.activtiy_close_down);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}