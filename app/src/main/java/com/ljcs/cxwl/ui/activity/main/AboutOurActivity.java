package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.main.component.DaggerAboutOurComponent;
import com.ljcs.cxwl.ui.activity.main.contract.AboutOurContract;
import com.ljcs.cxwl.ui.activity.main.module.AboutOurModule;
import com.ljcs.cxwl.ui.activity.main.presenter.AboutOurPresenter;
import com.ljcs.cxwl.view.AboutUsDialog;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxDeviceTool;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/09 11:07:19
 */

public class AboutOurActivity extends BaseActivity implements AboutOurContract.View {

    @Inject AboutOurPresenter mPresenter;
    @BindView(R.id.tv_version) TextView tvVersion;
    @BindView(R.id.tv_content) TextView tvContent;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_about_our);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("关于我们");
    }

    @Override
    protected void initData() {
        tvVersion.setText("悦居星城 V" + RxDeviceTool.getAppVersionName(this));
        mPresenter.aboutMe();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAboutOurComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .aboutOurModule(new AboutOurModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(AboutOurContract.AboutOurContractPresenter presenter) {
        mPresenter = (AboutOurPresenter) presenter;
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
    public void aboutMeSuccess(CommonBean commonBean) {
        if (commonBean.code == Contains.REQUEST_SUCCESS) {
            if (commonBean.getData() != null) {
                tvContent.setText(commonBean.getData());
            }
        } else {
            onErrorMsg(commonBean.code, "");
        }
    }

    @OnClick(R.id.img_head)
    public void onViewClicked() {

        count++;
        Logger.i(count + "---------");
        if (count >= 10) {
            count = 0;
            Logger.i("弹出隐藏功能");
            final AboutUsDialog dialog = new AboutUsDialog(this);
            dialog.setCancelable(false);
            dialog.getCancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}