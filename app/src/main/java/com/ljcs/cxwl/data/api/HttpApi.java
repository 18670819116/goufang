package com.ljcs.cxwl.data.api;


import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.AppInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.HujiInfo;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.entity.RegisterBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

import static com.ljcs.cxwl.data.api.API.URL_GET_CERINFO_DETAIL;
import static com.ljcs.cxwl.data.api.API.URL_GET_QINIU_TOKEN;
import static com.ljcs.cxwl.data.api.API.URL_GET_UPDATA_VERSION;
import static com.ljcs.cxwl.data.api.API.URL_GET_UPLOADFILE;
import static com.ljcs.cxwl.data.api.API.URL_POST_ALL_INFO;
import static com.ljcs.cxwl.data.api.API.URL_POST_CERINFO;
import static com.ljcs.cxwl.data.api.API.URL_POST_CERINFO_LAST;
import static com.ljcs.cxwl.data.api.API.URL_POST_CHANGEPWD;
import static com.ljcs.cxwl.data.api.API.URL_POST_FORGETPWD;
import static com.ljcs.cxwl.data.api.API.URL_POST_GET_CODE;
import static com.ljcs.cxwl.data.api.API.URL_POST_GET_REGISTER_CODE;
import static com.ljcs.cxwl.data.api.API.URL_POST_HUKOU;
import static com.ljcs.cxwl.data.api.API.URL_POST_LOGIN;
import static com.ljcs.cxwl.data.api.API.URL_POST_LOGINOUT;
import static com.ljcs.cxwl.data.api.API.URL_POST_MATESINFO;
import static com.ljcs.cxwl.data.api.API.URL_POST_MATESINFO_COMMIT;
import static com.ljcs.cxwl.data.api.API.URL_POST_MATESINFO_DELETE;
import static com.ljcs.cxwl.data.api.API.URL_POST_MATESINFO_SAVE;
import static com.ljcs.cxwl.data.api.API.URL_POST_MATESINFO_ZINV;
import static com.ljcs.cxwl.data.api.API.URL_POST_REGISTER;


/**
 * Created by hu on 2017/5/16.
 */

public interface HttpApi {

    @POST(URL_POST_LOGIN)
    @FormUrlEncoded
    Observable<RegisterBean> Login(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_GET_CODE)
    @FormUrlEncoded
    Observable<CommonBean> getCode(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_GET_REGISTER_CODE)
    @FormUrlEncoded
    Observable<CommonBean> getRegisterCode(@FieldMap Map<String, RequestBody> params);

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
    @GET(URL_GET_UPDATA_VERSION)
    Observable<AppInfo> updataApp(@QueryMap Map<String, RequestBody> params);
    @POST(URL_POST_ALL_INFO)
    @FormUrlEncoded
    Observable<AllInfo> allInfo(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_CERINFO)
    @FormUrlEncoded
    Observable<CerInfo> cerInfo(@FieldMap Map<String, RequestBody> params);

    @GET(URL_POST_CERINFO_LAST)
    Observable<BaseEntity> cerInfoLast(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_CERINFO_DETAIL)
    Observable<CerInfo> cerInfoDetail(@QueryMap Map<String, RequestBody> params);

    @POST(URL_POST_MATESINFO)
    @FormUrlEncoded
    Observable<MatesInfo> matesInfo(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_MATESINFO_ZINV)
    @FormUrlEncoded
    Observable<MatesInfo> matesInfoZinv(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_MATESINFO_DELETE)
    @FormUrlEncoded
    Observable<BaseEntity> matesInfoDelete(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_MATESINFO_COMMIT)
    @FormUrlEncoded
    Observable<BaseEntity> matesInfoCommit(@FieldMap Map<String, RequestBody> params);

    @POST(URL_POST_MATESINFO_SAVE)
    @FormUrlEncoded
    Observable<BaseEntity> matesInfoSave(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_QINIU_TOKEN)
    Observable<QiniuToken> getQiniuToken(@QueryMap Map<String, RequestBody> params);

    @POST(URL_POST_HUKOU)
    @FormUrlEncoded
    Observable<HujiInfo> hukouInfo(@FieldMap Map<String, RequestBody> params);

    @Multipart
    @POST(URL_GET_UPLOADFILE)
    Observable<CommonBean> uploadFile(@Part MultipartBody.Part file);
}
