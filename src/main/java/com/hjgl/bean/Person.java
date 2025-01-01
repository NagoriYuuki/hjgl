package com.hjgl.bean;

import java.util.Date;

public class Person
{
    /**
     * - PersonID: 主键，人口编号 (INT)
     * - PersonName: 姓名  (VARCHAR)
     * - PersonGender: 性别    (VARCHAR)
     * - PersonBitrhday: 出生日期  (DATE)
     * - PersonIDCardNumber: 身份证号  (VARCHAR)
     * - PersonHouseholdID: 外键，关联到户籍表 (INT)
     */
    private int PersonID;
    private String PersonName;
    private String PersonGender;
    private Date PersonBirthday;
    private String PersonIDCardNumber;
    private int PersonHouseholdID;

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPersonGender() {
        return PersonGender;
    }

    public void setPersonGender(String personGender) {
        PersonGender = personGender;
    }

    public Date getPersonBirthday() {
        return PersonBirthday;
    }

    public void setPersonBirthday(Date personBirthday) {
        PersonBirthday = personBirthday;
    }

    public String getPersonIDCardNumber() {
        return PersonIDCardNumber;
    }

    public void setPersonIDCardNumber(String personIDCardNumber) {
        PersonIDCardNumber = personIDCardNumber;
    }

    public int getPersonHouseholdID() {
        return PersonHouseholdID;
    }

    public void setPersonHouseholdID(int personHouseholdID) {
        PersonHouseholdID = personHouseholdID;
    }


}
