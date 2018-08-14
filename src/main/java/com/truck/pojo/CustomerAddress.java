package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerAddress {
    private Integer addressId;

    private Integer customerId;

    private String address;

    private String addressAbbr;

    private Date createTime;

    private Date updateTime;

    private BigDecimal positionLongitude;

    private BigDecimal positionLatitude;

    public CustomerAddress(Integer addressId, Integer customerId, String address, String addressAbbr,
                           Date createTime, Date updateTime,BigDecimal positionLongitude,BigDecimal positionLatitude) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.addressAbbr = addressAbbr;
        this.positionLongitude = positionLongitude;
        this.positionLatitude = positionLatitude;
    }


    public String getAddressAbbr() {
        return addressAbbr;
    }

    public void setAddressAbbr(String addressAbbr) {
        this.addressAbbr = addressAbbr;
    }

    public CustomerAddress() {
        super();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public BigDecimal getPositionLongitude() {
        return positionLongitude;
    }

    public void setPositionLongitude(BigDecimal positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public BigDecimal getPositionLatitude() {
        return positionLatitude;
    }

    public void setPositionLatitude(BigDecimal positionLatitude) {
        this.positionLatitude = positionLatitude;
    }
}