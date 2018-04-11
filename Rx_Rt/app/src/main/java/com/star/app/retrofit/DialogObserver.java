package com.star.app.retrofit;

import android.content.Context;
import android.util.Log;

import com.star.app.retrofit.bean.BaseBean;
import com.star.app.retrofit.bean.IBaseBean;
import com.star.app.retrofit.utils.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by suxing on 2018/4/10.
 */
public abstract class DialogObserver<T extends BaseBean> implements Observer<T> {

    private static final int success = 200;
    private static final int login = 401;
    private Context mContext;

    public DialogObserver(Context mContext) {
        this.mContext = mContext;
        LoadingDialog.showprogress(mContext, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.i("star", "onSubscribe");
        // 判断网络是否可用
    }

    @Override
    public void onNext(T t) {
        Log.i("star", "onNext");
        // 根据 响应码，做处理
        int code = t.code();
        if (login == code) { // 异地登录
            Log.i("star", "login*out");
        } else if (success == code) { // 成功
            Log.i("star", "success");
            success(t);
        } else {
            Log.i("star", "error*");
        }
    }

    public abstract void success(T t);

    @Override
    public void onError(Throwable e) {
        Log.i("star", e.toString());
        LoadingDialog.dismissDialog();
        if (e.toString().contains(login + "")) { // 异地登录
            Log.i("star", "login out");
        } else { // 网络连接异常/服务器异常
            Log.i("star", "error");
        }

    }

    @Override
    public void onComplete() {
        Log.i("star", "onComplete");
        LoadingDialog.dismissDialog();
    }
}
