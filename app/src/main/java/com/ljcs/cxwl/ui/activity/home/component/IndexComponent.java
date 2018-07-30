package com.ljcs.cxwl.ui.activity.home.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.home.IndexFragment;
import com.ljcs.cxwl.ui.activity.home.module.IndexModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.home
 * @Description: The component for IndexFragment
 * @date 2018/07/27 15:03:41
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = IndexModule.class)
public interface IndexComponent {
    IndexFragment inject(IndexFragment Fragment);
}