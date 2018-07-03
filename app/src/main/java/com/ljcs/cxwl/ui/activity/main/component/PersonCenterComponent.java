package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.PersonCenterActivity;
import com.ljcs.cxwl.ui.activity.main.module.PersonCenterModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The component for PersonCenterActivity
 * @date 2018/07/03 08:33:38
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = PersonCenterModule.class)
public interface PersonCenterComponent {
    PersonCenterActivity inject(PersonCenterActivity Activity);
}