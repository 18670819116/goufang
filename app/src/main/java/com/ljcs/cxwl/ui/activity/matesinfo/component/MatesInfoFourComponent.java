package com.ljcs.cxwl.ui.activity.matesinfo.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoFourActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoFourModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The component for MatesInfoFourActivity
 * @date 2018/06/27 16:35:11
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MatesInfoFourModule.class)
public interface MatesInfoFourComponent {
    MatesInfoFourActivity inject(MatesInfoFourActivity Activity);
}