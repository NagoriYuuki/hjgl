package com.hjgl.bean;

public class Household {
    /**
     * - HouseholdID: 主键，户籍编号 (INT)
     * - HouseholdAddress: 户籍地址  (VARCHAR)
     * - HouserholderName: 户主姓名  (VARCHAR)
     * - HouseholdPopulation: 户籍人口数  (INT)
     * - HouseholdPersonID: 外键，关联到人口表 (INT)
     */
    private int HouseholdID;
    private String HouseholdAddress;
    private String HouserholderName;
    private int HouseholdPopulation;
    private int HouseholdPersonID;

    public int getHouseholdID() {
        return HouseholdID;
    }

    public void setHouseholdID(int householdID) {
        HouseholdID = householdID;
    }

    public String getHouseholdAddress() {
        return HouseholdAddress;
    }

    public void setHouseholdAddress(String householdAddress) {
        HouseholdAddress = householdAddress;
    }

    public String getHouserholderName() {
        return HouserholderName;
    }

    public void setHouserholderName(String houserholderName) {
        HouserholderName = houserholderName;
    }

    public int getHouseholdPopulation() {
        return HouseholdPopulation;
    }

    public void setHouseholdPopulation(int householdPopulation) {
        HouseholdPopulation = householdPopulation;
    }

    public int getHouseholdPersonID() {
        return HouseholdPersonID;
    }

    public void setHouseholdPersonID(int householdPersonID) {
        HouseholdPersonID = householdPersonID;
    }

}
