package com.ljcs.cxwl.ui.activity.matesinfo.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.matesinfo.MatesInfoThirdActivity;
import com.ljcs.cxwl.ui.activity.matesinfo.module.MatesInfoThirdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.matesinfo
 * @Description: The component for MatesInfoThirdActivity
 * @date 2018/06/27 16:33:53
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MatesInfoThirdModule.class)
public interface MatesInfoThirdComponent {
    MatesInfoThirdActivity inject(MatesInfoThirdActivity Activity);
}