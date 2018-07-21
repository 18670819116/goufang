package com.ljcs.cxwl.ui.activity.web.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.web.WebSatisficingActivity;
import com.ljcs.cxwl.ui.activity.web.module.WebSatisficingModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.web
 * @Description: The component for WebSatisficingActivity
 * @date 2018/07/12 11:56:05
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = WebSatisficingModule.class)
public interface WebSatisficingComponent {
    WebSatisficingActivity inject(WebSatisficingActivity Activity);
}