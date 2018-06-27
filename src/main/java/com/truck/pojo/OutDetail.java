package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OutDetail {
    private Integer id;

    private Integer outId;

    private String partsNo;

    private String partsName;

    private String partsEnName;

    private String unit;

    private BigDecimal salesPrice;

    private String deviceType;

    private Integer stockPosition;

    private Integer status;

    private Integer outNum;

    private Date createTime;

    private Date updateTime;

    private String address;

    private String defineSn;

    private Date entryTime;

    private String entryTimeStr;

    private String destination;

    private String buyContractNo;

    private String model;

    private String sn;

    private String engineNo;

    private String xxNo;

    private String brand;

    private String modelAlias;

    public OutDetail(Integer id, Integer outId, String partsNo, String partsName, String partsEnName, String unit, BigDecimal salesPrice, String deviceType, Integer stockPosition, Integer status, Integer outNum, Date createTime, Date updateTime, String defineSn,String address,  Date entryTime, String destination, String buyContractNo, String model, String sn, String engineNo, String xxNo, String brand,String modelAlias) {
        this.id = id;
        this.outId = outId;
        this.partsNo = partsNo;
        this.partsName = partsName;
        this.partsEnName = partsEnName;
        this.unit = unit;
        this.salesPrice = salesPrice;
        this.deviceType = deviceType;
        this.stockPosition = stockPosition;
        this.status = status;
        this.outNum = outNum;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.defineSn = defineSn;
        this.address = address;
        this.entryTime = entryTime;
        this.destination = destination;
        this.buyContractNo = buyContractNo;
        this.model = model;
        this.sn = sn;
        this.engineNo = engineNo;
        this.xxNo = xxNo;
        this.brand = brand;
        this.modelAlias = modelAlias;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefineSn() {
        return defineSn;
    }

    public void setDefineSn(String defineSn) {
        this.defineSn = defineSn;
    }

    public OutDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
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

    public Integer getStockPosition() {
        return stockPosition;
    }

    public void setStockPosition(Integer stockPosition) {
        this.stockPosition = stockPosition;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
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

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getEntryTimeStr() {
        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr) {
        this.entryTimeStr = entryTimeStr;
    }

    public String getModelAlias() {
        return modelAlias;
    }

    public void setModelAlias(String modelAlias) {
        this.modelAlias = modelAlias;
    }
}