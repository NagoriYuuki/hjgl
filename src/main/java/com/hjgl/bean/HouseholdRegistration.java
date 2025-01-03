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

    private int HouseholdRegistrationID;
    private Date HouseholdRegistrationTime;
    private Date HouseholdRegistrationReturnTime;
    private String HouseholdRegistrationStatus;
    private int HouseholdRegistrationHouseholdID;
    private int HouseholdRegistrationUserID;
    private int HouseholdRegistrationAdminID;
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

    public Date getHouseholdRegistrationReturnTime() {
        return HouseholdRegistrationReturnTime;
    }

    public void setHouseholdRegistrationReturnTime(Date householdRegistrationReturnTime) {
        HouseholdRegistrationReturnTime = householdRegistrationReturnTime;
    }

    public int getHouseholdRegistrationID() {
        return HouseholdRegistrationID;
    }

    public void setHouseholdRegistrationID(int householdRegistrationID) {
        HouseholdRegistrationID = householdRegistrationID;
    }

    public Date getHouseholdRegistrationTime() {
        return HouseholdRegistrationTime;
    }

    public void setHouseholdRegistrationTime(Date householdRegistrationTime) {
        HouseholdRegistrationTime = householdRegistrationTime;
    }

    public String getHouseholdRegistrationStatus() {
        return HouseholdRegistrationStatus;
    }

    public void setHouseholdRegistrationStatus(String householdRegistrationStatus) {
        HouseholdRegistrationStatus = householdRegistrationStatus;
    }

    public int getHouseholdRegistrationHouseholdID() {
        return HouseholdRegistrationHouseholdID;
    }

    public void setHouseholdRegistrationHouseholdID(int householdRegistrationHouseholdID) {
        HouseholdRegistrationHouseholdID = householdRegistrationHouseholdID;
    }

    public int getHouseholdRegistrationUserID() {
        return HouseholdRegistrationUserID;
    }

    public void setHouseholdRegistrationUserID(int householdRegistrationUserID) {
        HouseholdRegistrationUserID = householdRegistrationUserID;
    }

    public int getHouseholdRegistrationAdminID() {
        return HouseholdRegistrationAdminID;
    }

    public void setHouseholdRegistrationAdminID(int householdRegistrationAdminID) {
        HouseholdRegistrationAdminID = householdRegistrationAdminID;
    }

}
