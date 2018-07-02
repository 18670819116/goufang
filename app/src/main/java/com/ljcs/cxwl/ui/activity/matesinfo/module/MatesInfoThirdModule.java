package com.ljcs.cxwl.ui.activity.matesinfo.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoThirdActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoThirdContract;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoThirdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The moduele of MatesInfoThirdActivity, provide field for MatesInfoThirdActivity
 * @date 2018/06/27 16:33:53
 */
@Module
public class MatesInfoThirdModule {
    private final MatesInfoThirdContract.View mView;


    public MatesInfoThirdModule(MatesInfoThirdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MatesInfoThirdPresenter provideMatesInfoThirdPresenter(HttpAPIWrapper httpAPIWrapper, MatesInfoThirdActivity mActivity) {
        return new MatesInfoThirdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MatesInfoThirdActivity provideMatesInfoThirdActivity() {
        return (MatesInfoThirdActivity) mView;
    }
}