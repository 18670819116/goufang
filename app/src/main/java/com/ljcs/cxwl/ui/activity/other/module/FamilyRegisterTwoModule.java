package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwoActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwoContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterTwoActivity, provide field for FamilyRegisterTwoActivity
 * @date 2018/06/27 19:05:45
 */
@Module
public class FamilyRegisterTwoModule {
    private final FamilyRegisterTwoContract.View mView;


    public FamilyRegisterTwoModule(FamilyRegisterTwoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterTwoPresenter provideFamilyRegisterTwoPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                        FamilyRegisterTwoActivity mActivity) {
        return new FamilyRegisterTwoPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterTwoActivity provideFamilyRegisterTwoActivity() {
        return (FamilyRegisterTwoActivity) mView;
    }
}