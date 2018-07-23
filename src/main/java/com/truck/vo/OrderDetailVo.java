package com.truck.vo;

import java.math.BigDecimal;

public class OrderDetailVo {

    private Integer orderDetailId;

    private Integer userId;

    private Integer orderId;

    private Integer productId;

    private String productNo;

    private String productName;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private String createTime;

    private String updateTime;

    private Integer orderDetailStatus;
    private String statusDesc;

    private BigDecimal defineRatio;

    private BigDecimal servicesRatio;

    private BigDecimal distanceRatio;

    private BigDecimal distance;

    private String repairDates;

    private String man;

    private String manContact;

    private String dept;

    private String hourKm;

    private Integer repairType;

    private String errorDescs;

    private Integer maintainType;

    private String reservationDates;

    private String orderNo;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
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

    public Integer getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(Integer orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public BigDecimal getDefineRatio() {
        return defineRatio;
    }

    public void setDefineRatio(BigDecimal defineRatio) {
        this.defineRatio = defineRatio;
    }

    public BigDecimal getServicesRatio() {
        return servicesRatio;
    }

    public void setServicesRatio(BigDecimal servicesRatio) {
        this.servicesRatio = servicesRatio;
    }

    public BigDecimal getDistanceRatio() {
        return distanceRatio;
    }

    public void setDistanceRatio(BigDecimal distanceRatio) {
        this.distanceRatio = distanceRatio;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getRepairDates() {
        return repairDates;
    }

    public void setRepairDates(String repairDates) {
        this.repairDates = repairDates;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getManContact() {
        return manContact;
    }

    public void setManContact(String manContact) {
        this.manContact = manContact;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getHourKm() {
        return hourKm;
    }

    public void setHourKm(String hourKm) {
        this.hourKm = hourKm;
    }

    public Integer getRepairType() {
        return repairType;
    }

    public void setRepairType(Integer repairType) {
        this.repairType = repairType;
    }

    public String getErrorDescs() {
        return errorDescs;
    }

    public void setErrorDescs(String errorDescs) {
        this.errorDescs = errorDescs;
    }

    public Integer getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(Integer maintainType) {
        this.maintainType = maintainType;
    }

    public String getReservationDates() {
        return reservationDates;
    }

    public void setReservationDates(String reservationDates) {
        this.reservationDates = reservationDates;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
