package com.ljcs.cxwl.ui.activity.changephone.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneOneActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneOneContract;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneOnePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The moduele of ChangePhoneOneActivity, provide field for ChangePhoneOneActivity
 * @date 2018/07/09 16:38:08
 */
@Module
public class ChangePhoneOneModule {
    private final ChangePhoneOneContract.View mView;


    public ChangePhoneOneModule(ChangePhoneOneContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ChangePhoneOnePresenter provideChangePhoneOnePresenter(HttpAPIWrapper httpAPIWrapper,
                                                                  ChangePhoneOneActivity mActivity) {
        return new ChangePhoneOnePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ChangePhoneOneActivity provideChangePhoneOneActivity() {
        return (ChangePhoneOneActivity) mView;
    }
}