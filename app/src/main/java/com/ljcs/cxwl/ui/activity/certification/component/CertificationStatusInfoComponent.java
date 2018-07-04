package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationStatusInfoActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationStatusInfoModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: The component for CertificationStatusInfoActivity
 * @date 2018/07/03 21:25:58
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationStatusInfoModule.class)
public interface CertificationStatusInfoComponent {
    CertificationStatusInfoActivity inject(CertificationStatusInfoActivity Activity);
}