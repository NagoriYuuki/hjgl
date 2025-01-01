package com.hjgl.controller;

import com.hjgl.bean.Household;
import com.hjgl.dao.HouseholdDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("household")

public class HouseholdController {
    @Autowired
    HouseholdDao householdDao;

    @RequestMapping("list")
    public RestResult getlist(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        List list = householdDao.getlist(page, checktext);
        int count = householdDao.getCount(checktext);
        RestResult result = new RestResult(count, list);
        return result;
    }

    @RequestMapping("add")
    public int add(Household household) {
        int res = householdDao.add(household);
        return res;
    }

    @RequestMapping("edit")
    public int edit(Household household) {
        int res = householdDao.edit(household);
        return res;
    }

    @RequestMapping("delete")
    public int delete(Household household) {
        int res = householdDao.delete(household);
        return res;
    }
}