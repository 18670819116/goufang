package com.ljcs.cxwl.ui.activity.home.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.home.HomeActivity;
import com.ljcs.cxwl.ui.activity.home.module.HomeModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.home
 * @Description: The component for HomeActivity
 * @date 2018/07/27 14:45:35
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    HomeActivity inject(HomeActivity Activity);
}