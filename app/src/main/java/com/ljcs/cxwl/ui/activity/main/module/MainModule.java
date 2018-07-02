package com.ljcs.cxwl.ui.activity.main.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.MainActivity;
import com.ljcs.cxwl.ui.activity.main.contract.MainContract;
import com.ljcs.cxwl.ui.activity.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The moduele of MainActivity, provide field for MainActivity
 * @date 2018/07/02 16:17:40
 */
@Module
public class MainModule {
    private final MainContract.View mView;


    public MainModule(MainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MainPresenter provideMainPresenter(HttpAPIWrapper httpAPIWrapper, MainActivity mActivity) {
        return new MainPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MainActivity provideMainActivity() {
        return (MainActivity) mView;
    }
}