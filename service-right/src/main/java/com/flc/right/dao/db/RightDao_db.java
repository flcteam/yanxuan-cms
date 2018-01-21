package com.flc.right.dao.db;

import com.alibaba.fastjson.JSONObject;
import com.flc.right.dao.RightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("com.flc.dao.right.dao")
public class RightDao_db implements RightDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public JSONObject getRight(String rightid) {
        return null;
    }

    @Override
    public int getTotal() {
        int count = jdbcTemplate.queryForObject("select count(0) from t_right",Integer.class);
        return count;
    }

}
