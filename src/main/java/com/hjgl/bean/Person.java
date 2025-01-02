package com.hjgl.bean;

import org.springframework.format.annotation.DateTimeFormat;

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
    private int personid;
    private String personname;
    private String persongender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date personbirthday;
    private String personidcardnumber;
    private int personhouseholdid;
    private int isdelete;

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getPersongender() {
        return persongender;
    }

    public void setPersongender(String persongender) {
        this.persongender = persongender;
    }

    public Date getPersonbirthday() {
        return personbirthday;
    }

    public void setPersonbirthday(Date personbirthday) {
        this.personbirthday = personbirthday;
    }

    public String getPersonidcardnumber() {
        return personidcardnumber;
    }

    public void setPersonidcardnumber(String personidcardnumber) {
        this.personidcardnumber = personidcardnumber;
    }

    public int getPersonhouseholdid() {
        return personhouseholdid;
    }

    public void setPersonhouseholdid(int personhouseholdid) {
        this.personhouseholdid = personhouseholdid;
    }


}
