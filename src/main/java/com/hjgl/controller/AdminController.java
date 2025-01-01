package com.hjgl.controller;

import com.hjgl.bean.Admin;
import com.hjgl.dao.AdminDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminDao adminDao;

    @RequestMapping("list")
    public RestResult getList(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException, SQLException {
//        System.out.println("1111111111111111111111");
        List list=adminDao.getList(page,checktext);
        int count= adminDao.getCount(checktext);
        RestResult result=new RestResult(count,list);
        return result;
    }

    @RequestMapping("add")
    public int add(Admin admin) {
//        System.out.println("1111111111111111111111");
        System.out.println(admin.getAdminaccount());
        System.out.println(admin.getAdminname());
        System.out.println(admin.getAdminpassword());
        int result = adminDao.add(admin);
        return result;
    }

    @RequestMapping("edit")
    public int edit(Admin admin) {
        int result = adminDao.edit(admin);
        return result;
    }

    @RequestMapping("delete")
    public int delete(Admin admin) {
        int result = adminDao.delete(admin);
        return result;
    }


}
