package com.serveSide.pojo;

import java.util.Date;

public class Manager {
    private String account;     // 管理员账号
    private String password;    // 管理员密码
    private String phoneNum;    // 管理员电话号码
    private Date lastLoginTime; // 上次登录时间
    private Date creationDate;  // 账号创建时间
    // 管理员权限    1 为最高权限
    private int power;

    public Manager(String account, String password, String phoneNum, int power){
        this.account = account;
        this.password = password;
        this.phoneNum = phoneNum;
        this.power = power;
    }

    public Manager(){

    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", creationDate=" + creationDate +
                ", power=" + power +
                '}';
    }
}
