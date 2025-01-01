package com.hjgl.controller;

import com.hjgl.bean.User;
import com.hjgl.dao.UserDao;
import com.hjgl.util.Page;
import com.hjgl.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping("list")
    public RestResult getList(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException, SQLException {
        List list = userDao.getlist(page, checktext);
        int count = userDao.getCount(checktext);
        RestResult result = new RestResult(count, list);
        return result;
    }

    @RequestMapping("add")
    public int add(User user) {
        int result = userDao.add(user);
        return result;
    }

    @RequestMapping("edit")
    public int edit(User user) {
        int result = userDao.edit(user);
        return result;
    }

    @RequestMapping("delete")
    public int delete(User user) {
        int result = userDao.delete(user);
        return result;
    }
}
