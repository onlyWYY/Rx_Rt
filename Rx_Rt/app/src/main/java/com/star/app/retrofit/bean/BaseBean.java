package com.star.app.retrofit.bean;

/**
 * Created by suxing on 2018/4/11.
 */

public class BaseBean implements IBaseBean {
    private int code;
    private String msg;

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
