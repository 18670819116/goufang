package com.ljcs.cxwl.ui.activity.certification.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.certification.CertificationFourActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationFourContract;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: presenter of CertificationFourActivity
 * @date 2018/06/26 19:28:19
 */
public class CertificationFourPresenter implements CertificationFourContract.CertificationFourContractPresenter {

    private final CertificationFourContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private CertificationFourActivity mActivity;

    @Inject
    public CertificationFourPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CertificationFourContract.View
            view, CertificationFourActivity activity) {
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
    public void getQiniuToken() {
        Map<String, String> map = new HashMap<>();
        Disposable disposable = httpAPIWrapper.getQiniuToken(map).subscribe(new Consumer<QiniuToken>() {
            @Override
            public void accept(QiniuToken user) throws Exception {
                mView.getQiniuTokenSuccess(user);
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

    @Override
    public void postInfo(Map map) {
        Disposable disposable = httpAPIWrapper.cerInfo(map).subscribe(new Consumer<CerInfo>() {
            @Override
            public void accept(CerInfo user) throws Exception {
                mView.closeProgressDialog();
                mView.postInfoSuccess(user);
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

    @Override
    public void allInfo(Map map) {
        Disposable disposable = httpAPIWrapper.allInfo(map).subscribe(new Consumer<AllInfo>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull AllInfo appLogin) throws Exception {
                mView.closeProgressDialog();
                mView.allInfoSuccess(appLogin);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
            }
        }, new io.reactivex.functions.Action() {
            @Override
            public void run() throws Exception {
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