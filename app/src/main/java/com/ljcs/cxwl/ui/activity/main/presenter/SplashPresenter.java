package com.ljcs.cxwl.ui.activity.main.presenter;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.main.contract.SplashContract;
import com.ljcs.cxwl.ui.activity.main.SplashActivity;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.util.UpdateManager;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: presenter of SplashActivity
 * @date 2018/06/26 08:44:55
 */
public class SplashPresenter implements SplashContract.SplashContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final SplashContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private SplashActivity mActivity;
    private boolean timeOver = false;
    private static int permissionState = -1;    //-1表示原始状态,0表示允许,1表示拒绝.

    private static final int JUMPTOMAIN = 0;
    private static final int JUMPTOLOGIN = 1;
    private static final int JUMPTOGUEST = 2;    //跳到欢迎页面
    private static final int HASPUDATE = 3;     //表示是否有更新
    private int jump = JUMPTOLOGIN;

    @Inject
    public SplashPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull SplashContract.View view, SplashActivity
            activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = activity;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void getPermission() {
        AndPermission.with(mActivity).requestCode(101).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).rationale(new RationaleListener() {
            @Override
            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                AndPermission.rationaleDialog(mActivity, rationale).setNegativeButton("关闭", new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.show(mActivity, "权限申请失败,app部分功能将无法使用!!!");
                        if (jump == JUMPTOMAIN) {
                            mView.loginSuccees();
                        } else if (jump == JUMPTOLOGIN) {
                            mView.jumpToLogin();
                        } else {
                            mView.jumpToGuest();
                        }
                    }
                }).show();
            }
        }).callback(permission).start();

    }

    private PermissionListener permission = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 101) {
                permissionState = 0;
                if (timeOver) {
                    if (jump == JUMPTOMAIN) {
                        mView.loginSuccees();
                    } else if (jump == JUMPTOLOGIN) {
                        mView.jumpToLogin();
                    } else {
                        mView.jumpToGuest();
                    }
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 101) {
                Logger.i("权限申请失败");
                permissionState = 1;
                ToastUtil.show(mActivity, "权限申请失败,app部分功能将无法使用!!!");
                if (jump == JUMPTOMAIN) {
                    mView.loginSuccees();
                } else if (jump == JUMPTOLOGIN) {
                    mView.jumpToLogin();
                } else {
                    mView.jumpToGuest();
                }
            }
        }
    };

    /**
     * 控制更新版本弹框
     */
    private void alertUpdate() {
        // 这里来检测版本是否需要更新
        UpdateManager mUpdateManager = new UpdateManager(mActivity, "");
        mUpdateManager.checkUpdateInfo("", "", 22);
        mUpdateManager.setOnYiHouOnClickListener(new UpdateManager.OnYiHouOnClickListener() {
            @Override
            public void onYihouClick() {
                if (jump == JUMPTOMAIN) {
                    mView.loginSuccees();
                } else if (jump == JUMPTOLOGIN) {
                    mView.jumpToLogin();
                } else {
                    mView.jumpToGuest();
                }
            }
        });
    }

    public void jump() {
        timeOver = true;
        if (permissionState != 0) {
            return;
        }

        if (jump == JUMPTOMAIN) {
            mView.loginSuccees();
        } else if (jump == JUMPTOLOGIN) {
            mView.jumpToLogin();
        } else {
            mView.jumpToGuest();
        }
    }
}