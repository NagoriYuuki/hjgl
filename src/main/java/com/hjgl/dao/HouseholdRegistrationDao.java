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
    public int agree(HouseholdRegistration householdRegistration) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="update HouseholdRegistration set  HouseholdRegistrationStatus = ? where HouseholdRegistrationID = ? ";
        int res = JdbcUtil.update(sql,"agree",householdRegistration.getHouseholdregistrationid());
        return res;
    }

    @OperationLog(description = "拒绝")
    public int refuse(HouseholdRegistration householdRegistration) {
        String sql = "update HouseholdRegistration set HouseholdRegistrationStatus = ? where HouseholdRegistrationID = ? ";
        int res = JdbcUtil.update(sql,"refuse", householdRegistration.getHouseholdregistrationid());
        return res;
    }


    public List getRecord(Person person, Admin admin, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdRegistration ";
        List params=new ArrayList();
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<HouseholdRegistration> list = JdbcUtil.convertResultSetToList(rs, HouseholdRegistration.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(Person person, Admin admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from HouseholdRegistration ";
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
