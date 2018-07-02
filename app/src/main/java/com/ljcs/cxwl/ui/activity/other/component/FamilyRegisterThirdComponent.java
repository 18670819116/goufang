package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterThirdActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterThirdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyRegisterThirdActivity
 * @date 2018/06/27 19:46:50
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyRegisterThirdModule.class)
public interface FamilyRegisterThirdComponent {
    FamilyRegisterThirdActivity inject(FamilyRegisterThirdActivity Activity);
}