package com.star.app.retrofit.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by suxing on 2018/4/9.
 */

public class StringConverterFactory extends Converter.Factory {
    private static StringConverterFactory Instance = null;

    private StringConverterFactory() {
    }

    public static StringConverterFactory create() {
        if (null == Instance)
            synchronized (StringConverterFactory.class) {
                if (null == Instance)
                    Instance = new StringConverterFactory();
            }
        return Instance;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new StringRequestBodyConverter();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new StringResponseBodyConverter();
    }
}
