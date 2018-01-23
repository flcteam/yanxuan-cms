package com.flc.right.rightclass.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface RightClassDao {

    /**
     * 查出权限分类列表
     * @param fromindex
     * @param pagesize
     * @return
     * @throws Exception
     */
    public JSONArray getRightClassList(int fromindex, int pagesize);

    /**
     * 根据id查出权限分类
     * @param rightclassid
     * @return
     * @throws Exception
     */
    public JSONObject getRightClass(String rightclassid) throws Exception;

    /**
     * 根据查出分类总条数
     * @return
     * @throws Exception
     */
    public int getCount() throws Exception;

    /**
     * 保存权限分类
     * @param param
     * @throws Exception
     */
    public void saveRightClass(JSONObject param) throws Exception;

    /**
     * 根据id删除权限分类
     * @param ids
     * @throws Exception
     */
    public void delRightClasss(String[] ids) throws Exception;

}
