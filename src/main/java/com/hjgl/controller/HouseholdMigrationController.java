package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.*;
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
@RequestMapping("HouseholdMigration")
public class HouseholdMigrationController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HouseholdMigrationDao hmD;
    @Autowired
    HouseholdDao householdDao;
    @Autowired
    PersonDao personDao;

    @RequestMapping("agree")
    public int agree(@RequestBody HouseholdMigration householdMigration, HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        System.out.println("check:housemigration:-------->");
        System.out.println(householdMigration);
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin=(Admin)obj;
            int adminid=admin.getAdminid();
            householdMigration.setHouseholdmigrationadminid(adminid);
        }
        String sql1="select * from person where personid = ? ";
        ResultSet rs= JdbcUtil.query(sql1,householdMigration.getHouseholdmigrationuserid());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        if(list.size()!=0) {
            Person p = list.get(0);
            p.setPersonhouseholdid(householdMigration.getHouseholdmigrationnewhouseholdid());
            personDao.edit(p);
        }

        return hmD.agree(householdMigration);
    }

    @RequestMapping("refuse")
    public int refuse(HouseholdMigration householdMigration){
        return hmD.refuse(householdMigration);
    }

    @RequestMapping("list")
    public RestResult getlist(Person person , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdMigration> list=hmD.getRecord(person,admin,page);
        for(HouseholdMigration i:list){
            System.out.println(i.getHouseholdmigrationid());
            int personid=i.getHouseholdmigrationuserid();
            int adminid=i.getHouseholdmigrationadminid();
            i.setPerson(personDao.getPersonByPersonid(personid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
        }
        int cnt=hmD.getCount(person,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
