package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationFourActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFourContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationFourPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The moduele of CertificationFourActivity, provide field for CertificationFourActivity
 * @date 2018/06/26 19:28:19
 */
@Module
public class CertificationFourModule {
    private final CertificationFourContract.View mView;


    public CertificationFourModule(CertificationFourContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationFourPresenter provideCertificationFourPresenter(HttpAPIWrapper httpAPIWrapper, CertificationFourActivity mActivity) {
        return new CertificationFourPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationFourActivity provideCertificationFourActivity() {
        return (CertificationFourActivity) mView;
    }
}