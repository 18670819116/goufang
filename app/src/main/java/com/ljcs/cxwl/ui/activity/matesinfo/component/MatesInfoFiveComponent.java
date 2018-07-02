package com.ljcs.cxwl.ui.activity.matesinfo.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoFiveActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoFiveModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The component for MatesInfoFiveActivity
 * @date 2018/06/28 12:16:34
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MatesInfoFiveModule.class)
public interface MatesInfoFiveComponent {
    MatesInfoFiveActivity inject(MatesInfoFiveActivity Activity);
}