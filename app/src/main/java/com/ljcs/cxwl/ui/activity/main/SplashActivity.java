package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerSplashComponent;
import com.ljcs.cxwl.ui.activity.main.contract.SplashContract;
import com.ljcs.cxwl.ui.activity.main.module.SplashModule;
import com.ljcs.cxwl.ui.activity.main.presenter.SplashPresenter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: $description
 * @date 2018/06/26 08:44:55
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    public static int LOCATION_FINISH = 65;
    @Inject SplashPresenter mPresenter;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }

    @Override
    protected void initData() {
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Logger.i("-------------------剩余" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Logger.i("剩余" + 0);
                mPresenter.jump();
            }
        };
//        countDownTimer.start();
        mPresenter.jump();
        mPresenter.queryShipperInfo();
        mPresenter.getPermission();
        mPresenter.updataApp();

    }

    @Override
    protected void setupActivityComponent() {
        DaggerSplashComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .splashModule(new SplashModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(SplashContract.SplashContractPresenter presenter) {
        mPresenter = (SplashPresenter) presenter;
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
    public void loginSuccees() {
        startActivty(MainActivity.class);
        finish();
    }

    @Override
    public void jumpToLogin() {
        startActivty(LoginActivity.class);
        finish();
    }

    @Override
    public void jumpToGuest() {

    }

}