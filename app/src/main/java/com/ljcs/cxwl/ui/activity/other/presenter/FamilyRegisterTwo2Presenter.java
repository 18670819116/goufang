package com.ljcs.cxwl.ui.activity.other.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwo2Activity;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwo2Contract;
import com.ljcs.cxwl.util.UploadUtil;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: presenter of FamilyRegisterTwo2Activity
 * @date 2018/07/10 10:43:02
 */
public class FamilyRegisterTwo2Presenter implements FamilyRegisterTwo2Contract.FamilyRegisterTwo2ContractPresenter {

    private final FamilyRegisterTwo2Contract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private FamilyRegisterTwo2Activity mActivity;

    @Inject
    public FamilyRegisterTwo2Presenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FamilyRegisterTwo2Contract
            .View view, FamilyRegisterTwo2Activity activity) {
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
    public void matesInfo(Map map) {
//        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.matesInfo(map).subscribe(new Consumer<MatesInfo>() {
            @Override
            public void accept(MatesInfo user) throws Exception {
                mView.closeProgressDialog();
                mView.matesInfoSuccess(user);
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

    @Override
    public void uploadPic(List<String> list, UploadFileCallBack callBack) {
        UploadUtil.uploadPicsOkhttp(httpAPIWrapper, list, callBack);
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