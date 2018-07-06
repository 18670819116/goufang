package com.ljcs.cxwl.ui.activity.other.presenter;
import android.support.annotation.NonNull;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.other.contract.FamilyRegisterTwoContract;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterTwoActivity;
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
 * @Package com.ljcs.cxwl.ui.activity.other
 * @Description: presenter of FamilyRegisterTwoActivity
 * @date 2018/06/27 19:05:45
 */
public class FamilyRegisterTwoPresenter implements FamilyRegisterTwoContract.FamilyRegisterTwoContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FamilyRegisterTwoContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FamilyRegisterTwoActivity mActivity;

    @Inject
    public FamilyRegisterTwoPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FamilyRegisterTwoContract.View view, FamilyRegisterTwoActivity activity) {
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