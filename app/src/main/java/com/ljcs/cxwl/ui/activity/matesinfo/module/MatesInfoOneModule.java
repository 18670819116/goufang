package com.ljcs.cxwl.ui.activity.matesinfo.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoOneActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.contract.MatesInfoOneContract;
import com.ljcs.cxwl.ui.activity.matesinfo.presenter.MatesInfoOnePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The moduele of MatesInfoOneActivity, provide field for MatesInfoOneActivity
 * @date 2018/06/27 16:32:06
 */
@Module
public class MatesInfoOneModule {
    private final MatesInfoOneContract.View mView;


    public MatesInfoOneModule(MatesInfoOneContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MatesInfoOnePresenter provideMatesInfoOnePresenter(HttpAPIWrapper httpAPIWrapper, MatesInfoOneActivity
            mActivity) {
        return new MatesInfoOnePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MatesInfoOneActivity provideMatesInfoOneActivity() {
        return (MatesInfoOneActivity) mView;
    }
}