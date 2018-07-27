package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyMatesStatusActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyMatesStatusContract;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyMatesStatusPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of FamilyMatesStatusActivity, provide field for FamilyMatesStatusActivity
 * @date 2018/07/05 13:56:02
 */
@Module
public class FamilyMatesStatusModule {
    private final FamilyMatesStatusContract.View mView;


    public FamilyMatesStatusModule(FamilyMatesStatusContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FamilyMatesStatusPresenter provideFamilyMatesStatusPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                        FamilyMatesStatusActivity mActivity) {
        return new FamilyMatesStatusPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FamilyMatesStatusActivity provideFamilyMatesStatusActivity() {
        return (FamilyMatesStatusActivity) mView;
    }
}