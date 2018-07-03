package com.ljcs.cxwl.data.api;


import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.ljcs.cxwl.data.api.API.URL_POST_CHANGEPWD;
import static com.ljcs.cxwl.data.api.API.URL_POST_FORGETPWD;
import static com.ljcs.cxwl.data.api.API.URL_POST_GET_CODE;
import static com.ljcs.cxwl.data.api.API.URL_POST_LOGIN;
import static com.ljcs.cxwl.data.api.API.URL_POST_LOGINOUT;
import static com.ljcs.cxwl.data.api.API.URL_POST_REGISTER;


/**
 * Created by hu on 2017/5/16.
 */

public interface HttpApi {
    //主机列表

    @POST(URL_POST_LOGIN)
    @FormUrlEncoded
    Observable<RegisterBean> Login(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_GET_CODE)
    @FormUrlEncoded
    Observable<CommonBean> getCode(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_REGISTER)
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_FORGETPWD)
    @FormUrlEncoded
    Observable<CommonBean> forgetPwd(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_LOGINOUT)
    @FormUrlEncoded
    Observable<BaseEntity> loginOut(@FieldMap Map<String, RequestBody> params);
    @POST(URL_POST_CHANGEPWD)
    @FormUrlEncoded
    Observable<RegisterBean> changePwd(@FieldMap Map<String, RequestBody> params);
}
