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
public class HouseholdMigrationDao {
    @OperationLog(description = "已同意")
    public int agree(HouseholdMigration householdMigration) throws SQLException, IllegalAccessException, InstantiationException {
//        System.out.println("migration: "+householdMigration);
        String sql = "update HouseholdMigration set HouseholdMigrationStatus = ? where HouseholdMigrationID = ?";
        int res = JdbcUtil.update(sql, "已同意", householdMigration.getHouseholdmigrationid());
        return res;
    }


    @OperationLog(description = "拒绝")
    public int refuse(HouseholdMigration householdMigration) {
        String sql = "update HouseholdMigration set HouseholdMigrationStatus = ? where HouseholdMigrationID = ? ";
        int res = JdbcUtil.update(sql,"已拒绝", householdMigration.getHouseholdmigrationid());
        return res;
    }


    public List getRecord(Person person, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdMigration ";
        List params=new ArrayList();
//        System.out.println(sql);
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<HouseholdMigration> list = JdbcUtil.convertResultSetToList(rs, HouseholdMigration.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(Person person, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdMigration ";
        List params = new ArrayList();
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        int count=0;
        while (rs.next()){
            count++;
        }
        JdbcUtil.close(rs);
        return count;
    }
}
