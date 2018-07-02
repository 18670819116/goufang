package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyAddActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyAddModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyAddActivity
 * @date 2018/07/02 10:34:10
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyAddModule.class)
public interface FamilyAddComponent {
    FamilyAddActivity inject(FamilyAddActivity Activity);
}