package com.ljcs.cxwl.ui.activity.changephone.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneTwoActivity;
import com.ljcs.cxwl.ui.activity.changephone.module.ChangePhoneTwoModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: The component for ChangePhoneTwoActivity
 * @date 2018/07/09 16:39:17
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ChangePhoneTwoModule.class)
public interface ChangePhoneTwoComponent {
    ChangePhoneTwoActivity inject(ChangePhoneTwoActivity Activity);
}