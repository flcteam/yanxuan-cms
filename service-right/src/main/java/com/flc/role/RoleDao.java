package com.flc.role;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface RoleDao {

    public final static String BeanName = "role.dao";

    /**
     * 根据分类id查出角色列表
     * @param fromindex
     * @param pagesize
     * @return
     * @throws Exception
     */
    public JSONArray getRoleList(int fromindex, int pagesize) throws Exception;

    /**
     * 根据角色id查出角色
     * @param roleid
     * @return
     * @throws Exception
     */
    public JSONObject getRole(String roleid) throws Exception;

    /**
     * 根据分类id查出总条数
     * @return
     * @throws Exception
     */
    public int getCount() throws Exception;

    /**
     * 保存角色
     * @param mj
     * @throws Exception
     * return roleid
     */
    public String saveRole(JSONObject mj) throws Exception;

    /**
     * 根据id删除角色
     * @param ids
     * @throws Exception
     */
    public void delRoles(String[] ids) throws Exception;

    /**
     * 检测用户是否有该角色
     * @param uid
     * @param roleid
     * @return
     * @throws Exception
     */
    public boolean checkUserAndRole(String uid, String roleid)throws Exception;

    /**
     * 根据角色id查出所有含有该角色的用户id
     * @param roleid 角色id
     * @return
     * @throws Exception
     */
    public String[] getUidsByRoleid(String roleid) throws Exception;
    
}
