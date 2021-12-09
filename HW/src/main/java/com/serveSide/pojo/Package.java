package com.serveSide.pojo;

import java.util.Date;

public class Package {

    // 订单号 主键
    private int orderNo;
    // 订单创建时间
    private Date time;
    // 快递员ID  外键
    private int courierID;
    private String courierName;
    private String courierPhoneNum;
    private String customerName;
    private String customerPhoneNum;
    private String customerAddress;

    public Package(){

    }

    public Package(int orderNo, Date time, int courierID, String courierName, String courierPhoneNum, String customerName, String customerPhoneNum, String customerAddress) {
        this.orderNo = orderNo;
        this.time = time;
        this.courierID = courierID;
        this.courierName = courierName;
        this.courierPhoneNum = courierPhoneNum;
        this.customerName = customerName;
        this.customerPhoneNum = customerPhoneNum;
        this.customerAddress = customerAddress;
    }

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierPhoneNum() {
        return courierPhoneNum;
    }

    public void setCourierPhoneNum(String courierPhoneNum) {
        this.courierPhoneNum = courierPhoneNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

    public void setCustomerPhoneNum(String customerPhoneNum) {
        this.customerPhoneNum = customerPhoneNum;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Package{" +
                "orderNo=" + orderNo +
                ", time=" + time +
                ", courierID=" + courierID +
                ", courierName='" + courierName + '\'' +
                ", courierPhoneNum='" + courierPhoneNum + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNum='" + customerPhoneNum + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
}
