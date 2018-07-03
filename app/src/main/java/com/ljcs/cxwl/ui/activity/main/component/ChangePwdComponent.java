package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ChangePwdActivity;
import com.ljcs.cxwl.ui.activity.main.module.ChangePwdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The component for ChangePwdActivity
 * @date 2018/07/03 13:37:53
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ChangePwdModule.class)
public interface ChangePwdComponent {
    ChangePwdActivity inject(ChangePwdActivity Activity);
}