package com.ljcs.cxwl.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.main.AboutOurActivity;
import com.ljcs.cxwl.ui.activity.main.contract.AboutOurContract;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxSPTool;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: presenter of AboutOurActivity
 * @date 2018/07/09 11:07:19
 */
public class AboutOurPresenter implements AboutOurContract.AboutOurContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final AboutOurContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AboutOurActivity mActivity;

    @Inject
    public AboutOurPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AboutOurContract.View view, AboutOurActivity activity) {
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
    public void aboutMe() {
        Map<String, String> map = new HashMap<>();
        map.put("token", RxSPTool.getString(mActivity, ShareStatic.APP_LOGIN_TOKEN));
        Disposable disposable = httpAPIWrapper.aboutMe(map).subscribe(new Consumer<CommonBean>() {
            @Override
            public void accept(CommonBean user) throws Exception {
                //isSuccesse
                mView.closeProgressDialog();
                mView.aboutMeSuccess(user);
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