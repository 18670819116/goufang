package com.ljcs.cxwl.ui.activity.other.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyAddContract;
import com.ljcs.cxwl.ui.activity.other.FamilyAddActivity;
import com.ljcs.cxwl.util.UploadUtil;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: presenter of FamilyAddActivity
 * @date 2018/07/02 10:34:10
 */
public class FamilyAddPresenter implements FamilyAddContract.FamilyAddContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final FamilyAddContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FamilyAddActivity mActivity;

    @Inject
    public FamilyAddPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FamilyAddContract.View view,
                              FamilyAddActivity activity) {
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
    public void uploadPic(List<String> list, UploadFileCallBack callBack) {
        UploadUtil.uploadPicsOkhttp(httpAPIWrapper,list,callBack);
    }
    @Override
    public void matesInfoZinv(Map map) {
        Disposable disposable = httpAPIWrapper.matesInfoZinv(map).subscribe(new Consumer<MatesInfo>() {
            @Override
            public void accept(MatesInfo user) throws Exception {
                mView.matesInfoZinvSuccess(user);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.e("onError" + throwable.toString());
                throwable.printStackTrace();
                mView.closeProgressDialog();
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void matesInfoDelete(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.matesInfoDelete(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity user) throws Exception {
                mView.matesInfoDeleteSuccess(user);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.e("onError" + throwable.toString());
                throwable.printStackTrace();
                mView.closeProgressDialog();
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