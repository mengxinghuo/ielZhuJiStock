package com.truck.vo;


public class SoldVo {

    private Integer outDetailId;

    private String deviceType;

    private String model;

    private String sn;

    private String ptName;

    public Integer getOutDetailId() {
        return outDetailId;
    }

    public void setOutDetailId(Integer outDetailId) {
        this.outDetailId = outDetailId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }
}