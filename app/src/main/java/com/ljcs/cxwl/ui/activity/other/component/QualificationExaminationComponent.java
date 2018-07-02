package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.QualificationExaminationActivity;
import com.ljcs.cxwl.ui.activity.other.module.QualificationExaminationModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for QualificationExaminationActivity
 * @date 2018/06/27 16:49:13
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = QualificationExaminationModule.class)
public interface QualificationExaminationComponent {
    QualificationExaminationActivity inject(QualificationExaminationActivity Activity);
}