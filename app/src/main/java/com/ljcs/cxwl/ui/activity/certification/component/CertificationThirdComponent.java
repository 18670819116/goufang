package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationThirdActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationThirdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The component for CertificationThirdActivity
 * @date 2018/06/26 19:27:38
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationThirdModule.class)
public interface CertificationThirdComponent {
    CertificationThirdActivity inject(CertificationThirdActivity Activity);
}