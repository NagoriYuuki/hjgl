package com.hjgl.dao;

import com.hjgl.bean.Household;
import com.hjgl.bean.Person;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.Page;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class HouseholdDao {

    public Household getPersonByHousehold(Person person ,Page page) throws SQLException, IllegalAccessException, InstantiationException {
        //查询人外接id=户籍id的人
        String sql="select * from household where HouseholdID = ?";
        List pa=new ArrayList();
        if(person!=null){
            pa.add(person.getPersonhouseholdid());
        }
        sql=sql+" order by status asc ";
        if(page!=null&&page.getPage()>0){
            sql=sql+" limit ?,? ";
            pa.add(page.getStart());
            pa.add(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql,pa.toArray());
        List<Household> list=JdbcUtil.convertResultSetToList(rs,Household.class);
        Household res=list.get(0);
        JdbcUtil.close(rs);
        return  res;
    }

    public Household getPersonByHouseholName(String householdname) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from person where Householdname = ?";
        ResultSet rs = JdbcUtil.query(sql, householdname);
        List<Household> list = JdbcUtil.convertResultSetToList(rs, Household.class);
        Household household= list.get(0);
        JdbcUtil.close(rs);
        return household;
    }


    public List<Household> getlist(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from Household ";
        List list = new ArrayList();
        if (checktext != null) {
            sql = sql + " where HouseholderName like ?";
            list.add("%" + checktext + "%");
        }
        if (sql.contains("where")) {
            sql = sql + " and isdelete=0 ";
        } else {
            sql = sql + " where isdelete=0 ";
        }
        if (page != null) {
            sql = sql + " limit ?,? ";
            list.add(page.getStart());
            list.add(page.getLimit());
        }
        ResultSet rs = JdbcUtil.query(sql, list.toArray());
        List<Household> per = JdbcUtil.convertResultSetToList(rs, Household.class);
        JdbcUtil.close(rs);
        return per;
    }

    public int getCount(String checktexxt) throws SQLException {
        String sql = "select count(*) from Household where isdelete = 0 ";
        if (checktexxt != null) {
            sql = sql + " and HouseholderName like ?";
            String find = "%" + checktexxt + "%";
            ResultSet rs = JdbcUtil.query(sql, find);
            int cnt = 0;
            if (rs.next()) {
                cnt = rs.getInt(1);

            }
            return cnt;
        }
        ResultSet rs = JdbcUtil.query(sql);
        rs.next();
        int cnt = rs.getInt(1);
        JdbcUtil.close(rs);
        return cnt;
    }
    /**
     * - HouseholdID: 主键，户籍编号 (INT)
     * - HouseholdAddress: 户籍地址  (VARCHAR)
     * - HouseholderName: 户主姓名  (VARCHAR)
     * - HouseholdPopulation: 户籍人口数  (INT)
     * - HouseholdPersonID: 外键，关联到人口表 (INT)
     */
    //增加
    public int add(Household hou) {
        System.out.println("check:");
        String sql = "INSERT INTO Household (HouseholdAddress, HouseholderName, HouseholdPopulation, HouseholdPersonID) VALUES (?, ?, ?, ?)";
        int res = JdbcUtil.update(sql,
                hou.getHouseholdaddress(),
                hou.getHouseholdername(),
                hou.getHouseholdpopulation(),
                hou.getHouseholdpersonid()
        );
        return res;
    }

    //删除

    public int delete(int id) {
        String sql = "update household set isdelete=1 where HouseholdID = ? ";
        int res = JdbcUtil.update(sql, id);
        return res;
    }

    public int delete(Household household) {
        String sql = "update household set isdelete=1 where HouseholdID = ? ";
        System.out.println(household.getHouseholdid());
        int res = JdbcUtil.update(sql, household.getHouseholdid());
        return res;
    }
    //修改

    public int edit(Household hou) {
        String sql = "update household set HouseholdAddress =? HouseholderName =? HouseholdPopulation =?  HouseholdPersonID =? ";
        int res = JdbcUtil.update(sql,hou.getHouseholdaddress(),hou.getHouseholdername(),hou.getHouseholdpopulation(),hou.getHouseholdpersonid() );
        return res;
    }
}
