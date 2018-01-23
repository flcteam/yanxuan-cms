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
@Component("")
public class GetUids {

    @Autowired
    private RoleDao dao;

    @ResponseBody
    @RequestMapping("//{jsonParam}")
    public JSONObject exe(@PathVariable String jsonParam) throws Exception {
        JSONObject retJSON = new JSONObject();
        JSONObject data = new JSONObject();
        retJSON.put("retcode", 0);
        retJSON.put("retdesc", "");

        JSONObject param = (JSONObject) JSON.parse(jsonParam);
        String roleid = param.getString("roleid");
        dao.getUidsByRoleid(roleid);
        String[] uids = (String[]) data.put("uids", new String[1]);
        data.put("uids", uids);

        retJSON.put("data", data);
        return retJSON;
    }

}
