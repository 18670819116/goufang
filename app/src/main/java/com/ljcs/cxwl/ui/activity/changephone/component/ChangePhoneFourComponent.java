package com.ljcs.cxwl.ui.activity.changephone.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneFourActivity;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneFourModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The component for ChangePhoneFourActivity
 * @date 2018/07/19 20:33:44
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ChangePhoneFourModule.class)
public interface ChangePhoneFourComponent {
    ChangePhoneFourActivity inject(ChangePhoneFourActivity Activity);
}