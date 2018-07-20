package com.ljcs.cxwl.ui.activity.scan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.certification.AboutCertificationActivity;
import com.ljcs.cxwl.ui.activity.scan.component.DaggerScanComponent;
import com.ljcs.cxwl.ui.activity.scan.contract.ScanContract;
import com.ljcs.cxwl.ui.activity.scan.module.ScanModule;
import com.ljcs.cxwl.ui.activity.scan.presenter.ScanPresenter;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.view.SureDialog;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vondear.rxtool.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.scan
 * @Description: $description
 * @date 2018/07/13 11:32:30
 */

public class ScanActivity extends BaseActivity implements ScanContract.View {

    @Inject
    ScanPresenter mPresenter;
    @BindView(R.id.iv_shoudiantong)
    ImageView mIvShoudiantong;
    private CaptureFragment captureFragment;
    private int REQUEST_IMAGE = 160;//相册
    private boolean isLight;
    private SureDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("扫一扫");
        autolayout.setBackgroundColor(getResources().getColor(R.color.main_color));
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initData() {

    }

    /**
     * 手电筒开关点击事件
     */
    @OnClick(R.id.iv_shoudiantong)
    public void onViewClicked() {
        isLight = !isLight;
        if (isLight) {
            CodeUtils.isLightEnable(true);//开闪光灯
            mIvShoudiantong.setImageResource(R.mipmap.ic_shoudiantong_on);

        } else {
            CodeUtils.isLightEnable(false);//关闪光灯
            mIvShoudiantong.setImageResource(R.mipmap.ic_shoudiantong_off);
        }
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Logger.i("result" + result);
            if (dialog == null || !dialog.isShowing()) {
                showDialog(result);
            }

            //开启重新扫码
            Message obtain = Message.obtain();
            obtain.what = R.id.restart_preview;
            captureFragment.getHandler().sendMessageDelayed(obtain, 1500);

        }

        @Override
        public void onAnalyzeFailed() {
            Logger.e("解析失败");
            ToastUtil.showCenterShort("解析二维码失败");
        }
    };

    private void showDialog(final String result) {
        dialog = new SureDialog(this);
        dialog.getContentView().setText("正在读取您的购房资格审查数据，\n是否同意");
        dialog.getCancelView().setText("不同意");
        dialog.getCancelView().setTextColor(getResources().getColor(R.color.color_333333));
        dialog.getSureView().setText("同意");
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();

            }
        });
        dialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", RxSPTool.getString(ScanActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                map.put("sfzhm", Contains.sAllInfo.getData().getSmyz().getSfzhm());
                map.put("smnr", result);
                mPresenter.scan(map);
            }
        });
        dialog.show();
    }


    @Override
    protected void setupActivityComponent() {
        DaggerScanComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .scanModule(new ScanModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ScanContract.ScanContractPresenter presenter) {
        mPresenter = (ScanPresenter) presenter;
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
    public void scanSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort("数据提交成功");
            finish();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isLight) {
            CodeUtils.isLightEnable(false);//关闪光灯
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}