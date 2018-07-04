package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.certification.CertificationOneActivity;
import com.ljcs.cxwl.ui.activity.certification.CertificationStatusInfoActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerMainComponent;
import com.ljcs.cxwl.ui.activity.main.contract.MainContract;
import com.ljcs.cxwl.ui.activity.main.module.MainModule;
import com.ljcs.cxwl.ui.activity.main.presenter.MainPresenter;
import com.ljcs.cxwl.ui.activity.other.FamilyAddNowActivity;
import com.ljcs.cxwl.ui.activity.other.QualificationExaminationActivity;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.util.UIUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/02 16:17:40
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter mPresenter;
    @BindView(R.id.relayou_head)
    RelativeLayout relayouHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        autolayout.setVisibility(View.GONE);
        relayouHead.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerMainComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MainContract.MainContractPresenter presenter) {
        mPresenter = (MainPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.img_head, R.id.img_right_menu, R.id.relayout_item1, R.id.relayout_item2, R.id.relayout_item3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                break;
            case R.id.img_right_menu:
                startActivty(PersonCenterActivity.class);
                overridePendingTransition(R.anim.activity_open_top, R.anim.activity_translate_out);
                break;
            case R.id.relayout_item1:
                //实名认证
//                startActivty(CertificationOneActivity.class);
                //实名认证详情
                startActivty(CertificationStatusInfoActivity.class);
                break;
            case R.id.relayout_item2:
                //添加家庭成员
                startActivty(FamilyAddNowActivity.class);
                break;
            case R.id.relayout_item3:
                //购房资格申请
                startActivty(QualificationExaminationActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.showCenterShort("再按一次退出购房");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                AppManager.getInstance().appExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}