package com.ljcs.cxwl.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Date 2018/6/28.
 */

public class AgreementActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_agreement);
        ButterKnife.bind(this);
        toolbarTitle.setText("新用户注册协议");
    }

    @Override
    protected void setupActivityComponent() {

    }


}
