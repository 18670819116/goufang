package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.SuggestActivity;
import com.ljcs.cxwl.ui.activity.main.contract.SuggestContract;
import com.ljcs.cxwl.ui.activity.main.presenter.SuggestPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The moduele of SuggestActivity, provide field for SuggestActivity
 * @date 2018/07/06 15:46:33
 */
@Module
public class SuggestModule {
    private final SuggestContract.View mView;


    public SuggestModule(SuggestContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public SuggestPresenter provideSuggestPresenter(HttpAPIWrapper httpAPIWrapper, SuggestActivity mActivity) {
        return new SuggestPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public SuggestActivity provideSuggestActivity() {
        return (SuggestActivity) mView;
    }
}