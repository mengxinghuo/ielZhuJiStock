package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class EntryDetail {
    private Integer id;

    private Integer entryId;

    private String customsClearance;

    private String destination;

    private String packageNo;

    private String serialNo;

    private String partsNo;

    private String partsName;

    private String partsEnName;

    private String unit;

    private Integer purchaseNum;

    private BigDecimal purchasePrice;

    private BigDecimal salesPrice;

    private String deviceType;

    private Integer entryPosition;

    private Integer inspectStatus;

    private Integer entryNum;

    private Date createTime;

    private Date updateTime;

    private Integer shipNum;

    private String buyContractNo;

    private String model;

    private String sn;

    private String engineNo;

    private String xxNo;

    private String brand;

    private String errorDescs;

    public EntryDetail(Integer id, Integer entryId, String customsClearance, String destination, String packageNo, String serialNo, String partsNo, String partsName, String partsEnName, String unit, Integer purchaseNum, BigDecimal purchasePrice, BigDecimal salesPrice, String deviceType, Integer entryPosition, Integer inspectStatus, Integer entryNum, Date createTime, Date updateTime, Integer shipNum, String buyContractNo, String model, String sn, String engineNo, String xxNo, String brand,String errorDescs) {
        this.id = id;
        this.entryId = entryId;
        this.customsClearance = customsClearance;
        this.destination = destination;
        this.packageNo = packageNo;
        this.serialNo = serialNo;
        this.partsNo = partsNo;
        this.partsName = partsName;
        this.partsEnName = partsEnName;
        this.unit = unit;
        this.purchaseNum = purchaseNum;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;
        this.deviceType = deviceType;
        this.entryPosition = entryPosition;
        this.inspectStatus = inspectStatus;
        this.entryNum = entryNum;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shipNum = shipNum;
        this.buyContractNo = buyContractNo;
        this.model = model;
        this.sn = sn;
        this.engineNo = engineNo;
        this.xxNo = xxNo;
        this.brand = brand;
        this.errorDescs = errorDescs;
    }

    public EntryDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getCustomsClearance() {
        return customsClearance;
    }

    public void setCustomsClearance(String customsClearance) {
        this.customsClearance = customsClearance == null ? null : customsClearance.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo == null ? null : packageNo.trim();
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo == null ? null : partsNo.trim();
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName == null ? null : partsName.trim();
    }

    public String getPartsEnName() {
        return partsEnName;
    }

    public void setPartsEnName(String partsEnName) {
        this.partsEnName = partsEnName == null ? null : partsEnName.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public Integer getEntryPosition() {
        return entryPosition;
    }

    public void setEntryPosition(Integer entryPosition) {
        this.entryPosition = entryPosition;
    }

    public Integer getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(Integer inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
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

    public Integer getShipNum() {
        return shipNum;
    }

    public void setShipNum(Integer shipNum) {
        this.shipNum = shipNum;
    }

    public String getBuyContractNo() {
        return buyContractNo;
    }

    public void setBuyContractNo(String buyContractNo) {
        this.buyContractNo = buyContractNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getXxNo() {
        return xxNo;
    }

    public void setXxNo(String xxNo) {
        this.xxNo = xxNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getErrorDescs() {
        return errorDescs;
    }

    public void setErrorDescs(String errorDescs) {
        this.errorDescs = errorDescs;
    }
}