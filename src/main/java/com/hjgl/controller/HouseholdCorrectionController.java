package com.hjgl.controller;

import com.hjgl.bean.*;
import com.hjgl.dao.AdminDao;
import com.hjgl.dao.HouseholdCorrectionDao;
import com.hjgl.dao.PersonDao;
import com.hjgl.dao.UserDao;
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
@RequestMapping("HouseholdCorrection")
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
            householdCorrection.setHouseholdcorrectionadminid(adminid);
        }
        System.out.println(1111);
        String sql1= "select * from person where personid = ? ";
        ResultSet rs= JdbcUtil.query(sql1,householdCorrection.getHouseholdcorrectionpersonid());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        if(list.size()!=0) {
            Person person = list.get(0);
//            personDao.edit(person);
        }
        return hcD.agree(householdCorrection);
    }

    @RequestMapping("refuse")
    public int refuse(HouseholdCorrection householdCorrection){
        return hcD.refuse(householdCorrection);
    }

    @RequestMapping("list")
    public RestResult getlist(User user , Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<HouseholdCorrection> list=hcD.getRecord(user,admin,page);
        System.out.println("List");
        for(HouseholdCorrection i:list){
            int userid=i.getHouseholdcorrectionpersonid();//用户id
            int adminid=i.getHouseholdcorrectionadminid();//管理员id
            i.setPerson(personDao.getPersonByPersonid(userid));
            i.setAdmin(adminDao.getAdminByAdminid(adminid));
            //查询用户管理员信息
        }
        System.out.println(1234243);
        int cnt=hcD.getCount(user,admin);
        System.out.println(cnt+" "+list.size());
        RestResult result=new RestResult(cnt,list);
        return result;
    }
}
