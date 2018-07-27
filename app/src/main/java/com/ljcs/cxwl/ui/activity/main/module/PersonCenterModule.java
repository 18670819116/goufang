package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.PersonCenterActivity;
import com.ljcs.cxwl.ui.activity.main.contract.PersonCenterContract;
import com.ljcs.cxwl.ui.activity.main.presenter.PersonCenterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The moduele of PersonCenterActivity, provide field for PersonCenterActivity
 * @date 2018/07/03 08:33:38
 */
@Module
public class PersonCenterModule {
    private final PersonCenterContract.View mView;


    public PersonCenterModule(PersonCenterContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PersonCenterPresenter providePersonCenterPresenter(HttpAPIWrapper httpAPIWrapper, PersonCenterActivity
            mActivity) {
        return new PersonCenterPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PersonCenterActivity providePersonCenterActivity() {
        return (PersonCenterActivity) mView;
    }
}