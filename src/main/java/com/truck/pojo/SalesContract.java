package com.truck.pojo;

import java.util.Date;

public class SalesContract {
    private Integer salesContractId;

    private Integer customerId;

    private Integer addressId;

    private Integer contactId;

    private Date date;
    private String salesDate;

    private Integer outId;

    private String outNo;

    private String bpkNo;

    private String salesContractNo;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String accessory1;

    private String accessory2;

    private String accessory3;

    public SalesContract(Integer salesContractId, Integer customerId, Integer addressId, Integer contactId, Date date,
                          Integer outId, String outNo, String bpkNo, String salesContractNo, Integer type,
                         Integer status, Date createTime, Date updateTime, String accessory1, String accessory2, String accessory3) {
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
        this.accessory1 = accessory1;
        this.accessory2 = accessory2;
        this.accessory3 = accessory3;
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

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
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
        this.outNo = outNo;
    }

    public String getBpkNo() {
        return bpkNo;
    }

    public void setBpkNo(String bpkNo) {
        this.bpkNo = bpkNo;
    }

    public String getSalesContractNo() {
        return salesContractNo;
    }

    public void setSalesContractNo(String salesContractNo) {
        this.salesContractNo = salesContractNo;
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

    public String getAccessory1() {
        return accessory1;
    }

    public void setAccessory1(String accessory1) {
        this.accessory1 = accessory1;
    }

    public String getAccessory2() {
        return accessory2;
    }

    public void setAccessory2(String accessory2) {
        this.accessory2 = accessory2;
    }

    public String getAccessory3() {
        return accessory3;
    }

    public void setAccessory3(String accessory3) {
        this.accessory3 = accessory3;
    }
}