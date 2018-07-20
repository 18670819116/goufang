package com.ljcs.cxwl.ui.activity.changephone.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneOneActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneOneContract;
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
 * @Package com.ljcs.cxwl.ui.activity.changephone
 * @Description: presenter of ChangePhoneOneActivity
 * @date 2018/07/09 16:38:08
 */
public class ChangePhoneOnePresenter implements ChangePhoneOneContract.ChangePhoneOneContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final ChangePhoneOneContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ChangePhoneOneActivity mActivity;

    @Inject
    public ChangePhoneOnePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ChangePhoneOneContract.View view,
                                   ChangePhoneOneActivity activity) {
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