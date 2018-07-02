package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ForgetPwdActivity;
import com.ljcs.cxwl.ui.activity.main.module.ForgetPwdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The component for ForgetPwdActivity
 * @date 2018/06/26 16:39:54
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ForgetPwdModule.class)
public interface ForgetPwdComponent {
    ForgetPwdActivity inject(ForgetPwdActivity Activity);
}