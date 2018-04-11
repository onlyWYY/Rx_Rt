package com.star.app.retrofit.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.star.app.R;

/**
 * Created by suxing on 2018/4/10.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private static LoadingDialog mLoadingDialog;

    public static void showprogress(Context context, boolean iscanCancel) {
        mLoadingDialog = new LoadingDialog(context, R.style.loading_dialog);
        //触摸外部无法取消,必须
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setTitle("");
        mLoadingDialog.setContentView(R.layout.dialog_loading);
        mLoadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //按返回键响应是否取消等待框的显示
        mLoadingDialog.setCancelable(iscanCancel);
        mLoadingDialog.show();
    }

    public static void dismissDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}
