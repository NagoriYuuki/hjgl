package com.hjgl.controller;

import com.hjgl.bean.Household;
import com.hjgl.bean.Person;
import com.hjgl.bean.User;
import com.hjgl.dao.HouseholdDao;
import com.hjgl.dao.PersonDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("person")

public class PersonController {
    @Autowired
    PersonDao personDao;

    @Autowired
    HouseholdDao householdDao;

    @RequestMapping("list")
    public RestResult getlist(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        List list = personDao.getlistname(page, checktext);
        int count = personDao.getCountname(checktext);
        RestResult result = new RestResult(count, list);
        return result;
    }

    //查询户籍
//    @RequestMapping("list")
    public RestResult getlisthouse(Person person,Page page) throws SQLException, IllegalAccessException, InstantiationException {
        Household h=householdDao.getPersonByHousehold(person,page);
        List<Person> list=personDao.getHouselist(h,page);
        int count = personDao.getHouseCount(h,page);
        RestResult result=new RestResult(count,list);
        return  result;

    }

    //    @RequestMapping("list")
    public RestResult getlistnumber(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        List list = personDao.getlistnumber(page, checktext);
        int count = personDao.getCountnumber(checktext);
        RestResult result = new RestResult(count, list);
        return result;
    }

    @RequestMapping("add")
    public int add(Person person) {
        int res = personDao.add(person);
        return res;
    }

    @RequestMapping("edit")
    public int edit(Person person) {
        int res = personDao.edit(person);
        return res;
    }

    @RequestMapping("delete")
    public int delete(Person person) {
        int res = personDao.delete(person);
        return res;
    }

}
