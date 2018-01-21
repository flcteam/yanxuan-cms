package com.flc.core;

import com.alibaba.fastjson.JSONObject;

public class BeanParam {

    public int retcode;
    public String retdesc;
    public JSONObject data;

    public void setParam(String key, String value) {
        data.put(key, value);
    }

    public void setParam(String key, int value) {
        data.put(key, value);
    }

    public void setParam(String key, boolean value) {
        data.put(key, value);
    }

    public void setParam(String key, JSONObject value) {
        data.put(key, value);
    }

}
