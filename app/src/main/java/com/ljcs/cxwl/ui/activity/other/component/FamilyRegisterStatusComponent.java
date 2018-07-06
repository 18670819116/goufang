package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterStatusActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterStatusModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterStatusActivity
 * @date 2018/07/05 10:00:42
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterStatusModule.class)
public interface FamilyRegisterStatusComponent {
    FamilyRegisterStatusActivity inject(FamilyRegisterStatusActivity Activity);
}