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
    private int HouseholdCancellationID;
    private Date HouseholdCancellationTime;
    private Date HouseholdCancellationRteturnTime;
    private String HouseholdCancellationStatus;
    private int HouseholdCancellationHouseholdID;
    private int HouseholdCancellationUserID;
    private int HouseholdCancellationAdminID;

    private Person person;
    private User user;
    private Admin admin;

    public Date getHouseholdCancellationRteturnTime() {
        return HouseholdCancellationRteturnTime;
    }

    public void setHouseholdCancellationRteturnTime(Date householdCancellationRteturnTime) {
        HouseholdCancellationRteturnTime = householdCancellationRteturnTime;
    }

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

    public int getHouseholdCancellationID() {
        return HouseholdCancellationID;
    }

    public void setHouseholdCancellationID(int householdCancellationID) {
        HouseholdCancellationID = householdCancellationID;
    }

    public Date getHouseholdCancellationTime() {
        return HouseholdCancellationTime;
    }

    public void setHouseholdCancellationTime(Date householdCancellationTime) {
        HouseholdCancellationTime = householdCancellationTime;
    }

    public String getHouseholdCancellationStatus() {
        return HouseholdCancellationStatus;
    }

    public void setHouseholdCancellationStatus(String householdCancellationStatus) {
        HouseholdCancellationStatus = householdCancellationStatus;
    }

    public int getHouseholdCancellationHouseholdID() {
        return HouseholdCancellationHouseholdID;
    }

    public void setHouseholdCancellationHouseholdID(int householdCancellationHouseholdID) {
        HouseholdCancellationHouseholdID = householdCancellationHouseholdID;
    }

    public int getHouseholdCancellationUserID() {
        return HouseholdCancellationUserID;
    }

    public void setHouseholdCancellationUserID(int householdCancellationUserID) {
        HouseholdCancellationUserID = householdCancellationUserID;
    }

    public int getHouseholdCancellationAdminID() {
        return HouseholdCancellationAdminID;
    }

    public void setHouseholdCancellationAdminID(int householdCancellationAdminID) {
        HouseholdCancellationAdminID = householdCancellationAdminID;
    }



}
