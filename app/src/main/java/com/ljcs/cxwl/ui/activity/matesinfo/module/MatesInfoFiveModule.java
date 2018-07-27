package com.ljcs.cxwl.ui.activity.matesinfo.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoFiveActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoFiveContract;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoFivePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The moduele of MatesInfoFiveActivity, provide field for MatesInfoFiveActivity
 * @date 2018/06/28 12:16:34
 */
@Module
public class MatesInfoFiveModule {
    private final MatesInfoFiveContract.View mView;


    public MatesInfoFiveModule(MatesInfoFiveContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MatesInfoFivePresenter provideMatesInfoFivePresenter(HttpAPIWrapper httpAPIWrapper, MatesInfoFiveActivity
            mActivity) {
        return new MatesInfoFivePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MatesInfoFiveActivity provideMatesInfoFiveActivity() {
        return (MatesInfoFiveActivity) mView;
    }
}