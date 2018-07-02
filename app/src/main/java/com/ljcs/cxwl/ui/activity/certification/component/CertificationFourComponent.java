package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationFourActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationFourModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The component for CertificationFourActivity
 * @date 2018/06/26 19:28:19
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationFourModule.class)
public interface CertificationFourComponent {
    CertificationFourActivity inject(CertificationFourActivity Activity);
}