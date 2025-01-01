package com.hjgl.bean;

public class User
{
    /**
     * - UserID: 主键，用户编号 (INT)
     * - UserName: 用户名  (VARCHAR)
     * - UserAccount: 账号  (VARCHAR)
     * - UserPassword: 密码  (VARCHAR)
     */

    private int UserID;
    private String UserName;
    private String UserAccount;
    private String UserPassword;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }


}
