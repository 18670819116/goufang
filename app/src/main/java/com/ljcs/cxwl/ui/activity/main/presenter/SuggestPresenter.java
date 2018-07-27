package com.ljcs.cxwl.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.main.SuggestActivity;
import com.ljcs.cxwl.ui.activity.main.contract.SuggestContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: presenter of SuggestActivity
 * @date 2018/07/06 15:46:33
 */
public class SuggestPresenter implements SuggestContract.SuggestContractPresenter {

    private final SuggestContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private SuggestActivity mActivity;

    @Inject
    public SuggestPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull SuggestContract.View view,
                            SuggestActivity activity) {
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
    public void commitSuggest(Map map) {
        Disposable disposable = httpAPIWrapper.commitSuggest(map).subscribe(new Consumer<CommonBean>() {
            @Override
            public void accept(CommonBean user) throws Exception {
                mView.commitSuggestSuccess(user);
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