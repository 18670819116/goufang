package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.LoginActivity;
import com.ljcs.cxwl.ui.activity.main.module.LoginModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.login
 * @Description: The component for LoginActivity
 * @date 2017/10/17 08:38:03
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    LoginActivity inject(LoginActivity Activity);
}