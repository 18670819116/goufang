package com.ljcs.cxwl.ui.activity.changephone.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneThirdActivity;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneThirdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The component for ChangePhoneThirdActivity
 * @date 2018/07/09 16:39:43
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ChangePhoneThirdModule.class)
public interface ChangePhoneThirdComponent {
    ChangePhoneThirdActivity inject(ChangePhoneThirdActivity Activity);
}