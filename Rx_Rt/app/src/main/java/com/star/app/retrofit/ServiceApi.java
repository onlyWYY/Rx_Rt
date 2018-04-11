package com.star.app.retrofit;


import com.star.app.retrofit.bean.BaseBean;
import com.star.app.retrofit.bean.ConfigBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by suxing on 2018/4/9.
 */

public interface ServiceApi {

    @FormUrlEncoded
    @POST("index.php?r=config")
    Observable<ConfigBean> config(
            @Field("verson") String verson,
            @Field("os") String os);

    @POST("index.php?r=user/refresh-login")
    Observable<BaseBean> refresh(
            @Query("token") String token);
}
