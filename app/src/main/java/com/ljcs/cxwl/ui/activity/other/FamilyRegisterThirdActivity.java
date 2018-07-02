package com.ljcs.cxwl.ui.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.adapter.ZinvInfoAdapter;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.entity.CertificationInfo;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterThirdComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterThirdContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterThirdModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterThirdPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: $description
 * @date 2018/06/27 19:46:50
 */

public class FamilyRegisterThirdActivity extends BaseActivity implements FamilyRegisterThirdContract.View {

    @Inject
    FamilyRegisterThirdPresenter mPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ZinvInfoAdapter mAdapter;
    private List<CertificationInfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_third);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("添加家庭成员");


    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mAdapter = new ZinvInfoAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFamilyRegisterThirdComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).familyRegisterThirdModule(new FamilyRegisterThirdModule(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(FamilyRegisterThirdContract.FamilyRegisterThirdContractPresenter presenter) {
        mPresenter = (FamilyRegisterThirdPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.tv_add, R.id.next})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_add:
                intent = new Intent(this, FamilyAddActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.next:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 101) {
            mAdapter.setNewData(Contains.sCertificationInfoList);
        }
    }
}