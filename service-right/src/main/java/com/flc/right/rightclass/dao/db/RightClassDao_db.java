package com.flc.right.rightclass.dao.db;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flc.id.IDType;
import com.flc.right.rightclass.dao.RightClassDao;
import com.flc.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component("right.rightclass.dao")
public class RightClassDao_db implements RightClassDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public JSONArray getRightClassList(int fromindex, int pagesize) {
        List rows = null;
        JSONArray rusultlist = new JSONArray();
        if(pagesize <= 0) {
            String sql = "select  f_name, f_rightclassid, f_modifytime from t_right_class order by f_rightclassid ";
            rows = jdbcTemplate.queryForList(sql);
        } else {
            String sql = "select f_name, f_rightclassid, f_modifytime from t_right_class order by f_rightclassid limit ?,?";
            rows = jdbcTemplate.queryForList(sql, fromindex, pagesize);
        }
        if(rows == null) {
            return null;
        }
        if(rows.size() == 0) {
            return null;
        }
        Iterator it = rows.iterator();
        try {
            while (it.hasNext()) {
                JSONObject o = new JSONObject();
                Map map = (Map) it.next();
                o.put("rightclassid", (String) map.get("f_rightclassid"));
                o.put("name", (String) map.get("f_name"));
                o.put("modifytime", (String) map.get("f_modifytime"));
                rusultlist.add(o);
            }
        } catch (Exception e) {
            throw e;
        }
        return rusultlist;
    }

    @Override
    public JSONObject getRightClass(String rightclassid) throws Exception {
        if(rightclassid == null || rightclassid.equals("")) {
            return null;
        }
        String sql = "select f_name, f_rightclassid, f_modifytime from t_right_class where f_rightclassid = ?";
        List rows = jdbcTemplate.queryForList(sql, rightclassid);
        Iterator it = rows.iterator();
        JSONObject mj = new JSONObject();
        try {
            while (it.hasNext()) {
                Map map = (Map) it.next();
                mj.put("rightclassid", (String) map.get("f_rightclassid"));
                mj.put("name", (String) map.get("f_name"));
                mj.put("modifytime", (String) map.get("f_modifytime"));
            }
        } catch (Exception e) {
            throw e;
        }
        return mj;
    }

    @Override
    public int getCount() throws Exception {
        return jdbcTemplate.queryForObject(
                "select count(0) from t_right_class",
                new Object[] {},
                java.lang.Integer.class);
    }

    @Override
    public void saveRightClass(JSONObject param) throws Exception {
        String rightclassid = param.getString("rightclassid");
        int count = jdbcTemplate.queryForObject("select count(0) from t_right_class where f_rightclassid = ?", new Object[] {rightclassid}, java.lang.Integer.class);
        if(count == 0) {
            if(rightclassid.equals("")) {
                rightclassid = com.flc.id.ID.getDUID(IDType.RIGHTCLASS);
            }
            String sql = "insert into t_right_class (f_rightclassid, f_name, f_modifytime) values (?,?,?)";
            jdbcTemplate.update(sql, rightclassid, param.getString("name"), DateUtil.formatDBDate(new Date()));
        } else {
            String sql = "update t_right_class set f_name = ?, f_modifytime = ? where f_rightclassid = ?";
            jdbcTemplate.update(sql, param.getString("name"), DateUtil.formatDBDate(new Date()), rightclassid);
        }
    }

    @Override
    public void delRightClasss(String[] ids) throws Exception {
        if(ids == null || ids.length == 0) {
            return;
        }
        for(int i = 0; i < ids.length; i++) {
            String sql = "delete from t_right_class where f_rightclassid = ?";
            jdbcTemplate.update(sql, ids[i]);
        }
    }
}
