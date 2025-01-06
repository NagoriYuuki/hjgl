package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.AdminDao;
import com.hjgl.dao.HouseholdRegistrationDao;
import com.hjgl.dao.PersonDao;
import com.hjgl.dao.UserDao;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("HouseholdRegistration")
public class HouseholdRegistrationController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PersonDao personDao;
    @Autowired
    HouseholdRegistrationDao hrD;
    @Autowired
    PersonController personController;

    @RequestMapping("agree")
    public int agree(@RequestBody HouseholdRegistration householdRegistration , HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin =(Admin) obj;
            int adminid=admin.getAdminid();
            householdRegistration.setHouseholdregistrationadminid(adminid);
        }
        String sql1="select * from person where personid = ? ";
        ResultSet rs= JdbcUtil.query(sql1,householdRegistration.getHouseholdregistrationpersonid());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
//        System.out.println("agree");
        if(list.size()!=0) {
            Person p = list.get(0);
            p.setPersonhouseholdid(householdRegistration.getHouseholdregistrationhouseholdid());
//            System.out.println(p.getPersonhouseholdid());
            personDao.edit(p);
        }
        return hrD.agree(householdRegistration);
    }
    @RequestMapping("refuse")
    public int refuse(@RequestBody HouseholdRegistration householdRegistration){
        return hrD.refuse(householdRegistration);
    }

    @RequestMapping("list")
    public RestResult getlist(Person person , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdRegistration> list=hrD.getRecord(person,admin,page);
        for(HouseholdRegistration i:list){
            int personid=i.getHouseholdregistrationuserid();
            int adminid=i.getHouseholdregistrationadminid();
            i.setPerson(personDao.getPersonByPersonid(personid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
        }

        int cnt=hrD.getCount(person,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
