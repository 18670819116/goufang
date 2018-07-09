package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ComplainActivity;
import com.ljcs.cxwl.ui.activity.main.contract.ComplainContract;
import com.ljcs.cxwl.ui.activity.main.presenter.ComplainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The moduele of ComplainActivity, provide field for ComplainActivity
 * @date 2018/07/09 10:58:14
 */
@Module
public class ComplainModule {
    private final ComplainContract.View mView;


    public ComplainModule(ComplainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ComplainPresenter provideComplainPresenter(HttpAPIWrapper httpAPIWrapper, ComplainActivity mActivity) {
        return new ComplainPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ComplainActivity provideComplainActivity() {
        return (ComplainActivity) mView;
    }
}