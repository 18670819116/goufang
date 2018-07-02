package com.ljcs.cxwl.ui.activity.matesinfo.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoOneActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoOneModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The component for MatesInfoOneActivity
 * @date 2018/06/27 16:32:06
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MatesInfoOneModule.class)
public interface MatesInfoOneComponent {
    MatesInfoOneActivity inject(MatesInfoOneActivity Activity);
}