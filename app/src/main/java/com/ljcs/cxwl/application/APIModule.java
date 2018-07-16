package com.ljcs.cxwl.application;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.data.api.HttpApi;
import com.ljcs.cxwl.data.api.support.ErrorHandlerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author hu
 * @desc 功能描述
 * @date 2017/5/31 10:04
 */
@Module
public final class APIModule {

    private final Application application;

    public APIModule(Application application) {
        this.application = application;
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
       // builder.addInterceptor(logging);
        builder.addInterceptor(new ErrorHandlerInterceptor());
        // }
        builder.connectTimeout(API.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).readTimeout(API.IO_TIMEOUT, TimeUnit
                .MILLISECONDS);//.addInterceptor(new RequestBodyInterceptor());
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
//        if (SpUtil.getBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true)) {
//            //线上环境
        builder.client(okHttpClient).baseUrl(API.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());


//        } else {
//            //开发环境
//            builder.client(okHttpClient)
//                    .baseUrl(API.BASE_URL_DEVOLOP)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create());
//        }
        return builder.build();
    }

    @Provides
    @Singleton
    public HttpApi provideHttpAPI(Retrofit restAdapter) {
        return restAdapter.create(HttpApi.class);
    }

    //这里是对外输出部分
    @Provides
    @Singleton
    @Remote
    public HttpAPIWrapper provideHttpAPIWrapper(HttpApi httpAPI) {
        return new HttpAPIWrapper(httpAPI);
    }
}
