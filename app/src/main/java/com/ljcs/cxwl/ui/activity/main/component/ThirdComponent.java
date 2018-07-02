package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.ThirdFragment;
import com.ljcs.cxwl.ui.activity.main.module.ThirdModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: The component for ThirdFragment
 * @date 2017/10/18 14:21:40
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ThirdModule.class)
public interface ThirdComponent {
    ThirdFragment inject(ThirdFragment Fragment);
}