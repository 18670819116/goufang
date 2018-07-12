package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwo2Activity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwo2Module;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterTwo2Activity
 * @date 2018/07/10 10:43:02
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterTwo2Module.class)
public interface FamilyRegisterTwo2Component {
    FamilyRegisterTwo2Activity inject(FamilyRegisterTwo2Activity Activity);
}