package com.ljcs.cxwl.ui.activity.certification.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationTwoContract;
import com.ljcs.cxwl.ui.activity.certification.CertificationTwoActivity;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import rx.functions.Action;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: presenter of CertificationTwoActivity
 * @date 2018/06/26 19:26:58
 */
public class CertificationTwoPresenter implements CertificationTwoContract.CertificationTwoContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final CertificationTwoContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CertificationTwoActivity mActivity;

    @Inject
    public CertificationTwoPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CertificationTwoContract.View
            view, CertificationTwoActivity activity) {
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


}