package com.hjgl.dao;

import com.hjgl.bean.Admin;
import com.hjgl.bean.HouseholdMigration;
import com.hjgl.bean.Person;
import com.hjgl.bean.User;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.OperationLog;
import com.hjgl.util.Page;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HouseholdMigrationDao {
    @OperationLog(description = "")
    public int agree(HouseholdMigration householdMigration, PersonDao personDao) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="update HouseholdMigration set HouseholdMigrationRteturnTime = now() and HouseholdMigrationStatus = ? where HouseholdMigrationID = ? ";
        int res = JdbcUtil.update(sql,"agree",householdMigration.getHouseholdMigrationID());
        String sql1="select * from person where personid = ? ";
        ResultSet rs=JdbcUtil.query(sql1,householdMigration.getHouseholdMigrationNewHouseholdID());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        Person p=list.get(0);
        p.setPersonHouseholdID(householdMigration.getHouseholdMigrationNewHouseholdID());
        personDao.edit(p);
        return res;
    }

    @OperationLog(description = "")
    public int refuse(HouseholdMigration householdMigration) {
        String sql = "update HouseholdMigration set HouseholdMigrationStatus = ? and HouseholdMigrationRteturnTime = now() where HouseholdMigrationID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdMigration.getHouseholdMigrationID());
        return res;
    }


    public List getRecord(User user, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select a.* from HouseholdMigration a left join user b on a.HouseholdMigrationUserID = b.userid left join a.HouseholdMigrationAdminID = c.adminid";
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
        sql=sql+" order by status asc ";//閹烘帒绨?
        if(page!=null&&page.getPage()>0){
            sql=sql+" limit ?,? ";
            params.add(page.getStart());
            params.add(page.getLimit());

        }
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<HouseholdMigration> list = JdbcUtil.convertResultSetToList(rs, HouseholdMigration.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(User user, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select count(*) from HouseholdMigration a left join user b on a.HouseholdMigrationPersonID = b.userid left join book c on a.HouseholdMigrationPersonID = c.PersonID ";
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
