package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterActivity, provide field for FamilyRegisterActivity
 * @date 2018/06/27 17:51:07
 */
@Module
public class FamilyRegisterModule {
    private final FamilyRegisterContract.View mView;


    public FamilyRegisterModule(FamilyRegisterContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterPresenter provideFamilyRegisterPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                  FamilyRegisterActivity mActivity) {
        return new FamilyRegisterPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterActivity provideFamilyRegisterActivity() {
        return (FamilyRegisterActivity) mView;
    }
}