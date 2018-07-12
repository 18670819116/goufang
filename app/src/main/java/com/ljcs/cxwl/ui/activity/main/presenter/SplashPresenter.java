package com.ljcs.cxwl.ui.activity.main.presenter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.AppInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.main.contract.SplashContract;
import com.ljcs.cxwl.ui.activity.main.SplashActivity;
import com.ljcs.cxwl.util.ToastUtil;
import com.ljcs.cxwl.util.UpdateManager;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxDeviceTool;
import com.vondear.rxtools.RxEncryptTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.RxTool;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
        UpdateManager mUpdateManager = new UpdateManager(mActivity, mVersion.getData().getVersionDownloadUrl());
        mUpdateManager.checkUpdateInfo(mVersion.getData().getVersionUid(), mVersion.getData().getVersionExplain(),
                mVersion.getData().getVersionIsCompulsory());
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
        Observable.interval(0, 1, TimeUnit.SECONDS).take(2).map(new Function<Long, Long>() {
            @Override
            public Long apply(@NonNull Long aLong) throws Exception {
                return 2 - aLong;
            }
        }).observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Logger.i("1");
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.i("2");
            }

            @Override
            public void onNext(@NonNull Long remainTime) {
                Logger.i("剩余时间" + remainTime);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.i("4");
            }

            @Override
            public void onComplete() {
                timeOver = true;
                Logger.i("时间到，开始跳转");
                if (permissionState != 0) {
                    return;
                }
                if (hasUpdate) {
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
        });
//        timeOver = true;
//        if (permissionState != 0) {
//            return;
//        }
//
//        if (jump == JUMPTOMAIN) {
//            mView.loginSuccees();
//        } else if (jump == JUMPTOLOGIN) {
//            mView.jumpToLogin();
//        } else {
//            mView.jumpToGuest();
//        }
    }

    @Override
    public void queryShipperInfo() {
//        if (isUpdate()) {
//            return;
//        }
        String savePsd = RxSPTool.getString(mActivity, ShareStatic.APP_LOGIN_MM);
        if (RxDataTool.isNullString(savePsd)) {
            jump = JUMPTOLOGIN;
        } else {

            //读取手机信息
//            AndPermission.with(mActivity).requestCode(100).permission(Manifest.permission.READ_PHONE_STATE).callback
//                   (permissionListener).start();
            Map<String, String> map = new HashMap<>();
            map.put("sjhm", RxSPTool.getString(mActivity, ShareStatic.APP_LOGIN_SJHM));
            map.put("mm", RxEncryptTool.encryptSHA1ToString(RxSPTool.getString(mActivity, ShareStatic.APP_LOGIN_MM)+ RxSPTool.getString(mActivity, ShareStatic.APP_LOGIN_SJHM)));
            login(map);

        }
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 100) {
//                Map<String, String> map = new HashMap<>();
//                map.put("sjhm", mAccount);
//                map.put("mm", mPassWord);
//                mPresenter.login(map);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。

        }
    };

    @Override
    public void login(Map map) {
        Disposable disposable = httpAPIWrapper.Login(map).subscribe(new Consumer<RegisterBean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull RegisterBean appLogin) throws Exception {
                Logger.i(appLogin.toString());
                if (jump == HASPUDATE) {
                    return;
                }
                if (appLogin.code != Contains.REQUEST_SUCCESS) {
                    jump = JUMPTOLOGIN;
                    return;
                }
                RxSPTool.putString(mActivity, ShareStatic.APP_LOGIN_TOKEN, appLogin.token);
                jump = JUMPTOMAIN;

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mView.closeProgressDialog();
            }
        });
        mCompositeDisposable.add(disposable);

    }

    AppInfo mVersion;
    private boolean getLastVersionBack = false;
    private boolean hasUpdate = false;

    @Override
    public void updataApp() {
        Map<String, String> map = new HashMap<>();
        Disposable disposable = httpAPIWrapper.updataApp(map).subscribe(new Consumer<AppInfo>() {
            @Override
            public void accept(AppInfo version) throws Exception {
                //isSuccesse
                Logger.i("onSuccesse");
                if (version.code == Contains.REQUEST_SUCCESS) {
                    mVersion = version;
                    if (Float.valueOf(version.getData().getVersionUid().replace(".", "")) > Float.valueOf
                            (RxDeviceTool.getAppVersionName(mActivity).replace(".", ""))) {
                        getLastVersionBack = true;
                        hasUpdate = true;
                        getUpdatePermission();
                    } else {

                    }
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                Logger.i("onError");
                throwable.printStackTrace();
//                        ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //onComplete
                Logger.i("onComplete");
            }
        });
        mCompositeDisposable.add(disposable);
    }


    public void getUpdatePermission() {
        AndPermission.with(mActivity).requestCode(124).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .rationale((requestCode, rationale) -> {
//                            AndPermission.rationaleDialog(mActivity, rationale).show();
//                        }
//                )
                .callback(updateListener).start();
    }

    private PermissionListener updateListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 124) {
                alertUpdate();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 124) {
                // TODO ...
            }
        }
    };
}