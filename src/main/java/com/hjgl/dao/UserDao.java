package com.hjgl.dao;

import com.hjgl.bean.User;
import com.hjgl.util.JdbcUtil;
import com.hjgl.util.Page;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    public User getUserByUserid(int userid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from user where userid=?";
        ResultSet rs=JdbcUtil.query(sql,userid);
        List<User> list=JdbcUtil.convertResultSetToList(rs,User.class);
        User res=list.get(0);
        JdbcUtil.close(rs);
        return  res;
    }

    public List<User> getlist(Page page,String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from user ";
        List list=new ArrayList();
        if (checktext!=null){
            sql=sql+" where username like ? ";
            list.add("%"+checktext+"%");
        }
        if (page!=null){
            sql=sql+" limit ?,? ";
            list.add(page.getStart());
            list.get(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql, list.toArray());
        List<User> list1=JdbcUtil.convertResultSetToList(rs,User.class);
        JdbcUtil.close(rs);
        return list1;
    }

    public int getCount(String checktext) throws SQLException {
        //聚合函数，用于计算满足查询条件的记录数
        String sql="select count(*) from user ";
        if(checktext!=null){
            sql=sql+"where username like ? ";
            String find="%"+checktext+"%";
            ResultSet rs=JdbcUtil.query(sql,find);
            int cnt=0;
            if(rs.next()){
                cnt =rs.getInt(1);
            }
            JdbcUtil.close(rs);
            return cnt;
        }
        ResultSet rs=JdbcUtil.query(sql);
        rs.next();
        int cnt=rs.getInt(1);
        JdbcUtil.close(rs);
        return cnt;
    }
    // 增加
    public int add(User user) {
        String sql="insert into user (username,useraccount,userpasword) values(?, ?, ?)";
        int result=JdbcUtil.update(sql,user.getUserName(),user.getUserAccount(),user.getUserPassword());
        return result;
    }

    // 编辑
    public int edit(User user) {
        String sql="update user set username=?,useraccount=?,userpassword=? where userid=?";
        int result=JdbcUtil.update(sql,user.getUserName(),user.getUserAccount(),user.getUserPassword(),user.getUserID());
        return result;
    }

    // 删除
    public int delete(User user) {
        String sql="delete from user where userid=?";
        int result=JdbcUtil.update(sql,user.getUserID());
        return result;
    }

}
