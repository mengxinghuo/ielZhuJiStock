package com.truck.pojo;

import java.util.Date;

public class ProjectOut {
    private Integer id;

    private Integer outDetailId;

    private Integer projectId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public ProjectOut(Integer id, Integer outDetailId, Integer projectId, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.outDetailId = outDetailId;
        this.projectId = projectId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProjectOut() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutDetailId() {
        return outDetailId;
    }

    public void setOutDetailId(Integer outDetailId) {
        this.outDetailId = outDetailId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
}