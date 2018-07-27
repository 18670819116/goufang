package com.ljcs.cxwl.ui.activity.changephone.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.changephone.ChangePhoneTwoActivity;
import com.ljcs.cxwl.ui.activity.changephone.contract.ChangePhoneTwoContract;
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
 * @Description: presenter of ChangePhoneTwoActivity
 * @date 2018/07/09 16:39:17
 */
public class ChangePhoneTwoPresenter implements ChangePhoneTwoContract.ChangePhoneTwoContractPresenter {

    private final ChangePhoneTwoContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private ChangePhoneTwoActivity mActivity;

    @Inject
    public ChangePhoneTwoPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ChangePhoneTwoContract.View view,
                                   ChangePhoneTwoActivity activity) {
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
    public void getChangeCode(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("yhsjhm", phone);
        Disposable disposable = httpAPIWrapper.getChangeCode(map).subscribe(new Consumer<CommonBean>() {
            @Override
            public void accept(CommonBean user) throws Exception {
                Logger.i(user.toString());
                mView.getChangeCodeSuccess(user);
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
    public void changePhone(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.changePhone(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity user) throws Exception {
                Logger.i(user.toString());
                mView.closeProgressDialog();
                mView.changePhoneSuccess(user);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                mView.closeProgressDialog();
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