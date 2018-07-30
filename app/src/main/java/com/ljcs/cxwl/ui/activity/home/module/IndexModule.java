package com.ljcs.cxwl.ui.activity.home.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.home.IndexFragment;
import com.ljcs.cxwl.ui.activity.home.contract.IndexContract;
import com.ljcs.cxwl.ui.activity.home.presenter.IndexPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.home
 * @Description: The moduele of IndexFragment, provide field for IndexFragment
 * @date 2018/07/27 15:03:41
 */
@Module
public class IndexModule {
    private final IndexContract.View mView;


    public IndexModule(IndexContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public IndexPresenter provideIndexPresenter(HttpAPIWrapper httpAPIWrapper, IndexFragment mFragment) {
        return new IndexPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public IndexFragment provideIndexFragment() {
        return (IndexFragment) mView;
    }
}