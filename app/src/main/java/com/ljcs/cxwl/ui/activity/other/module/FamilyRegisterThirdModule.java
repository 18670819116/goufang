package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterThirdActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterThirdContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterThirdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterThirdActivity, provide field for FamilyRegisterThirdActivity
 * @date 2018/06/27 19:46:50
 */
@Module
public class FamilyRegisterThirdModule {
    private final FamilyRegisterThirdContract.View mView;


    public FamilyRegisterThirdModule(FamilyRegisterThirdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterThirdPresenter provideFamilyRegisterThirdPresenter(HttpAPIWrapper httpAPIWrapper, FamilyRegisterThirdActivity mActivity) {
        return new FamilyRegisterThirdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterThirdActivity provideFamilyRegisterThirdActivity() {
        return (FamilyRegisterThirdActivity) mView;
    }
}