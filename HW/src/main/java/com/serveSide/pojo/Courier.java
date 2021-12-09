package com.serveSide.pojo;


public class Courier {
    private int ID;
    private String name;
    private String userName;
    private String password;
    private String phoneNum;

    public Courier(){

    }

    public Courier(String name, String userName, String password, String phoneNum) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
