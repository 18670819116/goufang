package com.ljcs.cxwl.data.api;



import com.ljcs.cxwl.entity.AppLogin;
import com.ljcs.cxwl.entity.Host;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.ljcs.cxwl.data.api.API.URL_GET_ALL_LOGIN;
import static com.ljcs.cxwl.data.api.API.ZHUJI_LIEBIAO;


/**
 * Created by hu on 2017/5/16.
 */

public interface HttpApi {
    //主机列表

    @GET(URL_GET_ALL_LOGIN)
    Observable<AppLogin> Login(@QueryMap Map<String, RequestBody> params);
    @GET(ZHUJI_LIEBIAO)
    Observable<Host> getHost(@QueryMap Map<String,RequestBody> params);


}
