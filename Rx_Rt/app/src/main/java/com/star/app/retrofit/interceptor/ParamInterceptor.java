package com.star.app.retrofit.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxing on 2018/4/9.
 */

public class ParamInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request == null) {
            throw new RuntimeException("Request返回值不能为空");
        } else {
            // 添加新的参数
            HttpUrl.Builder authorizedUrlBuilder = request.url()
                    .newBuilder()
                    .scheme(request.url().scheme())
                    .host(request.url().host())
//                    .addQueryParameter(MarvelService.PARAM_HASH, marvelHash) //添加新的参数
                    ;

            // 新的请求
            Request newRequest = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(authorizedUrlBuilder.build())
//                    .addHeader("star","stttttt") //添加公共请求头
                    .build();
            return chain.proceed(newRequest);
        }
    }
}
