package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationStatusInfoActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationStatusInfoContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationStatusInfoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: The moduele of CertificationStatusInfoActivity, provide field for CertificationStatusInfoActivity
 * @date 2018/07/03 21:25:58
 */
@Module
public class CertificationStatusInfoModule {
    private final CertificationStatusInfoContract.View mView;


    public CertificationStatusInfoModule(CertificationStatusInfoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationStatusInfoPresenter provideCertificationStatusInfoPresenter(HttpAPIWrapper httpAPIWrapper, CertificationStatusInfoActivity mActivity) {
        return new CertificationStatusInfoPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationStatusInfoActivity provideCertificationStatusInfoActivity() {
        return (CertificationStatusInfoActivity) mView;
    }
}