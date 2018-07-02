package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationFiveActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationFiveModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The component for CertificationFiveActivity
 * @date 2018/06/26 19:29:05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationFiveModule.class)
public interface CertificationFiveComponent {
    CertificationFiveActivity inject(CertificationFiveActivity Activity);
}