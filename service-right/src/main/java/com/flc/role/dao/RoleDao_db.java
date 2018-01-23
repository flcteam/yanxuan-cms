package com.flc.role.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flc.id.ID;
import com.flc.id.IDType;
import com.flc.role.RoleDao;
import com.flc.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("role.dao")
public class RoleDao_db implements RoleDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 查询角色列表
     */
    public JSONArray getRoleList(int fromindex, int pagesize)
            throws Exception {
        List rows = null;
        JSONArray rusultlist = new JSONArray();
        if(pagesize <= 0) {
            String sql = "select  f_name, f_roleid, f_desc, f_modifytime from t_role order by f_roleid ";
            rows = jdbcTemplate.queryForList(sql);
        } else {
            String sql = "select f_name, f_roleid, f_desc, f_modifytime from t_role order by f_roleid limit ?,?";
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
                JSONObject mj = new JSONObject();
                Map map = (Map) it.next();
                mj.put("roleid", (String) map.get("f_roleid"));
                mj.put("name", (String) map.get("f_name"));
                mj.put("desc", (String) map.get("f_desc"));
                mj.put("modifytime", (String) map.get("f_modifytime"));
                rusultlist.add(mj);
            }
        } catch (Exception e) {
            throw e;
        }
        return rusultlist;
    }

    /**
     * 根据id获取角色
     */
    public JSONObject getRole(String roleid) throws Exception {
        if(roleid == null || roleid.equals("")) {
            return null;
        }
        String sql = "select f_name, f_roleid, f_desc, f_modifytime from t_role where f_roleid = ?";
        List rows = jdbcTemplate.queryForList(sql, roleid);
        Iterator it = rows.iterator();
        JSONObject mj = new JSONObject();
        try {
            while (it.hasNext()) {
                Map map = (Map) it.next();
                mj.put("roleid", (String) map.get("f_roleid"));
                mj.put("name", (String) map.get("f_name"));
                mj.put("desc", (String) map.get("f_desc"));
                mj.put("modifytime", (String) map.get("f_modifytime"));

            }
        } catch (Exception e) {
            throw e;
        }
        return mj;
    }

    /**
     * 获取角色总条数
     */
    public int getCount() throws Exception {
        return jdbcTemplate.queryForObject(
                "select count(0) from t_role",
                new Object[] {},
                java.lang.Integer.class);
    }

    /**
     * 保存角色
     */
    public void saveRole(JSONObject mj) throws Exception {
        String roleid = mj.getString("roleid");
        int count = jdbcTemplate.queryForObject("select count(0) from t_role where f_roleid = ?", new Object[] {roleid}, java.lang.Integer.class);
        if(count == 0) {
            if(roleid.equals("")) {
                roleid = ID.getDUID(IDType.ROLE);
            }
            String sql = "insert into t_role (f_roleid, f_name, f_desc, f_modifytime) values (?,?,?,?)";
            jdbcTemplate.update(sql, roleid, mj.getString("name"), mj.getString("desc"), DateUtil.formatDBDate(new Date()));
        } else {
            String sql = "update t_role set f_name = ?, f_desc = ?, f_modifytime = ? where f_roleid = ?";
            jdbcTemplate.update(sql, mj.getString("name"), mj.getString("desc"), DateUtil.formatDBDate(new Date()), roleid);
        }
    }

    /**
     * 根据id删除
     */
    public void delRoles(String[] ids) throws Exception {
        if(ids == null || ids.length == 0) {
            return;
        }
        for(int i = 0; i < ids.length; i++) {
            String sql = "delete from t_role where f_roleid = ?";
            jdbcTemplate.update(sql, ids[i]);
        }
    }

    /**
     * 检测用户是否有该角色
     * @param uid 用户id
     * @param roleid 角色id
     */
    public boolean checkUserAndRole(String uid, String roleid) throws Exception {
        if(uid == null || uid.equals("")) {
            return false;
        }
        if(roleid == null || roleid.equals("")) {
            return false;
        }
        int count = jdbcTemplate.queryForObject("select count(0) from t_user_role where (f_roleid = ? and f_uid = ?) or exists ( select 1 from t_user_cos where f_cosid in (select f_cosid from t_cos_role where f_roleid = ?) and f_uid = ? )", new Object[]{roleid, uid, roleid, uid}, java.lang.Integer.class);
        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据角色id查出所有含有该角色id的用户id
     */
    public String[] getUidsByRoleid(String roleid) throws Exception {
        if(roleid == null || roleid.equals("")) {
            return null;
        }
        List<String> resultlist = new ArrayList<String>();
        String sql = "select distinct f_uid from ( select f_uid from t_user_role where f_roleid = ? union all select f_uid from t_user_cos where f_cosid in (select f_cosid from t_cos_role where f_roleid = ?)) b";
        List rows = jdbcTemplate.queryForList(sql, roleid, roleid);
        if(rows == null) {
            return null;
        }
        if(rows.size() == 0) {
            return null;
        }
        Iterator it = rows.iterator();
        while (it.hasNext()) {
            JSONObject mj = new JSONObject();
            Map map = (Map) it.next();
            resultlist.add((String) map.get("f_uid"));
        }
        return resultlist.toArray(new String[resultlist.size()]);
    }
}
