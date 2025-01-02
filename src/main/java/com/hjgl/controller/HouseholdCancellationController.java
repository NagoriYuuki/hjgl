package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.*;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("HouseholdCancellation")
public class HouseholdCancellationController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    PersonDao userDao;
    @Autowired
    HouseholdCancellationDao hcD;
    @Autowired
    HouseholdDao householdDao;


    @RequestMapping("agree")
    public int agree(HouseholdCancellation householdCancellation, HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        System.out.println("haha");
        System.out.println(householdCancellation.getHouseholdcancellationUserid());
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin=(Admin)obj;
            int adminid=admin.getAdminid();
            householdCancellation.setHouseholdcancellationadminid(adminid);
        }
        System.out.println(1);
        return hcD.agree(householdCancellation,householdDao);
    }

    @RequestMapping("refuse")
    public int refuse(HouseholdCancellation householdCancellation){
        return hcD.refuse(householdCancellation);
    }

    @RequestMapping("list")
    public RestResult getlist(User user , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdCancellation> list=hcD.getRecord(user,admin,page);
        for(HouseholdCancellation i:list){
            int userid=i.getHouseholdcancellationid();
            int adminid=i.getHouseholdcancellationadminid();
            i.setPerson(userDao.getPersonByPersonid(userid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
        }
        int cnt=hcD.getCount(user,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
