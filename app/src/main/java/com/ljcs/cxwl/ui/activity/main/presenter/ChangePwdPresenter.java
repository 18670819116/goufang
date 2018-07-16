package com.ljcs.cxwl.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.main.ChangePwdActivity;
import com.ljcs.cxwl.ui.activity.main.contract.ChangePwdContract;
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
 * @Description: presenter of ChangePwdActivity
 * @date 2018/07/03 13:37:53
 */
public class ChangePwdPresenter implements ChangePwdContract.ChangePwdContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final ChangePwdContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ChangePwdActivity mActivity;

    @Inject
    public ChangePwdPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ChangePwdContract.View view,
                              ChangePwdActivity activity) {
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
    public void changePwd(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.changePwd(map).subscribe(new Consumer<RegisterBean>() {
            @Override
            public void accept(RegisterBean user) throws Exception {
                //isSuccesse
                mView.closeProgressDialog();
                mView.changePwdSuccess(user);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                throwable.printStackTrace();
                Logger.e(throwable.toString());
                mView.closeProgressDialog();
                //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //onComplete
            }
        });
        mCompositeDisposable.add(disposable);
    }
}