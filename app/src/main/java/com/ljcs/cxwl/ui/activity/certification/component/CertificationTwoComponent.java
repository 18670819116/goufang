package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationTwoActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationTwoModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The component for CertificationTwoActivity
 * @date 2018/06/26 19:26:58
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationTwoModule.class)
public interface CertificationTwoComponent {
    CertificationTwoActivity inject(CertificationTwoActivity Activity);
}