package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.AboutOurActivity;
import com.ljcs.cxwl.ui.activity.main.contract.AboutOurContract;
import com.ljcs.cxwl.ui.activity.main.presenter.AboutOurPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The moduele of AboutOurActivity, provide field for AboutOurActivity
 * @date 2018/07/09 11:07:19
 */
@Module
public class AboutOurModule {
    private final AboutOurContract.View mView;


    public AboutOurModule(AboutOurContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AboutOurPresenter provideAboutOurPresenter(HttpAPIWrapper httpAPIWrapper, AboutOurActivity mActivity) {
        return new AboutOurPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AboutOurActivity provideAboutOurActivity() {
        return (AboutOurActivity) mView;
    }
}