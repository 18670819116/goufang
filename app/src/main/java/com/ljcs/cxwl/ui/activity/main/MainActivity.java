package com.ljcs.cxwl.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.certification.AboutCertificationActivity;
import com.ljcs.cxwl.ui.activity.certification.CertificationOneActivity;
import com.ljcs.cxwl.ui.activity.certification.CertificationStatusInfoActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerMainComponent;
import com.ljcs.cxwl.ui.activity.main.contract.MainContract;
import com.ljcs.cxwl.ui.activity.main.module.MainModule;
import com.ljcs.cxwl.ui.activity.main.presenter.MainPresenter;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterStatusActivity;
import com.ljcs.cxwl.ui.activity.other.QualificationExaminationActivity;
import com.ljcs.cxwl.ui.activity.web.WebSatisficingActivity;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.util.UIUtils;
import com.ljcs.cxwl.view.SureDialog;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/02 16:17:40
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter mPresenter;
    @BindView(R.id.relayou_head)
    RelativeLayout relayouHead;
    @BindView(R.id.tv1)
    TextView tv1;
    //    @BindView(R.id.tv2)
//    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        autolayout.setVisibility(View.GONE);
        relayouHead.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> map1 = new HashMap<>();
        map1.put("token", RxSPTool.getString(this, ShareStatic.APP_LOGIN_TOKEN));
        mPresenter.allInfo(map1);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMainComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MainContract.MainContractPresenter presenter) {
        mPresenter = (MainPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void allInfoSuccess(AllInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            //购房资格申请
            Contains.sAllInfo = baseEntity;
            if (Contains.sAllInfo.getData() != null) {
                if (Contains.sAllInfo.getData().getSmyz() == null) {
                    tv1.setText("未进行实名认证");
                } else {
                    if (Contains.sAllInfo.getData().getSmyz().getZt().equals("1")) {
                        tv1.setText("未进行实名认证");
                    }
                    if (Contains.sAllInfo.getData().getSmyz().getZt().equals("2")) {
                        tv1.setText("已提交，待审核");
                    }
                    if (Contains.sAllInfo.getData().getSmyz().getZt().equals("3")) {
                        tv1.setText("已实名认证");
                    }
                    if (Contains.sAllInfo.getData().getSmyz().getZt().equals("4")) {
                        tv1.setText("审核未通过");
                    }
                }
                if (Contains.sAllInfo.getData().getHjxx() == null) {
                    tv3.setText("待申请");
                    // tv2.setText("未添加");
                } else {
                    if (Contains.sAllInfo.getData().getHjxx().getZt().equals("1")) {
                        tv3.setText("待申请");
                        //   tv2.setText("未添加");
                    }
                    if (Contains.sAllInfo.getData().getHjxx().getZt().equals("2")) {
                        tv3.setText("待审核");
                        //  tv2.setText("已保存");
                    }
                    if (Contains.sAllInfo.getData().getHjxx().getZt().equals("3")) {
                        tv3.setText("审核通过");
                        // tv2.setText("已保存");
                    }
                    if (Contains.sAllInfo.getData().getHjxx().getZt().equals("4")) {
                        tv3.setText("审核未通过");
                        //  tv2.setText("已保存");
                    }
                    if (Contains.sAllInfo.getData().getHjxx().getZt().equals("5")) {
                        //   tv2.setText("已添加");
                        tv3.setText("待申请");
                    }

                }
            }
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }
    }

    @OnClick({R.id.img_head, R.id.img_right_menu, R.id.relayout_item1, R.id.relayout_item2, R.id.relayout_item3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                break;
            case R.id.img_right_menu:
                startActivty(PersonCenterActivity.class);
                overridePendingTransition(R.anim.activity_open_top, R.anim.activity_translate_out);
                break;
            case R.id.relayout_item1:
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    return;
                }
                if (Contains.sAllInfo.getData() == null) {
                    return;
                }
                if (Contains.sAllInfo.getData().getSmyz() == null || Contains.sAllInfo.getData().getSmyz().getZt()
                        .equals("1")) {
                    startActivty(AboutCertificationActivity.class);

                } else {
                    startActivty(CertificationStatusInfoActivity.class);

                }
                break;
            case R.id.relayout_item2:
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    return;
                }
                Intent intent = new Intent(this, WebSatisficingActivity.class);
                intent.putExtra("name", "详情");
                intent.putExtra("address", "http://zjt.hunan.gov.cn/");
                startActivity(intent);
                //购房政策
//                if (Contains.sAllInfo.getData() == null) {
//                    return;
//                }
//                //添加家庭成员
//                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null && Contains
//                        .sAllInfo.getData().getSmyz().getZt().equals("3")) {
//                    if (Contains.sAllInfo.getData().getHjxx() == null || Contains.sAllInfo.getData().getHjxx().getZt
//                            ().equals("1")) {
//                        startActivty(FamilyAddNowActivity.class);
//                    } else {
//                        //已提交
////                        ToastUtil.showCenterShort("跳详情");
//                        startActivty(FamilyMatesStatusActivity.class);
//                    }
//
//                } else {
////                    ToastUtil.showCenterShort("请先进行实名认证");
//                    showDialog();
//                }

                break;
            case R.id.relayout_item3:
                if (RxTool.isFastClick(Contains.FAST_CLICK)) {
                    return;
                }
                if (Contains.sAllInfo.getData() == null) {
                    return;
                }
                //实名认证状态1没认证2审核中3通过4未通过
                if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null && Contains
                        .sAllInfo.getData().getSmyz().getZt().equals("3")) {
                    if (Contains.sAllInfo.getData().getHjxx() == null || Contains.sAllInfo.getData().getHjxx().getZt
                            ().equals("1") || Contains.sAllInfo.getData().getHjxx().getZt().equals("5")) {
                        startActivty(QualificationExaminationActivity.class);
                    } else {
                        startActivty(FamilyRegisterStatusActivity.class);

                    }

                } else if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null &&
                        Contains.sAllInfo.getData().getSmyz().getZt().equals("1")) {
                    showDialog();
                } else if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null &&
                        Contains.sAllInfo.getData().getSmyz().getZt().equals("2")) {
                    ToastUtil.showCenterShort("实名认证信息审核中，请耐心等待");
                } else if (Contains.sAllInfo.getData() != null && Contains.sAllInfo.getData().getSmyz() != null &&
                        Contains.sAllInfo.getData().getSmyz().getZt().equals("4")) {
                    ToastUtil.showCenterShort("实名认证未通过审核");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.showCenterShort("再按一次退出GO房");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                AppManager.getInstance().appExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void cerInfoDetailSuccess(CerInfo baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            if (Contains.sAllInfo.getData() == null) {
                return;
            }
            if (Contains.sAllInfo.getData().getSmyz() == null || Contains.sAllInfo.getData().getSmyz().getZt().equals
                    ("1")) {
                startActivty(CertificationOneActivity.class);

            } else {
                startActivty(CertificationStatusInfoActivity.class);

            }
        }

    }

    private void showDialog() {
        final SureDialog dialog = new SureDialog(this);
        dialog.getContentView().setText("您还没有实名认证，请先实名认证");
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Contains.sAllInfo.getData() == null) {
                    return;
                }
                if (Contains.sAllInfo.getData().getSmyz() == null || Contains.sAllInfo.getData().getSmyz().getZt()
                        .equals("1")) {
                    startActivty(AboutCertificationActivity.class);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}