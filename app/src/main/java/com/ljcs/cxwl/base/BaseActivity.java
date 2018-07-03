package com.ljcs.cxwl.base;

import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.view.ProgressDialog;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.util.UIUtils;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxDataTool;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


/**
 * @author Yuan.Y.Q
 * @Date 2017/9/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {
    public Toolbar mToolbar;
    private RelativeLayout rootLayout;
    public LinearLayout autolayout;
    public boolean needFront = false;   //toolBar 是否需要显示在最上层的标识
    public ProgressDialog progressDialog;
    public TextView toolbarTitle;
    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        AppManager.getInstance().addActivity(this);
        progressDialog = new ProgressDialog(this);
        initToolbar();
        setupActivityComponent();
        initView();
        initData();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbarBusiness);
        autolayout = (LinearLayout) findViewById(R.id.toolbar_autolayout);
        mToolbar.setTitle("");
        toolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(UIUtils.getDisplayWidth(this),
                UIUtils.getStatusBarHeight(this) * 3);
        autolayout.setLayoutParams(layoutParams);
        autolayout.setPadding(0, (UIUtils.getStatusBarHeight(this)), 0, 0);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setToolbarTitle(String title) {
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (RelativeLayout) findViewById(R.id.root_layout);


        if (rootLayout == null) {
            return;
        }
        if (needFront) {
            autolayout.setBackgroundColor(getResources().getColor(R.color.color_00000000));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.color_00000000));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            rootLayout.addView(view, params);
            autolayout.bringToFront();
        } else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.BELOW, R.id.toolbar_autolayout);
            rootLayout.addView(view, params);
        }
        initToolbar();
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化dagger2
     */
    protected abstract void setupActivityComponent();

    protected <T> void startActivty(Class<T> clazz) {
        Intent intent = new Intent(this, clazz);
        try {
            startActivity(intent);
        } catch (Exception e) {
//            ToastUtil.show(this, "敬请期待！");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);

    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();

    }

    public void onErrorMsg(int code, String msg) {
        if (!RxDataTool.isNullString(msg)) {
            ToastUtil.showCenterShort(msg);
        }

    }
}
