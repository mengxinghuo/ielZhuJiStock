package com.truck.pojo;

import java.util.Date;

public class Project {
    private Integer id;

    private String name;

    private Integer customerId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Project(Integer id, String name, Integer customerId, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Project() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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