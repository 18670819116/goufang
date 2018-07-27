package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwo1Activity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwo1Contract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwo1Presenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterTwo1Activity, provide field for FamilyRegisterTwo1Activity
 * @date 2018/07/11 15:00:45
 */
@Module
public class FamilyRegisterTwo1Module {
    private final FamilyRegisterTwo1Contract.View mView;


    public FamilyRegisterTwo1Module(FamilyRegisterTwo1Contract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterTwo1Presenter provideFamilyRegisterTwo1Presenter(HttpAPIWrapper httpAPIWrapper,
                                                                          FamilyRegisterTwo1Activity mActivity) {
        return new FamilyRegisterTwo1Presenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterTwo1Activity provideFamilyRegisterTwo1Activity() {
        return (FamilyRegisterTwo1Activity) mView;
    }
}