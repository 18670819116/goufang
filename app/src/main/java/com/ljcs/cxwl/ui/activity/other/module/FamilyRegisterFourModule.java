package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterFourActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterFourContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterFourPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterFourActivity, provide field for FamilyRegisterFourActivity
 * @date 2018/06/27 20:03:29
 */
@Module
public class FamilyRegisterFourModule {
    private final FamilyRegisterFourContract.View mView;


    public FamilyRegisterFourModule(FamilyRegisterFourContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterFourPresenter provideFamilyRegisterFourPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                          FamilyRegisterFourActivity mActivity) {
        return new FamilyRegisterFourPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterFourActivity provideFamilyRegisterFourActivity() {
        return (FamilyRegisterFourActivity) mView;
    }
}