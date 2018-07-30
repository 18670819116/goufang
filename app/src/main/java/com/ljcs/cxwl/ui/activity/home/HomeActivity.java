package com.ljcs.cxwl.ui.activity.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.entity.TabDataBean;
import com.ljcs.cxwl.ui.activity.home.component.DaggerHomeComponent;
import com.ljcs.cxwl.ui.activity.home.contract.HomeContract;
import com.ljcs.cxwl.ui.activity.home.module.HomeModule;
import com.ljcs.cxwl.ui.activity.home.presenter.HomePresenter;
import com.ljcs.cxwl.ui.activity.main.ThirdFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.home
 * @Description: $description
 * @date 2018/07/27 14:45:35
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @Inject HomePresenter mPresenter;
    @BindView(R.id.realtabcontent) FrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent) FrameLayout tabcontent;
    @BindView(android.R.id.tabhost) FragmentTabHost tabhost;
    private ArrayList<TabDataBean> tabDataList = new ArrayList<>(4);
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        autolayout.setVisibility(View.GONE);

        mInflater = LayoutInflater.from(this);
        initUi();
    }

    private void initUi() {
        //初始化fTabHost, 第三个参数为内容容器
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        /*初始化数据源*/
        TabDataBean tabHome = new TabDataBean(R.string.app_name, R.drawable.ic_boy, IndexFragment.class);
        TabDataBean tabHot = new TabDataBean(R.string.brvah_app_name, R.drawable.ic_boy, ThirdFragment.class);
        TabDataBean tabCategory = new TabDataBean(R.string.app_name, R.drawable.ic_boy, IndexFragment.class);
        TabDataBean tabCart = new TabDataBean(R.string.app_name, R.drawable.ic_boy, IndexFragment.class);
        tabDataList.add(tabHome);
        tabDataList.add(tabHot);
        tabDataList.add(tabCategory);
        tabDataList.add(tabCart);
        //添加底部菜单项-tabSpec
        for (TabDataBean bean : tabDataList) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(getString(bean.getTabName()));
            //给菜单项添加内容，indicator,其中indicator需要的参数View即为菜单项的布局
            tabSpec.setIndicator(getIndicatorView(bean));
            //第二参数就是该菜单项对应的页面内容
            tabhost.addTab(tabSpec, bean.getContent(), null);
        }

    }

    private View getIndicatorView(TabDataBean bean) {
        View view = mInflater.inflate(R.layout.tabhost_tabspec_normal_layout, null);
        ImageView iconTab = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView tvTab = (TextView) view.findViewById(R.id.tv_tab_label);
        iconTab.setImageResource(bean.getTabIcon());
        tvTab.setText(bean.getTabName());
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerHomeComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(HomeContract.HomeContractPresenter presenter) {
        mPresenter = (HomePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}