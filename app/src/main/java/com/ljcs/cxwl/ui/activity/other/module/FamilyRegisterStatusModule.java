package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterStatusActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterStatusContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterStatusPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterStatusActivity, provide field for FamilyRegisterStatusActivity
 * @date 2018/07/05 10:00:42
 */
@Module
public class FamilyRegisterStatusModule {
    private final FamilyRegisterStatusContract.View mView;


    public FamilyRegisterStatusModule(FamilyRegisterStatusContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterStatusPresenter provideFamilyRegisterStatusPresenter(HttpAPIWrapper httpAPIWrapper, FamilyRegisterStatusActivity mActivity) {
        return new FamilyRegisterStatusPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterStatusActivity provideFamilyRegisterStatusActivity() {
        return (FamilyRegisterStatusActivity) mView;
    }
}