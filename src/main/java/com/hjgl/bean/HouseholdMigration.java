package com.hjgl.bean;

import java.util.Date;

public class HouseholdMigration
{
    /**
     * - HouseholdMigrationID: 主键，户口迁移申请表编号 (INT)
     * - HouseholdMigrationTime: 申请时间  (DATETIME)
     * - HouseholdMigrationStatus: 申请状态  (VARCHAR)
     * - HouseholdMigrationRteturnTime: 批阅时间  (DATETIME)
     * - HouseholdMigrationOriginalHouseholdID: 原户籍，外键，关联到户籍表 (INT)
     * - HouseholdMigrationUserID: 外键，关联到用户表 (INT)
     * - HouseholdMigrationAdminID: 外键，关联到管理员表 (INT)
     * - HouseholdMigrationNewHouseholdID: 新户籍 外键，关联到户籍表 (INT)
     */

    private int HouseholdMigrationID;
    private Date HouseholdMigrationTime;
    private Date HouseholdMigrationRteturnTime;
    private String HouseholdMigrationStatus;
    private int HouseholdMigrationOriginalHouseholdID;
    private int HouseholdMigrationUserID;
    private int HouseholdMigrationAdminID;
    private int HouseholdMigrationNewHouseholdID;

    private Person person;
    private User user;
    private Admin admin;

    public Date getHouseholdMigrationRteturnTime() {
        return HouseholdMigrationRteturnTime;
    }

    public void setHouseholdMigrationRteturnTime(Date householdMigrationRteturnTime) {
        HouseholdMigrationRteturnTime = householdMigrationRteturnTime;
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



    public int getHouseholdMigrationID() {
        return HouseholdMigrationID;
    }

    public void setHouseholdMigrationID(int householdMigrationID) {
        HouseholdMigrationID = householdMigrationID;
    }

    public Date getHouseholdMigrationTime() {
        return HouseholdMigrationTime;
    }

    public void setHouseholdMigrationTime(Date householdMigrationTime) {
        HouseholdMigrationTime = householdMigrationTime;
    }

    public String getHouseholdMigrationStatus() {
        return HouseholdMigrationStatus;
    }

    public void setHouseholdMigrationStatus(String householdMigrationStatus) {
        HouseholdMigrationStatus = householdMigrationStatus;
    }

    public int getHouseholdMigrationOriginalHouseholdID() {
        return HouseholdMigrationOriginalHouseholdID;
    }

    public void setHouseholdMigrationOriginalHouseholdID(int householdMigrationOriginalHouseholdID) {
        HouseholdMigrationOriginalHouseholdID = householdMigrationOriginalHouseholdID;
    }

    public int getHouseholdMigrationUserID() {
        return HouseholdMigrationUserID;
    }

    public void setHouseholdMigrationUserID(int householdMigrationUserID) {
        HouseholdMigrationUserID = householdMigrationUserID;
    }

    public int getHouseholdMigrationAdminID() {
        return HouseholdMigrationAdminID;
    }

    public void setHouseholdMigrationAdminID(int householdMigrationAdminID) {
        HouseholdMigrationAdminID = householdMigrationAdminID;
    }

    public int getHouseholdMigrationNewHouseholdID() {
        return HouseholdMigrationNewHouseholdID;
    }

    public void setHouseholdMigrationNewHouseholdID(int householdMigrationNewHouseholdID) {
        HouseholdMigrationNewHouseholdID = householdMigrationNewHouseholdID;
    }


}
