package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: The component for CertificationActivity
 * @date 2018/07/11 08:32:05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationModule.class)
public interface CertificationComponent {
    CertificationActivity inject(CertificationActivity Activity);
}