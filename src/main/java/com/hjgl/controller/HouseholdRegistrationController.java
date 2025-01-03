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
        System.out.println("1111111111111113642e24357614645679");
        System.out.println("controller:-------->"+householdRegistration.getHouseholdregistrationhouseholdid());
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin =(Admin) obj;
            int adminid=admin.getAdminid();
            householdRegistration.setHouseholdregistrationadminid(adminid);
        }
        String sql1="select * from person where personid = ? ";
        ResultSet rs= JdbcUtil.query(sql1,householdRegistration.getHouseholdregistrationpersonid());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        if(list.size()!=0) {
            Person p = list.get(0);
            p.setPersonhouseholdid(householdRegistration.getHouseholdregistrationhouseholdid());
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
            System.out.println(1);
            int personid=i.getHouseholdregistrationuserid();
            System.out.println(2);
            int adminid=i.getHouseholdregistrationadminid();
            System.out.println(3+" "+personid+" "+i.getHouseholdregistrationid());
            i.setPerson(personDao.getPersonByPersonid(personid));
            System.out.println(4);
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
            System.out.println(5);
        }

        int cnt=hrD.getCount(person,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
