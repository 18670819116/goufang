package com.ljcs.cxwl.data.api;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.HujiInfo;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.util.MD5Util;
import com.ljcs.cxwl.util.RSAUtil;
import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hu
 * @desc 对Request实体(不执行)在执行时所调度的线程，以及得到ResponseBody后根据retCode对Result进行进一步处理
 * @date 2017/5/31 16:56
 */
public class HttpAPIWrapper {

    private HttpApi mHttpAPI;

    @Inject
    public HttpAPIWrapper(HttpApi mHttpAPI) {
        this.mHttpAPI = mHttpAPI;
    }

    //登陆
    public Observable<RegisterBean> Login(Map data) {
        return wrapper(mHttpAPI.Login(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //验证码
    public Observable<CommonBean> getCode(Map data) {
        return wrapper(mHttpAPI.getCode(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //注册
    public Observable<RegisterBean> register(Map data) {
        return wrapper(mHttpAPI.register(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //忘记密码
    public Observable<CommonBean> forgetPwd(Map data) {
        return wrapper(mHttpAPI.forgetPwd(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //退出登录
    public Observable<CommonBean> loginOut(Map data) {
        return wrapper(mHttpAPI.loginOut(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //修改密码
    public Observable<RegisterBean> changePwd(Map data) {
        return wrapper(mHttpAPI.changePwd(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //提交实名认证信息
    public Observable<AllInfo> allInfo(Map data) {
        return wrapper(mHttpAPI.allInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //提交实名认证信息
    public Observable<CerInfo> cerInfo(Map data) {
        return wrapper(mHttpAPI.cerInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //提交实名认证信息最后一步
    public Observable<BaseEntity> cerInfoLast(Map data) {
        return wrapper(mHttpAPI.cerInfoLast(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //获取实名认证详情信息
    public Observable<CerInfo> cerInfoDetail(Map data) {
        return wrapper(mHttpAPI.cerInfoDetail(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //保存/修改配偶信息
    public Observable<MatesInfo> matesInfo(Map data) {
        return wrapper(mHttpAPI.matesInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //保存/修改家庭成员信息
    public Observable<MatesInfo> matesInfoZinv(Map data) {
        return wrapper(mHttpAPI.matesInfoZinv(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //删除家庭成员信息
    public Observable<BaseEntity> matesInfoDelete(Map data) {
        return wrapper(mHttpAPI.matesInfoDelete(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //提交家庭成员信息
    public Observable<BaseEntity> matesInfoCommit(Map data) {
        return wrapper(mHttpAPI.matesInfoCommit(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //保存家庭成员信息
    public Observable<BaseEntity> matesInfoSave(Map data) {
        return wrapper(mHttpAPI.matesInfoSave(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //获取七牛token
    public Observable<QiniuToken> getQiniuToken(Map data) {
        return wrapper(mHttpAPI.getQiniuToken(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //获取七牛token
    public Observable<HujiInfo> hukouInfo(Map data) {
        return wrapper(mHttpAPI.hukouInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    /**
     * 给任何Http的Observable加上通用的线程调度器
     */
    private static final ObservableTransformer SCHEDULERS_TRANSFORMER = new ObservableTransformer() {
        @Override
        public ObservableSource apply(@NonNull Observable upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * 根据Http的response中关于状态码的描述，自定义生成本地的Exception
     *
     * @param resourceObservable
     * @param <T>
     * @return
     */
    private <T extends BaseEntity> Observable<T> wrapper(Observable<T> resourceObservable) {
        return resourceObservable.flatMap(new Function<T, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(@NonNull final T baseResponse) throws Exception {
                return Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {

                        e.onNext(baseResponse);
                    }
                });
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable e) throws Exception {
                e.printStackTrace();
                String errorText = "请求失败";
                if (e instanceof HttpException) {
                    HttpException exception = (HttpException) e;
                } else if (e instanceof UnknownHostException) {
                    Logger.i("请打开网络");
                    errorText = "请打开网络";
                } else if (e instanceof SocketTimeoutException) {
                    Logger.i("请求超时");
                    errorText = "请求超时";
                } else if (e instanceof ConnectException) {
                    Logger.i("连接失败");
                    errorText = "连接失败";
                } else if (e instanceof HttpException) {
                    Logger.i("请求超时");
                    errorText = "请求超时";
                } else {
                    Logger.i("请求失败");
                    errorText = "请求失败";
                }

                ToastUtil.displayShortToast(errorText);
            }
        });
    }

    /**
     * 根据Http的response中关于状态码的描述，自定义生成本地的Exception
     *
     * @param resourceObservable
     * @param <T>
     * @return
     */
    private <T extends Object> Observable<T> wrapperObject(Observable<T> resourceObservable) {
        return resourceObservable.flatMap(new Function<T, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(@NonNull final T baseResponse) throws Exception {
                return Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                        if (baseResponse == null) {

                        } else {
                            //// TODO: 2017/6/8 没有做错误处理，因为现在后台返回的结果格式都不一样，等后台统一了返回再做处理
                            e.onNext(baseResponse);
                        }
                    }
                });
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable e) throws Exception {
                e.printStackTrace();
                String errorText = "";
                if (e instanceof HttpException) {
                    HttpException exception = (HttpException) e;
                } else if (e instanceof UnknownHostException) {
                    Logger.i("请打开网络");
                    errorText = "请打开网络";
                } else if (e instanceof SocketTimeoutException) {
                    Logger.i("请求超时");
                    errorText = "请求超时";
                } else if (e instanceof ConnectException) {
                    Logger.i("连接失败");
                    errorText = "连接失败";
                } else if (e instanceof HttpException) {
                    Logger.i("请求超时");
                    errorText = "请求超时";
                } else {
                    Logger.i("请求失败");
                    errorText = "请求失败";
                }
                ToastUtil.displayShortToast(errorText);
            }
        });
    }

    /**
     * 给任何Http的Observable加上在Service中运行的线程调度器
     */
    public static <T> ObservableTransformer<T, T> getSchedulerstransFormerToService() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //需要额外的添加其他的参数进去，所以把原有的参数和额外的参数通过这个方法一起添加进去.
    public static Map addParams(Map<String, String> data) {
        //添加统一的参数的地方
        return data;
    }

    //需要额外的添加其他的参数进去，所以把原有的参数和额外的参数通过这个方法一起添加进去.
    public static String addParams2String(Map<String, String> data) {
        //添加统一的参数的地方
        JsonObject js = new JsonObject();
        LinkedHashMap<String, String> stampData = (LinkedHashMap<String, String>) MD5Util.getStamp();
        Iterator<Map.Entry<String, String>> itStamp = stampData.entrySet().iterator();
        while (itStamp.hasNext()) {
            Map.Entry<String, String> entry = itStamp.next();
            js.addProperty(entry.getKey(), entry.getValue());
        }
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            js.addProperty(entry.getKey(), entry.getValue());
        }
        String temp = js.toString();
        byte[] encryptBytes = new byte[0];
        try {
            encryptBytes = RSAUtil.encryptByPublicKey(temp.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encryStr = new String(encryptBytes);
        return encryStr;
    }

}
