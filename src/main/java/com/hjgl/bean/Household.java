package com.hjgl.bean;

public class Household {
    /**
     * - HouseholdID: 主键，户籍编号 (INT)
     * - HouseholdAddress: 户籍地址  (VARCHAR)
     * - HouserholderName: 户主姓名  (VARCHAR)
     * - HouseholdPopulation: 户籍人口数  (INT)
     * - HouseholdPersonID: 外键，关联到人口表 (INT)
     */
    private int householdid;
    private String householdaddress;
    private String householdername;
    private int householdpopulation;
    private int householdpersonid;
    private int isdelete;

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getHouseholdid() {
        return householdid;
    }

    public void setHouseholdid(int householdid) {
        this.householdid = householdid;
    }

    public String getHouseholdaddress() {
        return householdaddress;
    }

    public void setHouseholdaddress(String householdaddress) {
        this.householdaddress = householdaddress;
    }

    public String getHouseholdername() {
        return householdername;
    }

    public void setHouseholdername(String householdername) {
        this.householdername = householdername;
    }

    public int getHouseholdpopulation() {
        return householdpopulation;
    }

    public void setHouseholdpopulation(int householdpopulation) {
        this.householdpopulation = householdpopulation;
    }

    public int getHouseholdpersonid() {
        return householdpersonid;
    }

    public void setHouseholdpersonid(int householdpersonid) {
        this.householdpersonid = householdpersonid;
    }

}
