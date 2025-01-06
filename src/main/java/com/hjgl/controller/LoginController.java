package com.hjgl.controller;

import com.hjgl.bean.Admin;
import com.hjgl.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    AdminDao dao;

    @RequestMapping("doLogin")
    public String doLogin(Admin admin, HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
//        System.out.println(admin.getAdminaccount());
        List<Admin> list = dao.getAdminByAccount(admin.getAdminaccount());
//        System.out.println(list.size());
        if (list.size() == 1) {
            Admin adminD = list.get(0);
            String password = admin.getAdminpassword();
            String passwordD = adminD.getAdminpassword();
            if (password != null && password.equals(passwordD)) {
                session.setAttribute("loginadmin", adminD);
                return "success";
            }
        }
        return "fail";
    }

    @RequestMapping("islogin")
    public Admin islogin(HttpSession session) {
        Object obj = session.getAttribute("loginadmin");
        if (obj instanceof Admin) {
            Admin result = (Admin) obj;
            return result;
        }
        return null;

    }


    @RequestMapping("loginout")
    public String loginout(HttpSession session) {
        session.removeAttribute("loginadmin");
        return "success";
    }

}