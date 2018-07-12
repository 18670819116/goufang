package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwo2Activity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwo2Contract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterTwo2Presenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyRegisterTwo2Activity, provide field for FamilyRegisterTwo2Activity
 * @date 2018/07/10 10:43:02
 */
@Module
public class FamilyRegisterTwo2Module {
    private final FamilyRegisterTwo2Contract.View mView;


    public FamilyRegisterTwo2Module(FamilyRegisterTwo2Contract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyRegisterTwo2Presenter provideFamilyRegisterTwo2Presenter(HttpAPIWrapper httpAPIWrapper, FamilyRegisterTwo2Activity mActivity) {
        return new FamilyRegisterTwo2Presenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyRegisterTwo2Activity provideFamilyRegisterTwo2Activity() {
        return (FamilyRegisterTwo2Activity) mView;
    }
}