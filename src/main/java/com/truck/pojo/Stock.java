package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {
    private Integer id;

    private Integer entryId;

    private String customsClearance;

    private String destination;

    private String partsNo;

    private String partsName;

    private String partsEnName;

    private String unit;

    private Integer quantity;

    private BigDecimal salesPrice;

    private String deviceType;

    private Integer repertory;

    private Integer position;

    private Date createTime;

    private Date updateTime;

    private String shipNum;

    private String buyContractNo;

    private String model;

    private String sn;

    private String engineNo;

    private String xxNo;

    private String brand;

    public Stock(Integer id, Integer entryId, String customsClearance, String destination, String partsNo, String partsName, String partsEnName, String unit, Integer quantity, BigDecimal salesPrice, String deviceType, Integer repertory, Integer position, Date createTime, Date updateTime, String shipNum, String buyContractNo, String model, String sn, String engineNo, String xxNo, String brand) {
        this.id = id;
        this.entryId = entryId;
        this.customsClearance = customsClearance;
        this.destination = destination;
        this.partsNo = partsNo;
        this.partsName = partsName;
        this.partsEnName = partsEnName;
        this.unit = unit;
        this.quantity = quantity;
        this.salesPrice = salesPrice;
        this.deviceType = deviceType;
        this.repertory = repertory;
        this.position = position;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shipNum = shipNum;
        this.buyContractNo = buyContractNo;
        this.model = model;
        this.sn = sn;
        this.engineNo = engineNo;
        this.xxNo = xxNo;
        this.brand = brand;
    }

    public Stock() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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
}