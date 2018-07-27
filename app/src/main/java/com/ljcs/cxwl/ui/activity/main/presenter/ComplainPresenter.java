package com.ljcs.cxwl.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.main.ComplainActivity;
import com.ljcs.cxwl.ui.activity.main.contract.ComplainContract;
import com.ljcs.cxwl.util.UploadUtil;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: presenter of ComplainActivity
 * @date 2018/07/09 10:58:14
 */
public class ComplainPresenter implements ComplainContract.ComplainContractPresenter {

    private final ComplainContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private ComplainActivity mActivity;

    @Inject
    public ComplainPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ComplainContract.View view,
                             ComplainActivity activity) {
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
    public void uploadPic(List<String> list, UploadFileCallBack callBack) {
        UploadUtil.uploadPicsOkhttp(httpAPIWrapper, list, callBack);
    }

    @Override
    public void commitShSuggest(Map map) {
        Disposable disposable = httpAPIWrapper.commitShSuggest(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity user) throws Exception {
                mView.closeProgressDialog();
                mView.commitShSuggestSuccess(user);
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