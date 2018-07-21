package com.ljcs.cxwl.ui.activity.web.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.web.WebSatisficingActivity;
import com.ljcs.cxwl.ui.activity.web.contract.WebSatisficingContract;
import com.ljcs.cxwl.ui.activity.web.presenter.WebSatisficingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.web
 * @Description: The moduele of WebSatisficingActivity, provide field for WebSatisficingActivity
 * @date 2018/07/12 11:56:05
 */
@Module
public class WebSatisficingModule {
    private final WebSatisficingContract.View mView;


    public WebSatisficingModule(WebSatisficingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WebSatisficingPresenter provideWebSatisficingPresenter(HttpAPIWrapper httpAPIWrapper, WebSatisficingActivity mActivity) {
        return new WebSatisficingPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public WebSatisficingActivity provideWebSatisficingActivity() {
        return (WebSatisficingActivity) mView;
    }
}