package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.RegisterActivity;
import com.ljcs.cxwl.ui.activity.main.contract.RegisterContract;
import com.ljcs.cxwl.ui.activity.main.presenter.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The moduele of RegisterActivity, provide field for RegisterActivity
 * @date 2018/06/26 14:58:24
 */
@Module
public class RegisterModule {
    private final RegisterContract.View mView;


    public RegisterModule(RegisterContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RegisterPresenter provideRegisterPresenter(HttpAPIWrapper httpAPIWrapper, RegisterActivity mActivity) {
        return new RegisterPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RegisterActivity provideRegisterActivity() {
        return (RegisterActivity) mView;
    }
}