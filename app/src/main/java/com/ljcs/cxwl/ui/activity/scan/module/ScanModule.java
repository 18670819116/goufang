package com.ljcs.cxwl.ui.activity.scan.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.scan.ScanActivity;
import com.ljcs.cxwl.ui.activity.scan.contract.ScanContract;
import com.ljcs.cxwl.ui.activity.scan.presenter.ScanPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.scan
 * @Description: The moduele of ScanActivity, provide field for ScanActivity
 * @date 2018/07/13 11:32:30
 */
@Module
public class ScanModule {
    private final ScanContract.View mView;


    public ScanModule(ScanContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ScanPresenter provideScanPresenter(HttpAPIWrapper httpAPIWrapper, ScanActivity mActivity) {
        return new ScanPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ScanActivity provideScanActivity() {
        return (ScanActivity) mView;
    }
}