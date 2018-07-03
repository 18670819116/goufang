package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ChangePwdActivity;
import com.ljcs.cxwl.ui.activity.main.contract.ChangePwdContract;
import com.ljcs.cxwl.ui.activity.main.presenter.ChangePwdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The moduele of ChangePwdActivity, provide field for ChangePwdActivity
 * @date 2018/07/03 13:37:53
 */
@Module
public class ChangePwdModule {
    private final ChangePwdContract.View mView;


    public ChangePwdModule(ChangePwdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ChangePwdPresenter provideChangePwdPresenter(HttpAPIWrapper httpAPIWrapper, ChangePwdActivity mActivity) {
        return new ChangePwdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ChangePwdActivity provideChangePwdActivity() {
        return (ChangePwdActivity) mView;
    }
}