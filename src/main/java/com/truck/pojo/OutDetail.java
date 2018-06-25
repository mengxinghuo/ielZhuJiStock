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

    public OutDetail(Integer id, Integer outId, String partsNo, String partsName, String partsEnName, String unit, BigDecimal salesPrice, String deviceType, Integer stockPosition, Integer status, Integer outNum, Date createTime, Date updateTime) {
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
}