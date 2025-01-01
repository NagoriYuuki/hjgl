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
 * - HouseholdCancellationID: 主键，户口注销申请表编号 (INT)
 * - HouseholdCancellationTime: 申请时间  (DATETIME)
 * - HouseholdCancellationRteturnTime: 批阅时间  (DATETIME)
 * - HouseholdCancellationStatus: 申请状态  (VARCHAR)
 * - HouseholdCancellationHouseholdID: 外键，关联到户籍表 (INT)
 * - HouseholdCancellationUserID: 外键，关联到用户表 (INT)
 * - HouseholdCancellationAdminID: 外键，关联到管理员表 (INT)
 */
@Repository
public class HouseholdCancellationDao {
    @OperationLog(description = "同意")
    public int agree(HouseholdCancellation householdCancellation ,HouseholdDao householdDao) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="update HouseholdCancellation set HouseholdCancellationRteturnTime = now() and HouseholdCancellationStatus = ? where HouseholdCancellationID = ? ";
        int res = JdbcUtil.update(sql,"agree",householdCancellation.getHouseholdCancellationID());
        String sql1= "select * from Household where Householdid = ?";
        ResultSet rs = JdbcUtil.query(sql1,householdCancellation.getHouseholdCancellationHouseholdID());
        List<Household> list=JdbcUtil.convertResultSetToList(rs, Household.class);
        Household household=list.get(0);
        householdDao.delete(household);
        return res;
    }

    @OperationLog(description = "拒绝")
    public int refuse(HouseholdCancellation householdCancellation) {
        String sql = "update HouseholdCancellation set HouseholdCancellationStatus = ? and HouseholdCancellationRteturnTime = now() where HouseholdCancellationID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdCancellation.getHouseholdCancellationID());
        return res;
    }


    public List getRecord(User user, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select a.* from HouseholdCancellation a left join user b on a.HouseholdCancellationUserID = b.userid left join a.HouseholdCancellationAdminID = c.adminid";
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
        sql=sql+" order by status asc ";//排序
        if(page!=null&&page.getPage()>0){
            sql=sql+" limit ?,? ";
            params.add(page.getStart());
            params.add(page.getLimit());

        }
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<HouseholdCancellation> list = JdbcUtil.convertResultSetToList(rs, HouseholdCancellation.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(User user, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select count(*) from HouseholdCancellation a left join user b on a.HouseholdCancellationPersonID = b.userid left join book c on a.HouseholdCancellationPersonID = c.PersonID ";
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
