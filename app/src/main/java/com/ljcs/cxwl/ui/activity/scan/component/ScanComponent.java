package com.ljcs.cxwl.ui.activity.scan.component;

import com.ljcs.cxwl.application.AppComponent;
import com.ljcs.cxwl.ui.activity.base.ActivityScope;
import com.ljcs.cxwl.ui.activity.scan.ScanActivity;
import com.ljcs.cxwl.ui.activity.scan.module.ScanModule;

import dagger.Component;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.scan
 * @Description: The component for ScanActivity
 * @date 2018/07/13 11:32:30
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ScanModule.class)
public interface ScanComponent {
    ScanActivity inject(ScanActivity Activity);
}