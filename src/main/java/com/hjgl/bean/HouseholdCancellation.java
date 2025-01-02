package com.hjgl.bean;

import java.util.Date;

public class HouseholdCancellation
{
    /**
     * - HouseholdCancellationID: 主键，户口注销申请表编号 (INT)
     * - HouseholdCancellationTime: 申请时间  (DATETIME)
     * - HouseholdCancellationRteturnTime: 批阅时间  (DATETIME)
     * - HouseholdCancellationStatus: 申请状态  (VARCHAR)
     * - HouseholdCancellationHouseholdID: 外键，关联到户籍表 (INT)
     * - HouseholdCancellationUserID: 外键，关联到用户表 (INT)
     * - HouseholdCancellationAdminID: 外键，关联到管理员表 (INT)
     */

    private int householdcancellationid;
    private Date householdcancellationtime;
    private Date householdcancellationrteturntime;
    private String householdcancellationstatus;
    private int householdcancellationhouseholdid;

    private int householdcancellationuserid;
    private int householdcancellationadminid;

    private Person person;
    private User user;
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getHouseholdcancellationadminid() {
        return householdcancellationadminid;
    }

    public void setHouseholdcancellationadminid(int householdcancellationadminid) {
        this.householdcancellationadminid = householdcancellationadminid;
    }

    public int getHouseholdcancellationUserid() {
        return householdcancellationuserid;
    }

    public void setHouseholdcancellationUserid(int householdcancellationUserid) {
        this.householdcancellationuserid = householdcancellationUserid;
    }

    public int getHouseholdcancellationhouseholdid() {
        return householdcancellationhouseholdid;
    }

    public void setHouseholdcancellationhouseholdid(int householdcancellationhouseholdid) {
        this.householdcancellationhouseholdid = householdcancellationhouseholdid;
    }

    public String getHouseholdcancellationstatus() {
        return householdcancellationstatus;
    }

    public void setHouseholdcancellationstatus(String householdcancellationstatus) {
        this.householdcancellationstatus = householdcancellationstatus;
    }

    public Date getHouseholdcancellationrteturntime() {
        return householdcancellationrteturntime;
    }

    public void setHouseholdcancellationrteturntime(Date householdcancellationrteturntime) {
        this.householdcancellationrteturntime = householdcancellationrteturntime;
    }

    public Date getHouseholdcancellationtime() {
        return householdcancellationtime;
    }

    public void setHouseholdcancellationtime(Date householdcancellationtime) {
        this.householdcancellationtime = householdcancellationtime;
    }

    public int getHouseholdcancellationid() {
        return householdcancellationid;
    }

    public void setHouseholdcancellationid(int householdcancellationid) {
        this.householdcancellationid = householdcancellationid;
    }
}
