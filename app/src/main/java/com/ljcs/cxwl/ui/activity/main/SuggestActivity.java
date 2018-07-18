package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerSuggestComponent;
import com.ljcs.cxwl.ui.activity.main.contract.SuggestContract;
import com.ljcs.cxwl.ui.activity.main.module.SuggestModule;
import com.ljcs.cxwl.ui.activity.main.presenter.SuggestPresenter;
import com.ljcs.cxwl.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/06 15:46:33
 */

public class SuggestActivity extends BaseActivity implements SuggestContract.View {

    @Inject
    SuggestPresenter mPresenter;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_count)
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("意见反馈");
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCount.setText(s.length()+"/200");
                if (s.length()>=200){
                    ToastUtil.showCenterShort("最多输入200个字符");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//            s.toString();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerSuggestComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .suggestModule(new SuggestModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(SuggestContract.SuggestContractPresenter presenter) {
        mPresenter = (SuggestPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
    }
}