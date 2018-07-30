package com.ljcs.cxwl.ui.activity.home.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.home.HomeActivity;
import com.ljcs.cxwl.ui.activity.home.contract.HomeContract;
import com.ljcs.cxwl.ui.activity.home.presenter.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.home
 * @Description: The moduele of HomeActivity, provide field for HomeActivity
 * @date 2018/07/27 14:45:35
 */
@Module
public class HomeModule {
    private final HomeContract.View mView;


    public HomeModule(HomeContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public HomePresenter provideHomePresenter(HttpAPIWrapper httpAPIWrapper, HomeActivity mActivity) {
        return new HomePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public HomeActivity provideHomeActivity() {
        return (HomeActivity) mView;
    }
}