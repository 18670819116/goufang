package com.ljcs.cxwl.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.API;
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
import com.ljcs.cxwl.util.PhoneUtils;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.util.UIUtils;
import com.ljcs.cxwl.view.SureDialog;
import com.orhanobut.logger.Logger;
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

    private boolean isFirst;//登录进来未实名认证

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
        initALPush();
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
                if (Contains.sAllInfo.getData().getZcyh() == null || Contains.sAllInfo.getData().getZcyh().getRzzt()
                        == null) {
                    tv1.setText("未进行实名认证");
                    if (!isFirst) {
                        showDialog();
                        isFirst = true;
                    }
                } else {
                    if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 0) {
                        tv1.setText("已提交，待审核");
                    } else if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 1) {
                        tv1.setText("已提交，待人工审核");
                    } else if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 2) {
                        tv1.setText("已实名认证");
                    } else if (Contains.sAllInfo.getData().getZcyh().getRzzt() == 3) {
                        tv1.setText("审核未通过");
                    }
                }
                if (Contains.sAllInfo.getData().getGrxx() != null || Contains.sAllInfo.getData().getGrxx().getJtcy()
                        != null || Contains.sAllInfo.getData().getGrxx().getJtcy().getRzzt() != null) {
                    tv3.setText("待申请");
                    // tv2.setText("未添加");
                    if (Contains.sAllInfo.getData().getGrxx().getJtcy().getRzzt()==0) {
                        //审核中
                        tv3.setText("待审核");
                    } else if (Contains.sAllInfo.getData().getGrxx().getJtcy().getRzzt()==1) {
                        //审完成
                        tv3.setText("预审通过");
                    } else if (Contains.sAllInfo.getData().getGrxx().getJtcy().getRzzt()==2) {
                        //未通过
                        tv3.setText("审核未通过");

                    }
                } else {
                    tv3.setText("待申请");

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
                if (Contains.sAllInfo.getData().getZcyh() == null || Contains.sAllInfo.getData().getZcyh().getRzzt()
                        == null) {
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
                intent.putExtra("address", API.URL_ZJW_INDEX);
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
                //实名认证状态0-待审核，1,待人工审核，2-审核通过，3-审核未通过）
                if (Contains.sAllInfo.getData().getZcyh() == null || Contains.sAllInfo.getData().getZcyh().getRzzt()
                        == null) {
                    //没有实名认证
                    showDialog();
                } else if (Contains.sAllInfo.getData().getZcyh() != null && Contains.sAllInfo.getData().getZcyh()
                        .getRzzt() != null && Contains.sAllInfo.getData().getZcyh().getRzzt() == 1) {
                    ToastUtil.showCenterShort("实名认证信息人工审核中，请耐心等待");
                } else if (Contains.sAllInfo.getData().getZcyh() != null && Contains.sAllInfo.getData().getZcyh()
                        .getRzzt() != null && Contains.sAllInfo.getData().getZcyh().getRzzt() == 3) {
                    ToastUtil.showCenterShort("实名认证未通过审核");
                } else if (Contains.sAllInfo.getData().getZcyh() != null && Contains.sAllInfo.getData().getZcyh()
                        .getRzzt() != null && Contains.sAllInfo.getData().getZcyh().getRzzt() == 0 || Contains
                        .sAllInfo.getData().getZcyh().getRzzt() == 2) {
                    //认证通过 认证审核中
                    if (Contains.sAllInfo.getData().getGrxx() != null && Contains.sAllInfo.getData().getGrxx()
                            .getJtcy()!=null&&Contains.sAllInfo.getData().getGrxx()
                            .getJtcy().getRzzt() != null) {
                        startActivty(FamilyRegisterStatusActivity.class);
                    } else {
                        startActivty(QualificationExaminationActivity.class);
                    }
//

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
                ToastUtil.showCenterShort("再按一次退出APP");
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
        dialog.getContentView().setText("您尚未进行实名认证，请先认证");
        dialog.getCancelView().setText("稍后认证");
        dialog.getCancelView().setTextColor(getResources().getColor(R.color.color_333333));
        dialog.getSureView().setText("去认证");
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

    private void initALPush() {
        PushServiceFactory.getCloudPushService().addAlias(PhoneUtils.getDeviceId(this), new CommonCallback() {
            @Override
            public void onSuccess(String s) {

                Logger.i("阿里云绑定别名成功  imei" + PhoneUtils.getDeviceId(MainActivity.this));
            }

            @Override
            public void onFailed(String s, String s1) {
                Logger.e("阿里云绑定别名失败 s" + s + "s1" + s1);

            }
        });
        PushServiceFactory.getCloudPushService().listAliases(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Logger.i("阿里云查询别名成功" + s);
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });
        PushServiceFactory.getCloudPushService().bindAccount(RxSPTool.getString(this, ShareStatic.APP_LOGIN_SJHM),
                new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Logger.i("阿里云绑定账号成功" + RxSPTool.getString(MainActivity.this, ShareStatic.APP_LOGIN_SJHM));
            }

            @Override
            public void onFailed(String s, String s1) {
                Logger.e("阿里云绑定账号失败 s" + s + "s1" + s1);
            }
        });

    }


}