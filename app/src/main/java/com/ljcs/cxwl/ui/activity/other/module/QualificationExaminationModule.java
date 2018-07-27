package com.ljcs.cxwl.ui.activity.other.module;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.QualificationExaminationActivity;
import com.ljcs.cxwl.ui.activity.other.contract.QualificationExaminationContract;
import com.ljcs.cxwl.ui.activity.other.presenter.QualificationExaminationPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The moduele of QualificationExaminationActivity, provide field for QualificationExaminationActivity
 * @date 2018/06/27 16:49:13
 */
@Module
public class QualificationExaminationModule {
    private final QualificationExaminationContract.View mView;


    public QualificationExaminationModule(QualificationExaminationContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public QualificationExaminationPresenter provideQualificationExaminationPresenter(HttpAPIWrapper httpAPIWrapper,
                                                                                      QualificationExaminationActivity mActivity) {
        return new QualificationExaminationPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public QualificationExaminationActivity provideQualificationExaminationActivity() {
        return (QualificationExaminationActivity) mView;
    }
}