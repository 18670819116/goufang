package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterFourActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterFourModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterFourActivity
 * @date 2018/06/27 20:03:29
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterFourModule.class)
public interface FamilyRegisterFourComponent {
    FamilyRegisterFourActivity inject(FamilyRegisterFourActivity Activity);
}