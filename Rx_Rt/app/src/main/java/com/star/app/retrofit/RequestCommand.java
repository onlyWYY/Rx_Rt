package com.star.app.retrofit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by suxing on 2018/4/9.
 */
public class RequestCommand {
    private static ServiceApi getApi() {
        return RetrofitBuilder.getApiInstance();
    }

    public static <T> void send(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io()) // 用于切换线程之前，只有第一个subscribeOn() 起作用
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 用于切换线程之后，之后不可再调用subscribeOn切换线程
                .subscribe(observer);
    }

    public static void requestConfig(Observer observer) {
        send(getApi().config("1.0.1", "android"), observer);
    }

    public static void requestRefresh(Observer observer) {
        send(getApi().refresh("847843287278232187432187"), observer);
    }
}
