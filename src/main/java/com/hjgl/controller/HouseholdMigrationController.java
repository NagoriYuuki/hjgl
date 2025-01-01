package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.*;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

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
    public int agree(HouseholdMigration householdMigration, HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin=(Admin)obj;
            int adminid=admin.getAdminid();
            householdMigration.setHouseholdMigrationAdminID(adminid);
        }
        return hmD.agree(householdMigration,personDao);
    }

    @RequestMapping("refuse")
    public int refuse(HouseholdMigration householdMigration){
        return hmD.refuse(householdMigration);
    }

    @RequestMapping("list")
    public RestResult getlist(User user , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdMigration> list=hmD.getRecord(user,admin,page);
        for(HouseholdMigration i:list){
            int userid=i.getHouseholdMigrationUserID();
            int adminid=i.getHouseholdMigrationAdminID();
            i.setUser(userDao.getUserByUserid(userid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
        }

        int cnt=hmD.getCount(user,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
