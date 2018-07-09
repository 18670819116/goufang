package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ComplainActivity;
import com.ljcs.cxwl.ui.activity.main.module.ComplainModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The component for ComplainActivity
 * @date 2018/07/09 10:58:14
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ComplainModule.class)
public interface ComplainComponent {
    ComplainActivity inject(ComplainActivity Activity);
}