package com.truck.vo;

import com.truck.pojo.Customer;
import com.truck.pojo.CustomerAddress;
import com.truck.pojo.CustomerContact;
import com.truck.pojo.OutDetail;

import java.util.Date;
import java.util.List;

public class SalesContractVo {
    private Integer salesContractId;

    private Integer customerId;
    private Customer customer;

    private Integer addressId;
    private CustomerAddress customerAddress;

    private Integer contactId;
    private CustomerContact customerContact;

    private String date;

    private Integer outId;
    private OutVo outVo;
    private List<OutDetail> outDetailList;

    private String outNo;

    private String bpkNo;

    private String salesContractNo;

    private Integer type;

    private Integer status;
    private String statusDesc;

    private String createTime;

    private String updateTime;

    private String accessory1;

    private String accessory2;

    private String accessory3;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public OutVo getOutVo() {
        return outVo;
    }

    public void setOutVo(OutVo outVo) {
        this.outVo = outVo;
    }

    public List<OutDetail> getOutDetailList() {
        return outDetailList;
    }

    public void setOutDetailList(List<OutDetail> outDetailList) {
        this.outDetailList = outDetailList;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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