package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterActivity
 * @date 2018/06/27 17:51:07
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterModule.class)
public interface FamilyRegisterComponent {
    FamilyRegisterActivity inject(FamilyRegisterActivity Activity);
}