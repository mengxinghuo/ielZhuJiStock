package com.truck.pojo;

import java.util.Date;

public class Transport {
    private Integer id;

    private String declareNum;

    private String destination;

    private String arrivalList;

    private String purchaseList;

    private String salesContract;

    private String invoice;

    private String purchaseContract;

    private String exportCost;

    private String salesList;

    private String entranceCost;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String shipNum;

    private String zhuJiSalesList;

    private String createTimeStr;

    public Transport(Integer id, String declareNum, String destination, String arrivalList, String purchaseList, String salesContract,
                     String invoice, String purchaseContract, String exportCost, String salesList, String entranceCost, Integer status,
                     Date createTime, Date updateTime,String shipNum,String zhuJiSalesList) {
        this.id = id;
        this.declareNum = declareNum;
        this.destination = destination;
        this.arrivalList = arrivalList;
        this.purchaseList = purchaseList;
        this.salesContract = salesContract;
        this.invoice = invoice;
        this.purchaseContract = purchaseContract;
        this.exportCost = exportCost;
        this.salesList = salesList;
        this.entranceCost = entranceCost;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shipNum = shipNum;
        this.zhuJiSalesList = zhuJiSalesList;
    }

    public Transport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(String declareNum) {
        this.declareNum = declareNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getArrivalList() {
        return arrivalList;
    }

    public void setArrivalList(String arrivalList) {
        this.arrivalList = arrivalList == null ? null : arrivalList.trim();
    }

    public String getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(String purchaseList) {
        this.purchaseList = purchaseList == null ? null : purchaseList.trim();
    }

    public String getSalesContract() {
        return salesContract;
    }

    public void setSalesContract(String salesContract) {
        this.salesContract = salesContract == null ? null : salesContract.trim();
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public String getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract == null ? null : purchaseContract.trim();
    }

    public String getExportCost() {
        return exportCost;
    }

    public void setExportCost(String exportCost) {
        this.exportCost = exportCost == null ? null : exportCost.trim();
    }

    public String getSalesList() {
        return salesList;
    }

    public void setSalesList(String salesList) {
        this.salesList = salesList == null ? null : salesList.trim();
    }

    public String getEntranceCost() {
        return entranceCost;
    }

    public void setEntranceCost(String entranceCost) {
        this.entranceCost = entranceCost == null ? null : entranceCost.trim();
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

    public String getShipNum() {
        return shipNum;
    }

    public void setShipNum(String shipNum) {
        this.shipNum = shipNum;
    }

    public String getZhuJiSalesList() {
        return zhuJiSalesList;
    }

    public void setZhuJiSalesList(String zhuJiSalesList) {
        this.zhuJiSalesList = zhuJiSalesList;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", declareNum='" + declareNum + '\'' +
                ", destination='" + destination + '\'' +
                ", arrivalList='" + arrivalList + '\'' +
                ", purchaseList='" + purchaseList + '\'' +
                ", salesContract='" + salesContract + '\'' +
                ", invoice='" + invoice + '\'' +
                ", purchaseContract='" + purchaseContract + '\'' +
                ", exportCost='" + exportCost + '\'' +
                ", salesList='" + salesList + '\'' +
                ", entranceCost='" + entranceCost + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", shipNum='" + shipNum + '\'' +
                ", zhuJiSalesList='" + zhuJiSalesList + '\'' +
                '}';
    }
}