package com.ljcs.cxwl.ui.activity.web;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.web.component.DaggerWebSatisficingComponent;
import com.ljcs.cxwl.ui.activity.web.contract.WebSatisficingContract;
import com.ljcs.cxwl.ui.activity.web.module.WebSatisficingModule;
import com.ljcs.cxwl.ui.activity.web.presenter.WebSatisficingPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.web
 * @Description: $description
 * @date 2018/07/12 11:56:05
 */

public class WebSatisficingActivity extends BaseActivity implements WebSatisficingContract.View {

    @Inject WebSatisficingPresenter mPresenter;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.webView) WebView webView;

    private String name;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_webview_sat);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        toolbarTitle.setText(name);
        address = bundle.getString("address");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        init();
    }

    private void init() {
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultFontSize(16);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        WwebView.addJavascriptInterface(new PayJavaScriptInterface(), "js");
//        WwebView.loadUrl("javascript:callFromJava('1')");
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        // WebView加载web资源
        webView.loadUrl(address);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.GONE);
                } else {
                    // 加载中
                    if (View.GONE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerWebSatisficingComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent()
        ).webSatisficingModule(new WebSatisficingModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(WebSatisficingContract.WebSatisficingContractPresenter presenter) {
        mPresenter = (WebSatisficingPresenter) presenter;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();// 返回上一页面
                return true;
            } else {
                finish();// 退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}