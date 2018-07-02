package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwoActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterTwoModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterTwoActivity
 * @date 2018/06/27 19:05:45
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterTwoModule.class)
public interface FamilyRegisterTwoComponent {
    FamilyRegisterTwoActivity inject(FamilyRegisterTwoActivity Activity);
}