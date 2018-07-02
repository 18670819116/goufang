package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.SplashActivity;
import com.ljcs.cxwl.ui.activity.main.module.SplashModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The component for SplashActivity
 * @date 2018/06/26 08:44:55
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    SplashActivity inject(SplashActivity Activity);
}