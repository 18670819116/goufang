package com.ljcs.cxwl.ui.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.adapter.GlideImageLoader;
import com.ljcs.cxwl.adapter.RvAdapter;
import com.ljcs.cxwl.adapter.index.ThirdAdapter;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseFragment;
import com.ljcs.cxwl.ui.activity.home.component.DaggerIndexComponent;
import com.ljcs.cxwl.ui.activity.home.contract.IndexContract;
import com.ljcs.cxwl.ui.activity.home.module.IndexModule;
import com.ljcs.cxwl.ui.activity.home.presenter.IndexPresenter;
import com.ljcs.cxwl.util.UIUtils;
import com.ljcs.cxwl.view.GridMenu;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.home
 * @Description: $description
 * @date 2018/07/27 15:03:41
 */

public class IndexFragment extends BaseFragment implements IndexContract.View, RvAdapter.OnItemClickListener, View
        .OnClickListener {
    private String[] gridMenuTitles = new String[]{"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "到家",
            "分类"};
    @Inject IndexPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;
    private List<String> images = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initRv();
        return view;
    }

    private void initRv() {
        RvAdapter rvAdapter = new RvAdapter();
        rvAdapter.setmOnItemClickLitener(this);
        initBannerTop(rvAdapter);
        initHeadLines(rvAdapter);
        initGridMenu(rvAdapter);
        initSnapUp(rvAdapter);
        initMiddleBannner(rvAdapter);
        initHotMarket(rvAdapter);
        initGoodsTrend(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(rvAdapter);
    }

    private void initGoodsTrend(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getActivity(), R.layout.index_banner_top, null);
        Banner bannerTop = (Banner) bannerBigView.findViewById(R.id.bannerTop);
        //设置图片加载器
        bannerTop.setImageLoader(new GlideImageLoader());
        //设置图片集合
        images.clear();
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerTop.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        bannerTop.start();

        rvAdapter.addHeaderView6(bannerBigView);

    }

    private void initHotMarket(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getActivity(), R.layout.index_banner_top, null);
        Banner bannerTop = (Banner) bannerBigView.findViewById(R.id.bannerTop);
        //设置图片加载器
        bannerTop.setImageLoader(new GlideImageLoader());
        //设置图片集合
        images.clear();
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerTop.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        bannerTop.start();

        rvAdapter.addHeaderView5(bannerBigView);

    }

    private void initMiddleBannner(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getActivity(), R.layout.index_three, null);
        RecyclerView recyclerView = (RecyclerView) bannerBigView.findViewById(R.id.rv_three);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
       ThirdAdapter adapter= new ThirdAdapter();
        List<String> list = new ArrayList<>();
        list.add("https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign" +
                "=3872b066e550352aae6123086342fb1a/f11f3a292df5e0fe9ba436d0506034a85fdf72a6.jpg");
        list.add("https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign" +
                "=3872b066e550352aae6123086342fb1a/f11f3a292df5e0fe9ba436d0506034a85fdf72a6.jpg");
        list.add("https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign" +
                "=3872b066e550352aae6123086342fb1a/f11f3a292df5e0fe9ba436d0506034a85fdf72a6.jpg");
        list.add("https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign" +
                "=3872b066e550352aae6123086342fb1a/f11f3a292df5e0fe9ba436d0506034a85fdf72a6.jpg");
        recyclerView.setAdapter(adapter);
        rvAdapter.addHeaderView3(bannerBigView);
        adapter.setNewData(list);

    }

    private void initSnapUp(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getActivity(), R.layout.index_banner_top, null);
        Banner bannerTop = (Banner) bannerBigView.findViewById(R.id.bannerTop);
        //设置图片加载器
        bannerTop.setImageLoader(new GlideImageLoader());
        //设置图片集合
        images.clear();
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerTop.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        bannerTop.start();

        rvAdapter.addHeaderView4(bannerBigView);

    }

    private void initHeadLines(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getActivity(), R.layout.index_paomadeng, null);
        //设置图片加载器
        MarqueeView marqueeView = (MarqueeView) bannerBigView.findViewById(R.id.marqueeView);

        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是孙福生。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：孙福生微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：孙福生");
        marqueeView.startWithList(info);
        rvAdapter.addHeaderView1(bannerBigView);
    }

    private void initGridMenu(RvAdapter rvAdapter) {
        View gridMenu = View.inflate(UIUtils.getContext(), R.layout.index_grid_menu_10, null);

        List<GridMenu> gridMenus = new ArrayList<>();
        GridMenu gridCat = (GridMenu) gridMenu.findViewById(R.id.gridCat);
        GridMenu gridJHS = (GridMenu) gridMenu.findViewById(R.id.gridJHS);
        GridMenu gridTMgj = (GridMenu) gridMenu.findViewById(R.id.gridTMgj);
        GridMenu grid_wm = (GridMenu) gridMenu.findViewById(R.id.grid_wm);

        GridMenu grid_czzx = (GridMenu) gridMenu.findViewById(R.id.grid_czzx);
        GridMenu grid_fzlx = (GridMenu) gridMenu.findViewById(R.id.grid_fzlx);
        GridMenu grid_ljb = (GridMenu) gridMenu.findViewById(R.id.grid_ljb);
        GridMenu grid_dj = (GridMenu) gridMenu.findViewById(R.id.grid_dj);

        gridMenus.add(gridCat);
        gridMenus.add(gridJHS);
        gridMenus.add(gridTMgj);
        gridMenus.add(grid_wm);
        gridMenus.add(grid_czzx);
        gridMenus.add(grid_fzlx);
        gridMenus.add(grid_ljb);
        gridMenus.add(grid_dj);

        initGridMenuAttr(gridMenus);
        initOnCLick(gridMenus);
        rvAdapter.addHeaderView2(gridMenu);
    }

    /**
     * 设置10个子菜单的图片和文字
     */
    private void initGridMenuAttr(List<GridMenu> gridMenus) {
        for (int i = 0; i < gridMenus.size(); i++) {
            GridMenu gridMenu = gridMenus.get(i);
            gridMenu.setAttr(R.mipmap.ic_clock, gridMenuTitles[i]);
        }
    }

    private void initOnCLick(List<GridMenu> gridMenus) {
        for (GridMenu gridMenu : gridMenus) {
            gridMenu.setOnClickListener(this);
        }
    }

    private void initBannerTop(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getActivity(), R.layout.index_banner_top, null);
        Banner bannerTop = (Banner) bannerBigView.findViewById(R.id.bannerTop);
        //设置图片加载器
        bannerTop.setImageLoader(new GlideImageLoader());
        //设置图片集合
        images.clear();
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerTop.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        bannerTop.start();

        rvAdapter.addHeaderView7(bannerBigView);

    }

    @Override
    protected void setupFragmentComponent() {
        DaggerIndexComponent.builder().appComponent(((AppConfig) getActivity().getApplication())
                .getApplicationComponent()).indexModule(new IndexModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(IndexContract.IndexContractPresenter presenter) {
        mPresenter = (IndexPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onItemClick(View v, int postion) {

    }

    @Override
    public void onItemLongClick(View v, int postion) {

    }

    @Override
    public void onClick(View v) {

    }
}