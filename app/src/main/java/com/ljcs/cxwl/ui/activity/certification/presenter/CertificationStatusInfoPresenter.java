package com.ljcs.cxwl.ui.activity.certification.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationStatusInfoContract;
import com.ljcs.cxwl.ui.activity.certification.CertificationStatusInfoActivity;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: presenter of CertificationStatusInfoActivity
 * @date 2018/07/03 21:25:58
 */
public class CertificationStatusInfoPresenter implements CertificationStatusInfoContract
        .CertificationStatusInfoContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final CertificationStatusInfoContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CertificationStatusInfoActivity mActivity;

    @Inject
    public CertificationStatusInfoPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull
            CertificationStatusInfoContract.View view, CertificationStatusInfoActivity activity) {
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
    public void cerInfoDetail(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.cerInfoDetail(map).subscribe(new Consumer<CerInfo>() {
            @Override
            public void accept(CerInfo user) throws Exception {
                mView.cerInfoDetailSuccess(user);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.e("onError" + throwable.toString());
                throwable.printStackTrace();
                mView.closeProgressDialog();
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