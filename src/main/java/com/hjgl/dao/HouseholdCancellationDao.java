package com.hjgl.dao;

import com.hjgl.bean.*;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.OperationLog;
import com.hjgl.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    HouseholdDao householdDao;
    @OperationLog(description = "同意")
    public int agree(HouseholdCancellation householdCancellation) throws SQLException, IllegalAccessException, InstantiationException {
        System.out.println("houseid:-----> "+ householdCancellation.getHouseholdcancellationhouseholdid());
        System.out.println("adminid:-----> "+ householdCancellation.getHouseholdcancellationadminid());
        // 修正 SQL 语法，使用逗号分隔 SET 子句
        String sql = "UPDATE HouseholdCancellation SET HouseholdCancellationRteturnTime = NOW(), HouseholdCancellationStatus = ? WHERE HouseholdCancellationID = ?";
        int res = JdbcUtil.update(sql, "已同意", householdCancellation.getHouseholdcancellationid());

        // 获取关联的 Household 记录
        String sql1 = "SELECT * FROM Household WHERE Householdid = ?";
        ResultSet rs = JdbcUtil.query(sql1, householdCancellation.getHouseholdcancellationhouseholdid());
        List<Household> list = JdbcUtil.convertResultSetToList(rs, Household.class);
        JdbcUtil.close(rs);

        if (list.size() != 0) {
            Household household = list.get(0);
            household.setIsdelete(1); // 设置 isdelete 为 1
            householdDao.delete(household); // 假设有一个更新 Household 的方法
        }

        return res;
    }

    @OperationLog(description = "拒绝")
    public int refuse(HouseholdCancellation householdCancellation) {
        String sql = "update HouseholdCancellation set HouseholdCancellationStatus = ? and HouseholdCancellationRteturnTime = now() where HouseholdCancellationID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdCancellation.getHouseholdcancellationid());
        return res;
    }


    public List getRecord(User user, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdCancellation ";
        System.out.println(user);
        List params=new ArrayList();
        System.out.println(sql);
        ResultSet rs = JdbcUtil.query(sql, params.toArray());

        List<HouseholdCancellation> list = JdbcUtil.convertResultSetToList(rs, HouseholdCancellation.class);

        JdbcUtil.close(rs);
//        System.out.println(list.size());
        return list;
    }

    public int getCount(User user, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdCancellation ";
        List params = new ArrayList();
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        int count=0;
        while (rs.next()){
            count++;
        }
        JdbcUtil.close(rs);
        return count;
    }


    //删除

    public int delete(HouseholdCancellation householdCancellation){
        String sql="delete from householdcancellation where HouseholdCancellationID=?";

        int res=JdbcUtil.update(sql,householdCancellation.getHouseholdcancellationid());

        return res;
    }



}
