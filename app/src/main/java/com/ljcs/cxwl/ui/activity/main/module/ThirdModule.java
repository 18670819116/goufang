package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ThirdFragment;
import com.ljcs.cxwl.ui.activity.main.contract.ThirdContract;
import com.ljcs.cxwl.ui.activity.main.presenter.ThirdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The moduele of ThirdFragment, provide field for ThirdFragment
 * @date 2017/10/18 14:21:40
 */
@Module
public class ThirdModule {
    private final ThirdContract.View mView;


    public ThirdModule(ThirdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ThirdPresenter provideThirdPresenter(HttpAPIWrapper httpAPIWrapper, ThirdFragment mFragment) {
        return new ThirdPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public ThirdFragment provideThirdFragment() {
        return (ThirdFragment) mView;
    }
}