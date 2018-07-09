package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.AboutOurActivity;
import com.ljcs.cxwl.ui.activity.main.module.AboutOurModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The component for AboutOurActivity
 * @date 2018/07/09 11:07:19
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = AboutOurModule.class)
public interface AboutOurComponent {
    AboutOurActivity inject(AboutOurActivity Activity);
}