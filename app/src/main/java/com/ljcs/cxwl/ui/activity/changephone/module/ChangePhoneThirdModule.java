package com.ljcs.cxwl.ui.activity.changephone.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneThirdActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneThirdContract;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneThirdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The moduele of ChangePhoneThirdActivity, provide field for ChangePhoneThirdActivity
 * @date 2018/07/09 16:39:43
 */
@Module
public class ChangePhoneThirdModule {
    private final ChangePhoneThirdContract.View mView;


    public ChangePhoneThirdModule(ChangePhoneThirdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ChangePhoneThirdPresenter provideChangePhoneThirdPresenter(HttpAPIWrapper httpAPIWrapper, ChangePhoneThirdActivity mActivity) {
        return new ChangePhoneThirdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ChangePhoneThirdActivity provideChangePhoneThirdActivity() {
        return (ChangePhoneThirdActivity) mView;
    }
}