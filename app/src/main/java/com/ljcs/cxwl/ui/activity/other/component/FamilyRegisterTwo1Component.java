package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwo1Activity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwo1Module;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterTwo1Activity
 * @date 2018/07/11 15:00:45
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterTwo1Module.class)
public interface FamilyRegisterTwo1Component {
    FamilyRegisterTwo1Activity inject(FamilyRegisterTwo1Activity Activity);
}