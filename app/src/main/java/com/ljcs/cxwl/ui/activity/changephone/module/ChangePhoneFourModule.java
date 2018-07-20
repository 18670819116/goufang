package com.ljcs.cxwl.ui.activity.changephone.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneFourActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneFourContract;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneFourPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The moduele of ChangePhoneFourActivity, provide field for ChangePhoneFourActivity
 * @date 2018/07/19 20:33:44
 */
@Module
public class ChangePhoneFourModule {
    private final ChangePhoneFourContract.View mView;


    public ChangePhoneFourModule(ChangePhoneFourContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ChangePhoneFourPresenter provideChangePhoneFourPresenter(HttpAPIWrapper httpAPIWrapper, ChangePhoneFourActivity mActivity) {
        return new ChangePhoneFourPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ChangePhoneFourActivity provideChangePhoneFourActivity() {
        return (ChangePhoneFourActivity) mView;
    }
}