package com.ljcs.cxwl.ui.activity.certification.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.certification.CertificationFiveActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFiveContract;
import com.orhanobut.logger.Logger;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: presenter of CertificationFiveActivity
 * @date 2018/06/26 19:29:05
 */
public class CertificationFivePresenter implements CertificationFiveContract.CertificationFiveContractPresenter {

    private final CertificationFiveContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private CertificationFiveActivity mActivity;

    @Inject
    public CertificationFivePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CertificationFiveContract.View
            view, CertificationFiveActivity activity) {
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
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void cerInfoLast(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.cerInfoLast(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity user) throws Exception {
                mView.closeProgressDialog();
                mView.cerInfoLastSuccess(user);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.e("onError" + throwable.toString());
                mView.closeProgressDialog();
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