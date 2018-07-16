package com.ljcs.cxwl.ui.activity.changephone.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneTwoActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneTwoContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: presenter of ChangePhoneTwoActivity
 * @date 2018/07/09 16:39:17
 */
public class ChangePhoneTwoPresenter implements ChangePhoneTwoContract.ChangePhoneTwoContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final ChangePhoneTwoContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ChangePhoneTwoActivity mActivity;

    @Inject
    public ChangePhoneTwoPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ChangePhoneTwoContract.View view, ChangePhoneTwoActivity activity) {
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