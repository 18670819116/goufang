package com.ljcs.cxwl.ui.activity.main.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.main.SuggestActivity;
import com.ljcs.cxwl.ui.activity.main.module.SuggestModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: The component for SuggestActivity
 * @date 2018/07/06 15:46:33
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SuggestModule.class)
public interface SuggestComponent {
    SuggestActivity inject(SuggestActivity Activity);
}