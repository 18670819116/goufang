package com.ljcs.cxwl.ui.activity.other.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.ScanBean;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterStatusActivity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterStatusContract;
import com.vondear.rxtool.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: presenter of FamilyRegisterStatusActivity
 * @date 2018/07/05 10:00:42
 */
public class FamilyRegisterStatusPresenter implements FamilyRegisterStatusContract
        .FamilyRegisterStatusContractPresenter {

    private final FamilyRegisterStatusContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private FamilyRegisterStatusActivity mActivity;

    @Inject
    public FamilyRegisterStatusPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull
            FamilyRegisterStatusContract.View view, FamilyRegisterStatusActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = activity;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void allInfo(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.allInfo(map).subscribe(new Consumer<AllInfo>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull AllInfo appLogin) throws Exception {
                mView.allInfoSuccess(appLogin);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
                mView.intiViews();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void isScan() {
        mView.showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("token", RxSPTool.getString(mActivity, ShareStatic.APP_LOGIN_TOKEN));
        Disposable disposable = httpAPIWrapper.isScan(map).subscribe(new Consumer<ScanBean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull ScanBean appLogin) throws Exception {
                mView.isScanSuccess(appLogin);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

//    @Override
//    public void getUser(HashMap map) {
//        //mView.showProgressDialog();
//        Disposable disposable = httpAPIWrapper.getUser(map)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                      //mView.closeProgressDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                        throwable.printStackTrace();
//                      //mView.closeProgressDialog();
//                      //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //onComplete
//                        KLog.i("onComplete");
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }
}