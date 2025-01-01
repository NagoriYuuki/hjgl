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

/**
 * - HouseholdCorrectionID: 主键，户口信息更正申请表编号 (INT)
 * - HouseholdCorrectionTime: 申请时间  (DATETIME)
 * - HouseholdCorrectionStatus: 申请状态  (VARCHAR)
 * - HouseholdCorrectionAdminID: 外键，关联到管理员表 (INT)
 * - HouseholdCorrectionPersonID: 外键，关联到人口表 (INT)
 */

@Repository
public class HouseholdCorrectionDao {
    @OperationLog(description = "同意")
    public int agree(HouseholdCorrection householdCorrection,PersonDao personDao) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="update HouseholdCorrection set HouseholdCorrectionRteturnTime = now() and HouseholdCorrectionStatus = ? where HouseholdCorrectionID = ? ";
        int res = JdbcUtil.update(sql,"agree",householdCorrection.getHouseholdCorrectionID());
        String sql1= "select * from person where personid = ?";
        ResultSet rs=JdbcUtil.query(sql1,householdCorrection.getHouseholdCorrectionPersonID());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        Person person =list.get(0);
        personDao.edit(person);
        return res;
    }

    @OperationLog(description = "拒绝")
    public int refuse(HouseholdCorrection householdCorrection) {
        String sql = "update HouseholdCorrection set HouseholdCorrectionStatus = ? and HouseholdCorrectionRteturnTime = now() where HouseholdCorrectionID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdCorrection.getHouseholdCorrectionID());
        return res;
    }


    public List getRecord(User user, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select a.* from HouseholdCorrection a left join user b on a.HouseholdCorrectionUserID = b.userid left join a.HouseholdCorrectionAdminID = c.adminid";
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
        List<HouseholdCorrection> list = JdbcUtil.convertResultSetToList(rs, HouseholdCorrection.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(User user, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select count(*) from HouseholdCorrection a left join user b on a.HouseholdCorrectionPersonID = b.userid left join book c on a.HouseholdCorrectionPersonID = c.PersonID ";
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
