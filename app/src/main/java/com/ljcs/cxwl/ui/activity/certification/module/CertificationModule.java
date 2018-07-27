package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: The moduele of CertificationActivity, provide field for CertificationActivity
 * @date 2018/07/11 08:32:05
 */
@Module
public class CertificationModule {
    private final CertificationContract.View mView;


    public CertificationModule(CertificationContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationPresenter provideCertificationPresenter(HttpAPIWrapper httpAPIWrapper, CertificationActivity
            mActivity) {
        return new CertificationPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationActivity provideCertificationActivity() {
        return (CertificationActivity) mView;
    }
}