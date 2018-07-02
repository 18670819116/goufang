package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.RegisterActivity;
import com.ljcs.cxwl.ui.activity.main.module.RegisterModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The component for RegisterActivity
 * @date 2018/06/26 14:58:24
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {
    RegisterActivity inject(RegisterActivity Activity);
}