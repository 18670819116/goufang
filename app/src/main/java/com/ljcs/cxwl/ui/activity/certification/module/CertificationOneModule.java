package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationOneActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationOneContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationOnePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The moduele of CertificationOneActivity, provide field for CertificationOneActivity
 * @date 2018/06/26 19:26:21
 */
@Module
public class CertificationOneModule {
    private final CertificationOneContract.View mView;


    public CertificationOneModule(CertificationOneContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationOnePresenter provideCertificationOnePresenter(HttpAPIWrapper httpAPIWrapper,
                                                                      CertificationOneActivity mActivity) {
        return new CertificationOnePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationOneActivity provideCertificationOneActivity() {
        return (CertificationOneActivity) mView;
    }
}