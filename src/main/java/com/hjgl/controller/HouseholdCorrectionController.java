package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.AdminDao;
import com.hjgl.dao.HouseholdCorrectionDao;
import com.hjgl.dao.PersonDao;
import com.hjgl.dao.UserDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class HouseholdCorrectionController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HouseholdCorrectionDao hcD;
    @Autowired
    PersonDao personDao;

    @RequestMapping("agree")
    public int agree(HouseholdCorrection householdCorrection, HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin=(Admin)obj;
            int adminid=admin.getAdminid();
            householdCorrection.setHouseholdCorrectionAdminID(adminid);
        }
        return hcD.agree(householdCorrection,personDao);
    }

    @RequestMapping("refuse")
    public int refuse(HouseholdCorrection householdCorrection){
        return hcD.refuse(householdCorrection);
    }

    @RequestMapping("list")
    public RestResult getlist(User user , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdCorrection> list=hcD.getRecord(user,admin,page);
        for(HouseholdCorrection i:list){
            int userid=i.getHouseholdCorrectionPersonID();//用户id
            int adminid=i.getHouseholdCorrectionAdminID();//管理员id
            i.setUser(userDao.getUserByUserid(userid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
            //查询用户管理员信息
        }

        int cnt=hcD.getCount(user,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

}
