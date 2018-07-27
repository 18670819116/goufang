package com.ljcs.cxwl.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.main.MainActivity;
import com.ljcs.cxwl.ui.activity.main.contract.MainContract;
import com.orhanobut.logger.Logger;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: presenter of MainActivity
 * @date 2018/07/02 16:17:40
 */
public class MainPresenter implements MainContract.MainContractPresenter {

    private final MainContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private MainActivity mActivity;

    @Inject
    public MainPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MainContract.View view, MainActivity
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

    @Override
    public void allInfo(Map map) {
        Disposable disposable = httpAPIWrapper.allInfo(map).subscribe(new Consumer<AllInfo>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull AllInfo appLogin) throws Exception {
                mView.allInfoSuccess(appLogin);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void cerInfoDetail(Map map) {
        Disposable disposable = httpAPIWrapper.cerInfoDetail(map).subscribe(new Consumer<CerInfo>() {
            @Override
            public void accept(CerInfo user) throws Exception {
                mView.cerInfoDetailSuccess(user);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.e("onError" + throwable.toString());
                throwable.printStackTrace();
            }
        });
        mCompositeDisposable.add(disposable);
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