package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.SplashActivity;
import com.ljcs.cxwl.ui.activity.main.contract.SplashContract;
import com.ljcs.cxwl.ui.activity.main.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The moduele of SplashActivity, provide field for SplashActivity
 * @date 2018/06/26 08:44:54
 */
@Module
public class SplashModule {
    private final SplashContract.View mView;


    public SplashModule(SplashContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SplashPresenter provideSplashPresenter(HttpAPIWrapper httpAPIWrapper, SplashActivity mActivity) {
        return new SplashPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public SplashActivity provideSplashActivity() {
        return (SplashActivity) mView;
    }
}