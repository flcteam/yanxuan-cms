package com.flc.role.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flc.role.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("role.del")
public class Del {
    @Autowired
    private RoleDao dao;

    @ResponseBody
    @RequestMapping("/role/del/{jsonParam}")
    public JSONObject exe(@PathVariable String jsonParam) throws Exception {
        JSONObject retJSON = new JSONObject();
        JSONObject data = new JSONObject();
        retJSON.put("retcode", 0);
        retJSON.put("retdesc", "");

        JSONObject param = (JSONObject) JSON.parse(jsonParam);
        String[] ids = param.getObject("ids", String[].class);
        dao.delRoles(ids);

        retJSON.put("data", data);
        return retJSON;
    }

}
