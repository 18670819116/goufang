package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.LoginActivity;
import com.ljcs.cxwl.ui.activity.main.contract.LoginContract;
import com.ljcs.cxwl.ui.activity.main.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.login
 * @Description: The moduele of LoginActivity, provide field for LoginActivity
 * @date 2017/10/17 08:38:03
 */
@Module
public class LoginModule {
    private final LoginContract.View mView;


    public LoginModule(LoginContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public LoginPresenter provideLoginPresenter(HttpAPIWrapper httpAPIWrapper, LoginActivity mActivity) {
        return new LoginPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public LoginActivity provideLoginActivity() {
        return (LoginActivity) mView;
    }
}