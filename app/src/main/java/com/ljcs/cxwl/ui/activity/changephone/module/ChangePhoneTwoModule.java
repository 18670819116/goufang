package com.ljcs.cxwl.ui.activity.changephone.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneTwoActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneTwoContract;
import com.ljcs.cxwl.ui.activity.changephone.presenter.ChangePhoneTwoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The moduele of ChangePhoneTwoActivity, provide field for ChangePhoneTwoActivity
 * @date 2018/07/09 16:39:17
 */
@Module
public class ChangePhoneTwoModule {
    private final ChangePhoneTwoContract.View mView;


    public ChangePhoneTwoModule(ChangePhoneTwoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ChangePhoneTwoPresenter provideChangePhoneTwoPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                  ChangePhoneTwoActivity mActivity) {
        return new ChangePhoneTwoPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ChangePhoneTwoActivity provideChangePhoneTwoActivity() {
        return (ChangePhoneTwoActivity) mView;
    }
}