package com.ljcs.cxwl.ui.activity.certification.presenter;

import android.support.annotation.NonNull;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.certification.CertificationActivity;
import com.ljcs.cxwl.ui.activity.certification.contract.CertificationContract;
import com.ljcs.cxwl.util.UploadUtil;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.certification
 * @Description: presenter of CertificationActivity
 * @date 2018/07/11 08:32:05
 */
public class CertificationPresenter implements CertificationContract.CertificationContractPresenter {

    private final CertificationContract.View mView;
    HttpAPIWrapper httpAPIWrapper;
    private CompositeDisposable mCompositeDisposable;
    private CertificationActivity mActivity;

    @Inject
    public CertificationPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CertificationContract.View view,
                                  CertificationActivity activity) {
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
//        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath));
//        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", new File(filePath).getName(),
//                fileRequestBody);
//        Disposable disposable = httpAPIWrapper.uploadFile(body).subscribe(new Consumer<CommonBean>() {
//            @Override
//            public void accept(@io.reactivex.annotations.NonNull CommonBean appLogin) throws Exception {
//                mView.uploadPic(type,appLogin);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
//                Logger.e(throwable.toString());
//                mView.closeProgressDialog();
//            }
//        }, new io.reactivex.functions.Action() {
//            @Override
//            public void run() throws Exception {
//            }
//        });
//        mCompositeDisposable.add(disposable);
    }

    @Override
    public void postInfo(Map map) {
        Disposable disposable = httpAPIWrapper.cerInfo(map).subscribe(new Consumer<CerInfo>() {
            @Override
            public void accept(CerInfo user) throws Exception {
                mView.closeProgressDialog();
                mView.postInfoSuccess(user);
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