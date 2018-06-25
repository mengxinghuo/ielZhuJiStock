package com.truck.pojo;

import java.util.Date;

public class Out {
    private Integer id;

    private String outNo;

    private Integer status;

    private Integer operatorId;

    private Date createTime;

    private Date updateTime;

    private Integer repairId;

    private String repairNo;

    public Out(Integer id, String outNo, Integer status, Integer operatorId, Date createTime, Date updateTime, Integer repairId, String repairNo) {
        this.id = id;
        this.outNo = outNo;
        this.status = status;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.repairId = repairId;
        this.repairNo = repairNo;
    }

    public Out() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo == null ? null : outNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public String getRepairNo() {
        return repairNo;
    }

    public void setRepairNo(String repairNo) {
        this.repairNo = repairNo == null ? null : repairNo.trim();
    }
}