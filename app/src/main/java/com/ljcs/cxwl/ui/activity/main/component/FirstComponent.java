package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.FirstFragment;
import com.ljcs.cxwl.ui.activity.main.module.FirstModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The component for FirstFragment
 * @date 2017/10/18 11:56:00
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = FirstModule.class)
public interface FirstComponent {
    FirstFragment inject(FirstFragment Fragment);
}