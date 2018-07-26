package com.truck.vo;

import com.truck.pojo.OutDetail;

public class ProjectOutVo {
    private Integer id;

    private Integer outDetailId;

    private OutDetail outDetail;

    private Integer projectId;

    private ProjectVo projectVo;

    private Integer status;

    private String statusStr;

    private String createTime;

    private String updateTime;

    public ProjectOutVo() {
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

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public ProjectVo getProjectVo() {
        return projectVo;
    }

    public void setProjectVo(ProjectVo projectVo) {
        this.projectVo = projectVo;
    }

    public OutDetail getOutDetail() {
        return outDetail;
    }

    public void setOutDetail(OutDetail outDetail) {
        this.outDetail = outDetail;
    }
}