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
public class PersonDao {
    public Person getPersonByPersonid(int personid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from person where personid = ?";
        ResultSet rs= JdbcUtil.query(sql,personid);
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        Person person=list.get(0);
        JdbcUtil.close(rs);
        return person;
    }

    public List<Person> getHouselist(Household h, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String  sql="select * from person where isdelete = 0 ";
        List parms=new ArrayList();
        if(h!=null){
            sql=sql+" and PersonHouseholdID = ? ";
            parms.add(h.getHouseholdID());
        }
        if(page!=null){
            sql=sql+" limit ?,? ";
            parms.add(page.getStart());
            parms.add(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql,parms.toArray());
        List<Person> list=JdbcUtil.convertResultSetToList(rs,Person.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getHouseCount(Household h,Page page) throws SQLException {
        String sql="select count(*) from Person where isdelete = 0";
        List parms=new ArrayList();
        if(h!=null){
            sql=sql+" and PersonHouseholdID = ? ";
            parms.add(h.getHouseholdID());
        }
        if(page!=null){
            sql=sql+" limit ?,? ";
            parms.add(page.getStart());
            parms.add(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql,parms.toArray());
        rs.next();
        int count = rs.getInt(1);
        JdbcUtil.close(rs);
        return count;

    }

    //查询名称

    public List<Person> getlistname(Page page, String checktext ) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from person ";
        List list=new ArrayList();
        if (checktext != null && !checktext.isEmpty()) {
            sql = sql + " where personname like ?";
            list.add("%" + checktext + "%");
        }

        if(sql.contains("where")){
            sql=sql+" and isdelete=0 ";
        }else {
            sql=sql+" where isdelete=0 ";
        }
        if(page!=null){
            sql=sql+" limit ?,? ";
            list.add(page.getStart());
            list.add(page.getLimit());
        }
        System.out.println(sql);
        ResultSet rs=JdbcUtil.query(sql,list.toArray());

        List<Person> per= JdbcUtil.convertResultSetToList(rs,Person.class);
//        Person  p= per.get(0);

//        System.out.println(p.getPersonname()+"-----");

        JdbcUtil.close(rs);
        return per;
    }
    //查询
    public int getCountname(String checktexxt) throws SQLException {
        String sql="select count(*) from Person where isdelete = 0";
        System.out.println(checktexxt+"  -=======================");
        if(checktexxt!=null){
            sql=sql+" and personname like ?";
            String find="%"+checktexxt+"%";
            ResultSet rs=JdbcUtil.query(sql,find);

            int cnt=0;
            if(rs.next()){
                cnt=rs.getInt(1);

            }
            System.out.println("getccountname: "+cnt);
            return  cnt;
        }
        ResultSet rs=JdbcUtil.query(sql);
        rs.next();
        int cnt=rs.getInt(1);
        JdbcUtil.close(rs);
        return cnt;
    }
    /**
     * - PersonID: 主键，人口编号 (INT)
     * - PersonName: 姓名  (VARCHAR)
     * - PersonGender: 性别    (VARCHAR)
     * - PersonBitrhday: 出生日期  (DATE)
     * - PersonIDCardNumber: 身份证号  (VARCHAR)
     * - PersonHouseholdID: 外键，关联到户籍表 (INT)
     */
    //查询身份证
    public List<Person> getlistnumber(Page page, String checktext ) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from Person ";
        List list=new ArrayList();
        if(checktext!=null){
            sql=sql+" where PersonIDCardNumber like ?";
            list.add("%"+checktext+"%");
        }
        if(sql.contains("where")){
            sql=sql+" and isdelete=0 ";
        }else {
            sql=sql+" where isdelete=0 ";
        }
        if(page!=null){
            sql=sql+" limit ?,? ";
            list.add(page.getPage());
            list.add(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql,list.toArray());
        List<Person> per= JdbcUtil.convertResultSetToList(rs,Person.class);
        JdbcUtil.close(rs);
        return per;
    }

    public int getCountnumber(String checktexxt) throws SQLException {
        String sql="select count(*) from Person where isdelete = 0 ";
        if(checktexxt!=null){
            sql=sql+" and PersonIDCardNumber like ?";
            String find="%"+checktexxt+"%";
            ResultSet rs=JdbcUtil.query(sql,find);
            int cnt=0;
            if(rs.next()){
                cnt=rs.getInt(1);
            }
            return  cnt;
        }
        ResultSet rs=JdbcUtil.query(sql);
        rs.next();
        int cnt=rs.getInt(1);
        JdbcUtil.close(rs);
        return cnt;
    }

    //增加

    public int add(Person person) {
        System.out.println("householdid: " + person.getPersonhouseholdid());
        System.out.println(person.getPersongender());
        String sql = "INSERT INTO person (personname, persongender, personbirthday, personidcardnumber, personhouseholdid) VALUES(?,?,?,?,?)";
        int res = JdbcUtil.update(sql, person.getPersonname(), person.getPersongender(), person.getPersonbirthday(), person.getPersonidcardnumber(), person.getPersonhouseholdid());
        return res;
    }

    //删除

    public int delete(Person person){
        System.out.println("delete: " + person.getPersonid());
//        System.out.println(person.getPersonid());
        String sql="update person set isdelete=1 where personid =? ";
        int res=JdbcUtil.update(sql,person.getPersonid());
        return res;
    }

    //修改

    public int edit(Person person) {
        System.out.println(person.getPersonname());
        System.out.println("householdid: " + person.getPersonhouseholdid());
        System.out.println("personid: " + person.getPersonid());
        String sql = "update person set personname=?, persongender=?, personbirthday=?, personidcardnumber=?, personhouseholdid=? where personid = ?";
        int res = JdbcUtil.update(sql, person.getPersonname(), person.getPersongender(), person.getPersonbirthday(), person.getPersonidcardnumber(), person.getPersonhouseholdid(), person.getPersonid());
        return res;
    }


}
