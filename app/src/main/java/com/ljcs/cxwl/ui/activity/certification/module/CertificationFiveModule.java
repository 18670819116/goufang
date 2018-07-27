package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationFiveActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFiveContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationFivePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The moduele of CertificationFiveActivity, provide field for CertificationFiveActivity
 * @date 2018/06/26 19:29:05
 */
@Module
public class CertificationFiveModule {
    private final CertificationFiveContract.View mView;


    public CertificationFiveModule(CertificationFiveContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationFivePresenter provideCertificationFivePresenter(HttpAPIWrapper httpAPIWrapper,
                                                                        CertificationFiveActivity mActivity) {
        return new CertificationFivePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationFiveActivity provideCertificationFiveActivity() {
        return (CertificationFiveActivity) mView;
    }
}