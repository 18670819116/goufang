package com.ljcs.cxwl.ui.activity.changephone.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneThirdActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneThirdContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: presenter of ChangePhoneThirdActivity
 * @date 2018/07/09 16:39:43
 */
public class ChangePhoneThirdPresenter implements ChangePhoneThirdContract.ChangePhoneThirdContractPresenter {

    private final ChangePhoneThirdContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private ChangePhoneThirdActivity mActivity;

    @Inject
    public ChangePhoneThirdPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ChangePhoneThirdContract.View
            view, ChangePhoneThirdActivity activity) {
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