package com.ljcs.cxwl.ui.activity.certification.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.certification.CertificationThirdActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationThirdContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.certification
 * @Description: presenter of CertificationThirdActivity
 * @date 2018/06/26 19:27:38
 */
public class CertificationThirdPresenter implements CertificationThirdContract.CertificationThirdContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CertificationThirdContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CertificationThirdActivity mActivity;

    @Inject
    public CertificationThirdPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CertificationThirdContract.View view, CertificationThirdActivity activity) {
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