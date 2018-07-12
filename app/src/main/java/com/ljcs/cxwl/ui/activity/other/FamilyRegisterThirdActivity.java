package com.ljcs.cxwl.ui.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.adapter.ZinvInfoAdapter;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.ui.activity.other.component.DaggerFamilyRegisterThirdComponent;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterThirdContract;
import com.ljcs.cxwl.ui.activity.other.module.FamilyRegisterThirdModule;
import com.ljcs.cxwl.ui.activity.other.presenter.FamilyRegisterThirdPresenter;
import com.vondear.rxtools.RxSPTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;
    @BindView(R.id.next)
    TextView next;
    private ZinvInfoAdapter mAdapter;
    private List<AllInfo.Data.JtcyBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_family_register_third);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("添加子女信息");


    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        mAdapter = new ZinvInfoAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AllInfo.Data.JtcyBean bean = (AllInfo.Data.JtcyBean) adapter.getData().get(position);
                if (view.getId() == R.id.img_change) {
                    Intent intent = new Intent(FamilyRegisterThirdActivity.this, FamilyAddActivity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, 101);
                } else if (view.getId() == R.id.img_upload) {
                    startToImgActivity(FamilyRegisterThirdActivity.this, API.PIC + bean.getHkzp());
                }
            }
        });

        loadData();
    }

    private void loadData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.allInfo(map1);
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
                intent.putExtra("type", 1);
                startActivityForResult(intent, 101);
                break;
            case R.id.next:
                startActivty(FamilyRegisterFourActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 101) {
            loadData();
        }
    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            //购房资格申请
            Contains.sAllInfo = baseEntity;
            if (Contains.sAllInfo.getData().getJtcyList() != null && Contains.sAllInfo.getData().getJtcyList().size()
                    > 0) {
                layoutEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                mAdapter.setNewData(Contains.sAllInfo.getData().getJtcyList());
                next.setText("下一步");
            } else {
                recyclerView.setVisibility(View.GONE);
                layoutEmpty.setVisibility(View.VISIBLE);
                next.setText("跳过");
            }
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }
}