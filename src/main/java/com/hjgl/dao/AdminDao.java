package com.hjgl.dao;

import com.hjgl.bean.Admin;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.Page;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminDao {
    public Admin getAdminByAdminid(int adminid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where adminid=? ";
        ResultSet rs = JdbcUtil.query(sql, adminid);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs, Admin.class);
        Admin res = list.get(0);
        JdbcUtil.close(rs);
        return res;
    }

    public List<Admin> getAdminByName(String adminaccount) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where adminname = ?";
        ResultSet rs = JdbcUtil.query(sql, adminaccount);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs, Admin.class);
        JdbcUtil.close(rs);
        return list;
    }

    public List<Admin> getAdminByAccount(String adminaccount) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where adminaccount = ? ";
//        System.out.println(adminaccount);
        ResultSet rs = JdbcUtil.query(sql, adminaccount);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs, Admin.class);
        JdbcUtil.close(rs);
        return list;
    }

    public List<Admin> getList(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin ";
        List list = new ArrayList();
        if (checktext != null) {
            sql = sql + " where adminname like ? ";
            list.add("%" + checktext + "%");

        }
        if (page != null) {
            sql = sql + " limit ?,? ";
            list.add(page.getStart());
            list.add(page.getLimit());
        }
        ResultSet rs = JdbcUtil.query(sql, list.toArray());
        List<Admin> list1 = JdbcUtil.convertResultSetToList(rs, Admin.class);
        JdbcUtil.close(rs);
        return list1;
    }

    public int getCount(String checktext) throws SQLException {
        //聚合函数，用于计算满足查询条件的记录数
        String sql = "select count(*) from admin ";
        if (checktext != null) {
            sql = sql + "where adminname like ? ";
            String find = "%" + checktext + "%";
            ResultSet rs = JdbcUtil.query(sql, find);
            int cnt = 0;
            rs.next();
            cnt = rs.getInt(1);
            JdbcUtil.close(rs);
            return cnt;
        }
        ResultSet rs = JdbcUtil.query(sql);
        rs.next();
        int cnt = rs.getInt(1);
        JdbcUtil.close(rs);
        return cnt;
    }

    public int add(Admin admin) {
        String sql = "insert into admin(adminname, adminaccount, adminpassword) values(?, ?, ?)";
        int result = JdbcUtil.update(sql, admin.getAdminname(), admin.getAdminaccount(), admin.getAdminpassword());
        return result;
    }

    public int edit(Admin admin) {
        String sql = "update admin set adminname = ?, adminaccount = ?, adminpassword = ? where adminid = ?";
        int result = JdbcUtil.update(sql, admin.getAdminname(), admin.getAdminaccount(), admin.getAdminpassword(), admin.getAdminid());
        return result;
    }

    public int delete(Admin admin) {
        String sql = "delete from admin where adminid = ?";
        int result = JdbcUtil.update(sql, admin.getAdminid());
        return result;
    }

}
