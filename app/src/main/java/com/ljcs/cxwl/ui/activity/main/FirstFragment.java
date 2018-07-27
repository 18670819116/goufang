package com.ljcs.cxwl.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseFragment;
import com.ljcs.cxwl.ui.activity.main.component.DaggerFirstComponent;
import com.ljcs.cxwl.ui.activity.main.contract.FirstContract;
import com.ljcs.cxwl.ui.activity.main.module.FirstModule;
import com.ljcs.cxwl.ui.activity.main.presenter.FirstPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: $description
 * @date 2017/10/18 11:56:00
 */

public class FirstFragment extends BaseFragment implements FirstContract.View {

    @Inject FirstPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        TextView tvAndroid123 = (TextView) view.findViewById(R.id.tvCWJ);

        tvAndroid123.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvAndroid123.setText("数据库公司的古代诗歌鉴赏大概就是的感觉\n数据库公司的古代诗歌鉴赏大概就是的感觉\n数据库公司的古代诗歌鉴赏大概就是的感觉\n数据库公司的古代诗歌鉴赏大概就是的感觉\n" +
                "数据库公司的古代诗歌鉴赏大概就是的感觉\n数据库公司的古代诗数据库公司的古代诗歌鉴赏大概就是的感觉数据库公司的古代诗歌鉴赏大概就是的感觉数据库公司的古代诗歌鉴赏大概就是的感觉数据库公司的古代诗歌鉴赏大概就是的感觉数据库公司的古代诗歌鉴赏大概就是的感觉数据库公司的古代诗歌鉴赏大概就是的感觉数据库公司的古代诗歌鉴赏大概就是的感觉歌鉴赏大概就是的感觉\n数据库公司的古代诗歌鉴赏大概就是的感觉\n数据库公司的古代诗歌鉴赏大概就是的感觉\n");
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerFirstComponent.builder().appComponent(((AppConfig) getActivity().getApplication())
                .getApplicationComponent()).firstModule(new FirstModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(FirstContract.FirstContractPresenter presenter) {
        mPresenter = (FirstPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        //progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        //progressDialog.hide();
    }

}