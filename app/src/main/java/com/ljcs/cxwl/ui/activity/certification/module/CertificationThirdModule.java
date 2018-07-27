package com.ljcs.cxwl.ui.activity.certification.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationThirdActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationThirdContract;
import com.ljcs.cxwl.ui.activity.certification.presenter.CertificationThirdPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The moduele of CertificationThirdActivity, provide field for CertificationThirdActivity
 * @date 2018/06/26 19:27:38
 */
@Module
public class CertificationThirdModule {
    private final CertificationThirdContract.View mView;


    public CertificationThirdModule(CertificationThirdContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CertificationThirdPresenter provideCertificationThirdPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                          CertificationThirdActivity mActivity) {
        return new CertificationThirdPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CertificationThirdActivity provideCertificationThirdActivity() {
        return (CertificationThirdActivity) mView;
    }
}