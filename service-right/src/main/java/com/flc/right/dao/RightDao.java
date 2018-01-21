package com.flc.right.dao;

import com.alibaba.fastjson.JSONObject;

public interface RightDao {

    JSONObject getRight(String rightid);

    int getTotal();

}
