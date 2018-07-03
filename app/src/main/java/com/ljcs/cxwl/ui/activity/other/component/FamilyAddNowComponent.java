package com.ljcs.cxwl.ui.activity.other.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.other.FamilyAddNowActivity;
import com.ljcs.cxwl.ui.activity.other.module.FamilyAddNowModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: The component for FamilyAddNowActivity
 * @date 2018/07/03 11:09:00
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FamilyAddNowModule.class)
public interface FamilyAddNowComponent {
    FamilyAddNowActivity inject(FamilyAddNowActivity Activity);
}