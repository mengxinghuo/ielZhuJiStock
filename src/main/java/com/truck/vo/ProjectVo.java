package com.truck.vo;


import com.truck.pojo.OutDetail;

import java.util.Date;
import java.util.List;

public class ProjectVo {
    private Integer id;

    private Integer productId;

    private String name;

    private String createTime;

    private String updateTime;

    private Integer AdminId;

    private List<OutDetail> outDetails;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAdminId() {
        return AdminId;
    }

    public void setAdminId(Integer adminId) {
        AdminId = adminId;
    }

    public List<OutDetail> getOutDetails() {
        return outDetails;
    }

    public void setOutDetails(List<OutDetail> outDetails) {
        this.outDetails = outDetails;
    }
}
