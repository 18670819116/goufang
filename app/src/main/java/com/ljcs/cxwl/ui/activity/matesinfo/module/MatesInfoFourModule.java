package com.ljcs.cxwl.ui.activity.matesinfo.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoFourActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoFourContract;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoFourPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The moduele of MatesInfoFourActivity, provide field for MatesInfoFourActivity
 * @date 2018/06/27 16:35:11
 */
@Module
public class MatesInfoFourModule {
    private final MatesInfoFourContract.View mView;


    public MatesInfoFourModule(MatesInfoFourContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MatesInfoFourPresenter provideMatesInfoFourPresenter(HttpAPIWrapper httpAPIWrapper, MatesInfoFourActivity
            mActivity) {
        return new MatesInfoFourPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MatesInfoFourActivity provideMatesInfoFourActivity() {
        return (MatesInfoFourActivity) mView;
    }
}