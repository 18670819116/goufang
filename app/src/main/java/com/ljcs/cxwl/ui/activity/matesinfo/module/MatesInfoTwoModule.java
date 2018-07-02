package com.ljcs.cxwl.ui.activity.matesinfo.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoTwoActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoTwoContract;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoTwoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The moduele of MatesInfoTwoActivity, provide field for MatesInfoTwoActivity
 * @date 2018/06/27 16:33:03
 */
@Module
public class MatesInfoTwoModule {
    private final MatesInfoTwoContract.View mView;


    public MatesInfoTwoModule(MatesInfoTwoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MatesInfoTwoPresenter provideMatesInfoTwoPresenter(HttpAPIWrapper httpAPIWrapper, MatesInfoTwoActivity mActivity) {
        return new MatesInfoTwoPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MatesInfoTwoActivity provideMatesInfoTwoActivity() {
        return (MatesInfoTwoActivity) mView;
    }
}