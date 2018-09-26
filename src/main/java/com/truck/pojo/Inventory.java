package com.truck.pojo;

import java.util.Date;

public class Inventory {
    private Integer id;

    private String inventoryNo;

    private Date beginTime;

    private Date endTime;

    private Integer inventoryStatus;

    private Date createTime;

    private Date updateTime;

    public Inventory(Integer id, String inventoryNo, Date beginTime, Date endTime, Integer inventoryStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.inventoryNo = inventoryNo;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.inventoryStatus = inventoryStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Inventory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInventoryNo() {
        return inventoryNo;
    }

    public void setInventoryNo(String inventoryNo) {
        this.inventoryNo = inventoryNo == null ? null : inventoryNo.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
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