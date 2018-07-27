package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyAddNowActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyAddNowContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyAddNowPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyAddNowActivity, provide field for FamilyAddNowActivity
 * @date 2018/07/03 11:09:00
 */
@Module
public class FamilyAddNowModule {
    private final FamilyAddNowContract.View mView;


    public FamilyAddNowModule(FamilyAddNowContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyAddNowPresenter provideFamilyAddNowPresenter(HttpAPIWrapper httpAPIWrapper, FamilyAddNowActivity
            mActivity) {
        return new FamilyAddNowPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyAddNowActivity provideFamilyAddNowActivity() {
        return (FamilyAddNowActivity) mView;
    }
}