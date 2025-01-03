package com.hjgl.dao;


import com.hjgl.bean.*;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.OperationLog;
import com.hjgl.util.Page;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HouseholdRegistrationDao {
    @OperationLog(description = "同意")
    public int agree(HouseholdRegistration householdRegistration ,PersonDao personDao) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="update HouseholdRegistration set HouseholdRegistrationRteturnTime = now() and HouseholdRegistrationStatus = ? where HouseholdRegistrationID = ? ";
        int res = JdbcUtil.update(sql,"agree",householdRegistration.getHouseholdRegistrationID());
        String sql1="select * from person where personid = ? ";
        ResultSet rs=JdbcUtil.query(sql1,householdRegistration.getHouseholdRegistrationPersonID());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        Person p=list.get(0);
        p.setPersonhouseholdid(householdRegistration.getHouseholdRegistrationHouseholdID());
        personDao.edit(p);
        return res;
    }

    @OperationLog(description = "拒绝")
    public int refuse(HouseholdRegistration householdRegistration) {
        String sql = "update HouseholdRegistration set HouseholdRegistrationStatus = ? and HouseholdRegistrationRteturnTime = now() where HouseholdRegistrationID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdRegistration.getHouseholdRegistrationID());
        return res;
    }


    public List getRecord(User user, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select a.* from HouseholdRegistration a left join user b on a.HouseholdRegistrationUserID = b.userid left join a.HouseholdRegistrationAdminID = c.adminid";
        List params=new ArrayList();
        if(user!=null&&user.getUserName()!=null){
            sql=sql+" where b.name like ? ";
            params.add("%"+user.getUserName()+"%");
        }
        if(admin!=null&&admin.getAdminname()!=null){
            if(!sql.contains("where")) sql=sql+" where ";
            sql=sql+" and c.adminname like ? ";
            params.add("%"+admin.getAdminname()+"%");
        }
        sql=sql+" order by status asc ";//鎺掑簭
        if(page!=null&&page.getPage()>0){
            sql=sql+" limit ?,? ";
            params.add(page.getStart());
            params.add(page.getLimit());

        }
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<HouseholdRegistration> list = JdbcUtil.convertResultSetToList(rs, HouseholdRegistration.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(User user, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select count(*) from HouseholdRegistration a left join user b on a.HouseholdRegistrationPersonID = b.userid left join book c on a.HouseholdRegistrationPersonID = c.PersonID ";
        List params = new ArrayList();
        if (user != null && user.getUserName() != null) {
            sql = sql + " where b.UserName like ? ";
            params.add("%" + user.getUserName() + "%");
        }
        if (admin != null &&admin.getAdminname() != null) {
            if (!sql.contains("where")) {
                sql = sql + " where ";
            }
            sql = sql + " and c.adminname like ?";
            params.add("%" + admin.getAdminname() + "%");
        }
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        rs.next();
        int count = rs.getInt(1);
        JdbcUtil.close(rs);
        return count;
    }
}
