package com.ljcs.cxwl.ui.activity.other.presenter;
import android.support.annotation.NonNull;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyMatesStatusContract;
import com.ljcs.cxwl.ui.activity.other.FamilyMatesStatusActivity;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: presenter of FamilyMatesStatusActivity
 * @date 2018/07/05 13:56:02
 */
public class FamilyMatesStatusPresenter implements FamilyMatesStatusContract.FamilyMatesStatusContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FamilyMatesStatusContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FamilyMatesStatusActivity mActivity;

    @Inject
    public FamilyMatesStatusPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FamilyMatesStatusContract.View view, FamilyMatesStatusActivity activity) {
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
    public void allInfo(Map map) {
        Disposable disposable = httpAPIWrapper.allInfo(map).subscribe(new Consumer<AllInfo>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull AllInfo appLogin) throws Exception {
                mView.allInfoSuccess(appLogin);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
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