package com.ljcs.cxwl.ui.activity.changephone.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneOneActivity;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneOneModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The component for ChangePhoneOneActivity
 * @date 2018/07/09 16:38:08
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ChangePhoneOneModule.class)
public interface ChangePhoneOneComponent {
    ChangePhoneOneActivity inject(ChangePhoneOneActivity Activity);
}