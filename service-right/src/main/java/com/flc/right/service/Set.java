package com.flc.right.service;

import com.flc.core.CallBean;
import com.flc.right.dao.RightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("right.set")
public class Set extends CallBean {

    @Autowired
    private RightDao dao;

    @RequestMapping("/right/set/{params}")
    public String exe(@PathVariable String params) {
        int count = dao.getTotal();
        return count + "";
    }

}
