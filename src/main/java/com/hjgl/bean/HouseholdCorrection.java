package com.hjgl.bean;

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
    private int householdcorrectionid;
    private Date householdcorrectiontime;
    private String householdcorrectionstatus;
    private int householdcorrectionadminid;
    private int householdcorrectionpersonid;

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

    public int getHouseholdcorrectionid() {
        return householdcorrectionid;
    }

    public void setHouseholdcorrectionid(int householdcorrectionid) {
        this.householdcorrectionid = householdcorrectionid;
    }

    public Date getHouseholdcorrectiontime() {
        return householdcorrectiontime;
    }

    public void setHouseholdcorrectiontime(Date householdcorrectiontime) {
        this.householdcorrectiontime = householdcorrectiontime;
    }

    public String getHouseholdcorrectionstatus() {
        return householdcorrectionstatus;
    }

    public void setHouseholdcorrectionstatus(String householdcorrectionstatus) {
        this.householdcorrectionstatus = householdcorrectionstatus;
    }

    public int getHouseholdcorrectionadminid() {
        return householdcorrectionadminid;
    }

    public void setHouseholdcorrectionadminid(int householdcorrectionadminid) {
        this.householdcorrectionadminid = householdcorrectionadminid;
    }

    public int getHouseholdcorrectionpersonid() {
        return householdcorrectionpersonid;
    }

    public void setHouseholdcorrectionpersonid(int householdcorrectionpersonid) {
        this.householdcorrectionpersonid = householdcorrectionpersonid;
    }

}
