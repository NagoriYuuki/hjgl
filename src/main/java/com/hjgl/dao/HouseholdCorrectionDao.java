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
    public int agree(HouseholdCorrection householdCorrection) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="update HouseholdCorrection set HouseholdCorrectionStatus = ? where HouseholdCorrectionID = ? ";
        int res = JdbcUtil.update(sql,"已同意",householdCorrection.getHouseholdcorrectionid());
        return res;
    }

    @OperationLog(description = "拒绝")
    public int refuse(HouseholdCorrection householdCorrection) {
        String sql = "update HouseholdCorrection set HouseholdCorrectionStatus = ? where HouseholdCorrectionID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdCorrection.getHouseholdcorrectionid());
        return res;
    }


    public List getRecord(User user, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdCorrection ";
        System.out.println(user);
        List params=new ArrayList();
        System.out.println(sql);
        ResultSet rs = JdbcUtil.query(sql, params.toArray());

        List<HouseholdCorrection> list = JdbcUtil.convertResultSetToList(rs, HouseholdCorrection.class);

        JdbcUtil.close(rs);
//        System.out.println(list.size());
        return list;
    }

    public int getCount(User user, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdCorrection ";
        List params = new ArrayList();
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        int count=0;
        while (rs.next()){
            count++;
        }
        JdbcUtil.close(rs);
        return count;
    }

    public int delete(HouseholdCorrection householdCorrection){
        String sql="delete from householdCorrection where householdCorrectionID=?";
        int res=JdbcUtil.update(sql,householdCorrection.getHouseholdcorrectionid());
        return res;
    }

}
