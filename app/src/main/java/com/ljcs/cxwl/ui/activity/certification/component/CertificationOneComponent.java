package com.ljcs.cxwl.ui.activity.certification.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.certification.CertificationOneActivity;
import com.ljcs.cxwl.ui.activity.certification.module.CertificationOneModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: The component for CertificationOneActivity
 * @date 2018/06/26 19:26:21
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CertificationOneModule.class)
public interface CertificationOneComponent {
    CertificationOneActivity inject(CertificationOneActivity Activity);
}