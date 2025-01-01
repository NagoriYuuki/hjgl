package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.AdminDao;
import com.hjgl.dao.HouseholdRegistrationDao;
import com.hjgl.dao.PersonDao;
import com.hjgl.dao.UserDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class HouseholdRegistrationController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;
    @Autowired
    Person person;

    @Autowired
    PersonDao personDao;

    @Autowired
    HouseholdRegistrationDao hrD;
    @Autowired
    PersonController personController;

    @RequestMapping("agree")
    public int agree(HouseholdRegistration householdRegistration , HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin =(Admin) obj;
            int adminid=admin.getAdminid();
            householdRegistration.setHouseholdRegistrationAdminID(adminid);
        }
        return hrD.agree(householdRegistration,personDao);
    }
    @RequestMapping("refuse")
    public int refuse(HouseholdRegistration householdRegistration){
        return hrD.refuse(householdRegistration);
    }

    @RequestMapping("list")
    public RestResult getlist(User user , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdCancellation> list=hrD.getRecord(user,admin,page);
        for(HouseholdCancellation i:list){
            int userid=i.getHouseholdCancellationUserID();
            int adminid=i.getHouseholdCancellationAdminID();
            i.setUser(userDao.getUserByUserid(userid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
        }

        int cnt=hrD.getCount(user,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
