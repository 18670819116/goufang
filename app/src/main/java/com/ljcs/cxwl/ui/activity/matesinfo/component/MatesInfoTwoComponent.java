package com.ljcs.cxwl.ui.activity.matesinfo.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoTwoActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoTwoModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The component for MatesInfoTwoActivity
 * @date 2018/06/27 16:33:03
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MatesInfoTwoModule.class)
public interface MatesInfoTwoComponent {
    MatesInfoTwoActivity inject(MatesInfoTwoActivity Activity);
}