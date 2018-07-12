package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.AboutCertificationActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.AboutCertificationContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.AboutCertificationPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: The moduele of AboutCertificationActivity, provide field for AboutCertificationActivity
 * @date 2018/07/10 10:26:14
 */
@Module
public class AboutCertificationModule {
    private final AboutCertificationContract.View mView;


    public AboutCertificationModule(AboutCertificationContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AboutCertificationPresenter provideAboutCertificationPresenter(HttpAPIWrapper httpAPIWrapper, AboutCertificationActivity mActivity) {
        return new AboutCertificationPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AboutCertificationActivity provideAboutCertificationActivity() {
        return (AboutCertificationActivity) mView;
    }
}