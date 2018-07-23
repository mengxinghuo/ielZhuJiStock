package com.truck.pojo;

import java.util.Date;

public class Project {
    private Integer id;

    private Integer productId;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Integer adminId;

    public Project(Integer id, Integer productId, String name, Date createTime, Date updateTime,Integer adminId) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.adminId = adminId;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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