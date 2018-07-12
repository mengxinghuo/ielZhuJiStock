package com.truck.pojo;

import java.util.Date;

public class Customer {
    private Integer customerId;

    private String customerNo;

    private String ptName;

    private String customerLevel;

    private String business;

    private String area;

    private String city;

    private String phoneNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Customer(Integer customerId, String customerNo, String ptName, String customerLevel, String business, String area, String city, String phoneNumber, Integer status, Date createTime, Date updateTime) {
        this.customerId = customerId;
        this.customerNo = customerNo;
        this.ptName = ptName;
        this.customerLevel = customerLevel;
        this.business = business;
        this.area = area;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Customer() {
        super();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName == null ? null : ptName.trim();
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel == null ? null : customerLevel.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}