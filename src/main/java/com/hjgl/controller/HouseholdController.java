package com.hjgl.controller;

import com.hjgl.bean.Household;
import com.hjgl.bean.Person;
import com.hjgl.dao.HouseholdDao;
import com.hjgl.dao.PersonDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("household")

public class HouseholdController {
    @Autowired
    HouseholdDao householdDao;

    @Autowired
    PersonDao personDao;

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

    @PostMapping("setHouseholder")
    public String setHouseholder(@RequestParam(required = false) Integer householdid, @RequestParam int personid) {
        System.out.println("sethouseholder");
        System.out.println("householdid: " + householdid);

        try {
            // 获取人员信息
            Person person = personDao.getPersonByPersonid(personid);
            if (person == null) {
                return "Person not found";
            }

            // 如果 householdid 是 null，说明数据库会自动生成，或者前端未传递该值
            if (householdid != null) {
                // 更新人的户籍 ID
                person.setPersonhouseholdid(householdid);
            }

            System.out.println("Updated householdid: " + householdid);

            // 更新 Person 表
            personDao.edit(person);

            return "Householder set successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while setting householder";
        }
    }

}