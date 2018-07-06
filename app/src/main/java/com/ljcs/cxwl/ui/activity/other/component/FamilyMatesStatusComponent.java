package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyMatesStatusActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyMatesStatusModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyMatesStatusActivity
 * @date 2018/07/05 13:56:02
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyMatesStatusModule.class)
public interface FamilyMatesStatusComponent {
    FamilyMatesStatusActivity inject(FamilyMatesStatusActivity Activity);
}