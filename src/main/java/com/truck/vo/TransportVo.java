package com.truck.vo;

import com.truck.pojo.Transport;

import java.util.List;

public class TransportVo {
    private Integer id;

    private Integer declareNum;

    private String destination;

    private List arrivalList;

    private List purchaseList;

    private List salesContract;

    private List invoice;

    private List purchaseContract;

    private List exportCost;

    private List salesList;

    private List entranceCost;

    private Integer status;
    private String statusDesc;

    private String createTime;

    private String updateTime;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(Integer declareNum) {
        this.declareNum = declareNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List getArrivalList() {
        return arrivalList;
    }

    public void setArrivalList(List arrivalList) {
        this.arrivalList = arrivalList;
    }

    public List getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List purchaseList) {
        this.purchaseList = purchaseList;
    }

    public List getSalesContract() {
        return salesContract;
    }

    public void setSalesContract(List salesContract) {
        this.salesContract = salesContract;
    }

    public List getInvoice() {
        return invoice;
    }

    public void setInvoice(List invoice) {
        this.invoice = invoice;
    }

    public List getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(List purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

    public List getExportCost() {
        return exportCost;
    }

    public void setExportCost(List exportCost) {
        this.exportCost = exportCost;
    }

    public List getSalesList() {
        return salesList;
    }

    public void setSalesList(List salesList) {
        this.salesList = salesList;
    }

    public List getEntranceCost() {
        return entranceCost;
    }

    public void setEntranceCost(List entranceCost) {
        this.entranceCost = entranceCost;
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
}