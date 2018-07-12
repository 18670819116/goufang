package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.AboutCertificationActivity;
import com.ljcs.cxwl.ui.activity.certification.module.AboutCertificationModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: The component for AboutCertificationActivity
 * @date 2018/07/10 10:26:14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AboutCertificationModule.class)
public interface AboutCertificationComponent {
    AboutCertificationActivity inject(AboutCertificationActivity Activity);
}