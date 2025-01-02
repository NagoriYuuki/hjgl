package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.*;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
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
        System.out.println("controller:-------->"+householdCancellation.getHouseholdcancellationhouseholdid());
        System.out.println(householdCancellation.getHouseholdcancellationUserid());
        Object obj=session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin=(Admin)obj;
            int adminid=admin.getAdminid();
            householdCancellation.setHouseholdcancellationadminid(adminid);
        }
        System.out.println(householdCancellation.getHouseholdcancellationhouseholdid());
        String sql1= "select * from Household where Householdid = ?";
        ResultSet rs = JdbcUtil.query(sql1,householdCancellation.getHouseholdcancellationhouseholdid());
        List<Household> list= JdbcUtil.convertResultSetToList(rs, Household.class);
        System.out.println(list.size());
        if(list.size()!=0) {
            Household household = list.get(0);
            householdDao.delete(household);
        }
        return hcD.agree(householdCancellation);
    }

    @RequestMapping("refuse")
    public int refuse(HouseholdCancellation householdCancellation){
        return hcD.refuse(householdCancellation);
    }

    @RequestMapping("list")
    public RestResult getlist(User user , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdCancellation> list=hcD.getRecord(user,admin,page);
        for(HouseholdCancellation i:list){
            int userid=i.getHouseholdcancellationUserid();
            int adminid=i.getHouseholdcancellationadminid();
            i.setPerson(userDao.getPersonByPersonid(userid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
        }
        int cnt=hcD.getCount(user,admin);
        RestResult result=new RestResult(cnt,list);
        return result;
    }

    @RequestMapping("delete")
    public int delete(HouseholdCancellation householdCancellation) {
        int res = hcD.delete(householdCancellation);
        return res;
    }
}
