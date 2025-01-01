package com.hjgl.bean;

import com.hjgl.util.Page;

import java.util.Date;

public class HouseholdCorrection
{
    /**
     * - HouseholdCorrectionID: 主键，户口信息更正申请表编号 (INT)
     * - HouseholdCorrectionTime: 申请时间  (DATETIME)
     * - HouseholdCorrectionStatus: 申请状态  (VARCHAR)
     * - HouseholdCorrectionAdminID: 外键，关联到管理员表 (INT)
     * - HouseholdCorrectionPersonID: 外键，关联到人口表 (INT)
     */
    private int HouseholdCorrectionID;
    private Date HouseholdCorrectionTime;
    private String HouseholdCorrectionStatus;
    private int HouseholdCorrectionAdminID;
    private int HouseholdCorrectionPersonID;

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

    public int getHouseholdCorrectionID() {
        return HouseholdCorrectionID;
    }

    public void setHouseholdCorrectionID(int householdCorrectionID) {
        HouseholdCorrectionID = householdCorrectionID;
    }

    public Date getHouseholdCorrectionTime() {
        return HouseholdCorrectionTime;
    }

    public void setHouseholdCorrectionTime(Date householdCorrectionTime) {
        HouseholdCorrectionTime = householdCorrectionTime;
    }

    public String getHouseholdCorrectionStatus() {
        return HouseholdCorrectionStatus;
    }

    public void setHouseholdCorrectionStatus(String householdCorrectionStatus) {
        HouseholdCorrectionStatus = householdCorrectionStatus;
    }

    public int getHouseholdCorrectionAdminID() {
        return HouseholdCorrectionAdminID;
    }

    public void setHouseholdCorrectionAdminID(int householdCorrectionAdminID) {
        HouseholdCorrectionAdminID = householdCorrectionAdminID;
    }

    public int getHouseholdCorrectionPersonID() {
        return HouseholdCorrectionPersonID;
    }

    public void setHouseholdCorrectionPersonID(int householdCorrectionPersonID) {
        HouseholdCorrectionPersonID = householdCorrectionPersonID;
    }

}
