package com.star.app.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.star.app.BuildConfig;
import com.star.app.retrofit.convert.GsonConverterFactory;

import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Retrofit Client
 * Created by suxing on 2018/4/9.
 */
public class RetrofitBuilder {
    private static ServiceApi Instance = null;

    public static ServiceApi getApiInstance() {
        if (null == Instance)
            synchronized (ServiceApi.class) {
                if (null == Instance)
                    Instance = createApi();
            }
        return Instance;
    }

    private static ServiceApi createApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://temp.jiangxunlu.com/api/selan/api/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
        return retrofit.create(ServiceApi.class);
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new ParamInterceptor())
                .addInterceptor(logInterceptor())
//                .sslSocketFactory(createSSLSocket(), x509TrustManager)
//                .cookieJar()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static SSLSocketFactory createSSLSocket() {
        try {
            SSLContext ssl = SSLContext.getInstance("SSL");
            ssl.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
            return ssl.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static X509TrustManager x509TrustManager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] x509Certificates = new X509Certificate[0];
            return x509Certificates;
        }
    };

    private static Interceptor logInterceptor() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .request("Request")
                .response("Response")
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
//                .addHeader("star", "star") //添加公共请求头
                .build();
    }
}
