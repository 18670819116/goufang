package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ForgetPwdActivity;
import com.ljcs.cxwl.ui.activity.main.contract.ForgetPwdContract;
import com.ljcs.cxwl.ui.activity.main.presenter.ForgetPwdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The moduele of ForgetPwdActivity, provide field for ForgetPwdActivity
 * @date 2018/06/26 16:39:54
 */
@Module
public class ForgetPwdModule {
    private final ForgetPwdContract.View mView;


    public ForgetPwdModule(ForgetPwdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ForgetPwdPresenter provideForgetPwdPresenter(HttpAPIWrapper httpAPIWrapper, ForgetPwdActivity mActivity) {
        return new ForgetPwdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ForgetPwdActivity provideForgetPwdActivity() {
        return (ForgetPwdActivity) mView;
    }
}