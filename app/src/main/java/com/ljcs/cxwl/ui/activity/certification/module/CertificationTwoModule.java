package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationTwoActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationTwoContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationTwoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The moduele of CertificationTwoActivity, provide field for CertificationTwoActivity
 * @date 2018/06/26 19:26:58
 */
@Module
public class CertificationTwoModule {
    private final CertificationTwoContract.View mView;


    public CertificationTwoModule(CertificationTwoContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationTwoPresenter provideCertificationTwoPresenter(HttpAPIWrapper httpAPIWrapper, CertificationTwoActivity mActivity) {
        return new CertificationTwoPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationTwoActivity provideCertificationTwoActivity() {
        return (CertificationTwoActivity) mView;
    }
}