package com.flc.core;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class CallBean {

    public int retcode;
    public String retdesc;

    public BeanParam exe(JSONObject bParam) {
        BeanParam ret = new BeanParam();
        retcode = 0;
        retdesc = "";
        return ret;
    }

}
