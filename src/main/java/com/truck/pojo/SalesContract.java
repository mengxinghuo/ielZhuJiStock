package com.truck.pojo;

import java.util.Date;

public class SalesContract {
    private Integer salesContractId;

    private Integer customerId;

    private Integer addressId;

    private Integer contactId;

    private Date date;

    private Integer outId;

    private String outNo;

    private String bpkNo;

    private String salesContractNo;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public SalesContract(Integer salesContractId, Integer customerId, Integer addressId, Integer contactId, Date date, Integer outId, String outNo, String bpkNo, String salesContractNo, Integer type, Integer status, Date createTime, Date updateTime) {
        this.salesContractId = salesContractId;
        this.customerId = customerId;
        this.addressId = addressId;
        this.contactId = contactId;
        this.date = date;
        this.outId = outId;
        this.outNo = outNo;
        this.bpkNo = bpkNo;
        this.salesContractNo = salesContractNo;
        this.type = type;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public SalesContract() {
        super();
    }

    public Integer getSalesContractId() {
        return salesContractId;
    }

    public void setSalesContractId(Integer salesContractId) {
        this.salesContractId = salesContractId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo == null ? null : outNo.trim();
    }

    public String getBpkNo() {
        return bpkNo;
    }

    public void setBpkNo(String bpkNo) {
        this.bpkNo = bpkNo == null ? null : bpkNo.trim();
    }

    public String getSalesContractNo() {
        return salesContractNo;
    }

    public void setSalesContractNo(String salesContractNo) {
        this.salesContractNo = salesContractNo == null ? null : salesContractNo.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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