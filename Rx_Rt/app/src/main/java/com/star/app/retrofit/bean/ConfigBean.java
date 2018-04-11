package com.star.app.retrofit.bean;

import java.util.List;

/**
 * 配置信息bean
 * Created by suxing on 2018/4/11.
 */
public class ConfigBean extends BaseBean {
    //    private int code;
//    private String msg;
    private ConfigInfo info;

//    @Override
//    public int code() {
//        return code;
//    }
//
//    @Override
//    public String msg() {
//        return msg;
//    }

    public class ConfigInfo {
        public int version;
        public int apksize;
        public String apkurl;
        public String apkhash;
        public int updatetype;
        public List<String> backgrounds;
    }
}
