package com.ljcs.cxwl.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.main.RegisterActivity;
import com.ljcs.cxwl.ui.activity.main.contract.RegisterContract;
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
 * @Package com.example.ai.ui.activity.main
 * @Description: presenter of RegisterActivity
 * @date 2018/06/26 14:58:24
 */
public class RegisterPresenter implements RegisterContract.RegisterContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final RegisterContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private RegisterActivity mActivity;

    @Inject
    public RegisterPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RegisterContract.View view,
                             RegisterActivity activity) {
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
    public void getCode(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("yhsjhm", phone);
        Disposable disposable = httpAPIWrapper.getRegisterCode(map).subscribe(new Consumer<CommonBean>() {
            @Override
            public void accept(CommonBean user) throws Exception {
                Logger.i(user.toString());
                mView.getCode(user);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                throwable.printStackTrace();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //onComplete
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void register(Map map) {
        Disposable disposable = httpAPIWrapper.register(map).subscribe(new Consumer<RegisterBean>() {
            @Override
            public void accept(RegisterBean user) throws Exception {
                mView.register(user);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                throwable.printStackTrace();
                //mView.closeProgressDialog();
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