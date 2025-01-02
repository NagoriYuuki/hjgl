package com.hjgl.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("householdmigrationid")
    private int householdmigrationid;

    @JsonProperty("householdmigrationtime")
    private Date householdmigrationtime;

    @JsonProperty("householdmigrationstatus")
    private String householdmigrationstatus;

    @JsonProperty("householdmigrationoriginalhouseholdid")
    private int householdmigrationoriginalhouseholdid;

    @JsonProperty("householdmigrationuserid")
    private int householdmigrationuserid;

    @JsonProperty("householdmigrationadminid")
    private int householdmigrationadminid;

    @JsonProperty("householdmigrationnewhouseholdid")
    private int householdmigrationnewhouseholdid;
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



    public int getHouseholdmigrationid() {
        return householdmigrationid;
    }

    public void setHouseholdmigrationid(int householdmigrationid) {
        this.householdmigrationid = householdmigrationid;
    }

    public Date getHouseholdmigrationtime() {
        return householdmigrationtime;
    }

    public void setHouseholdmigrationtime(Date householdmigrationtime) {
        this.householdmigrationtime = householdmigrationtime;
    }

    public String getHouseholdmigrationstatus() {
        return householdmigrationstatus;
    }

    public void setHouseholdmigrationstatus(String householdmigrationstatus) {
        this.householdmigrationstatus = householdmigrationstatus;
    }

    public int getHouseholdmigrationoriginalhouseholdid() {
        return householdmigrationoriginalhouseholdid;
    }

    public void setHouseholdmigrationoriginalhouseholdid(int householdmigrationoriginalhouseholdid) {
        this.householdmigrationoriginalhouseholdid = householdmigrationoriginalhouseholdid;
    }

    public int getHouseholdmigrationuserid() {
        return householdmigrationuserid;
    }

    public void setHouseholdmigrationuserid(int householdmigrationuserid) {
        this.householdmigrationuserid = householdmigrationuserid;
    }

    public int getHouseholdmigrationadminid() {
        return householdmigrationadminid;
    }

    public void setHouseholdmigrationadminid(int householdmigrationadminid) {
        this.householdmigrationadminid = householdmigrationadminid;
    }

    public int getHouseholdmigrationnewhouseholdid() {
        return householdmigrationnewhouseholdid;
    }

    public void setHouseholdmigrationnewhouseholdid(int householdmigrationnewhouseholdid) {
        this.householdmigrationnewhouseholdid = householdmigrationnewhouseholdid;
    }

    @Override
    public String toString() {
        return "HouseholdMigration{" +
                "householdmigrationid=" + householdmigrationid +
                ", householdmigrationtime=" + householdmigrationtime +
                ", householdmigrationstatus='" + householdmigrationstatus + '\'' +
                ", householdmigrationoriginalhouseholdid=" + householdmigrationoriginalhouseholdid +
                ", householdmigrationuserid=" + householdmigrationuserid +
                ", householdmigrationadminid=" + householdmigrationadminid +
                ", householdmigrationnewhouseholdid=" + householdmigrationnewhouseholdid +
                ", person=" + person +
                ", user=" + user +
                ", admin=" + admin +
                '}';
    }
}
