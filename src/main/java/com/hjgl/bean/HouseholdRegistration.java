package com.hjgl.bean;

import java.util.Date;

public class HouseholdRegistration
{
    /**
     * - HouseholdRegistrationID: 主键，户口登记申请表编号 (INT)
     * - HouseholdRegistrationTime: 申请时间  (DATETIME)
     * - HouseholdRegistrationReturnTime: 审批时间  (DATETIME)
     * - HouseholdRegistrationStatus: 申请状态  (VARCHAR)
     * - HouseholdRegistrationHouseholdID: 外键，关联到户籍表 (INT)
     * - HouseholdRegistrationUserID: 外键，关联到用户表 (INT)
     * - HouseholdRegistrationAdminID: 外键，关联到管理员表 (INT)
     * - HouseholdRegistrationPersonID: 外键，关联到人员表 (INT)
     */

    private int householdregistrationid;
    private Date householdregistrationtime;
    private Date householdregistrationreturntime;
    private String householdregistrationstatus;
    private int householdregistrationhouseholdid;
    private int householdregistrationuserid;
    private int householdregistrationadminid;
    private int HouseholdRegistrationPersonID;


    private Person person;
    private User user;
    private Admin admin;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getHouseholdRegistrationPersonID() {
        return HouseholdRegistrationPersonID;
    }

    public void setHouseholdRegistrationPersonID(int householdRegistrationPersonID) {
        HouseholdRegistrationPersonID = householdRegistrationPersonID;
    }

    public Date getHouseholdregistrationreturntime() {
        return householdregistrationreturntime;
    }

    public void setHouseholdregistrationreturntime(Date householdregistrationreturntime) {
        this.householdregistrationreturntime = householdregistrationreturntime;
    }

    public int getHouseholdregistrationid() {
        return householdregistrationid;
    }

    public void setHouseholdregistrationid(int householdregistrationid) {
        this.householdregistrationid = householdregistrationid;
    }

    public Date getHouseholdregistrationtime() {
        return householdregistrationtime;
    }

    public void setHouseholdregistrationtime(Date householdregistrationtime) {
        this.householdregistrationtime = householdregistrationtime;
    }

    public String getHouseholdregistrationstatus() {
        return householdregistrationstatus;
    }

    public void setHouseholdregistrationstatus(String householdregistrationstatus) {
        this.householdregistrationstatus = householdregistrationstatus;
    }

    public int getHouseholdregistrationhouseholdid() {
        return householdregistrationhouseholdid;
    }

    public void setHouseholdregistrationhouseholdid(int householdregistrationhouseholdid) {
        this.householdregistrationhouseholdid = householdregistrationhouseholdid;
    }

    public int getHouseholdregistrationuserid() {
        return householdregistrationuserid;
    }

    public void setHouseholdregistrationuserid(int householdregistrationuserid) {
        this.householdregistrationuserid = householdregistrationuserid;
    }

    public int getHouseholdregistrationadminid() {
        return householdregistrationadminid;
    }

    public void setHouseholdregistrationadminid(int householdregistrationadminid) {
        this.householdregistrationadminid = householdregistrationadminid;
    }

}
