package com.ljcs.cxwl.ui.activity.main.presenter;
import android.support.annotation.NonNull;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.ui.activity.main.contract.FirstContract;
import com.ljcs.cxwl.ui.activity.main.FirstFragment;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: presenter of FirstFragment
 * @date 2017/10/18 11:56:00
 */
public class FirstPresenter implements FirstContract.FirstContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FirstContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FirstFragment mFragment;

    @Inject
    public FirstPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FirstContract.View view, FirstFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
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
//                      //ToastUtil.show(mFragment.getActivity(), mFragment.getString(R.string.loading_failed_1));
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