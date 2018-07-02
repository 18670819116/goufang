package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyAddActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyAddContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyAddPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyAddActivity, provide field for FamilyAddActivity
 * @date 2018/07/02 10:34:10
 */
@Module
public class FamilyAddModule {
    private final FamilyAddContract.View mView;


    public FamilyAddModule(FamilyAddContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyAddPresenter provideFamilyAddPresenter(HttpAPIWrapper httpAPIWrapper, FamilyAddActivity mActivity) {
        return new FamilyAddPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyAddActivity provideFamilyAddActivity() {
        return (FamilyAddActivity) mView;
    }
}