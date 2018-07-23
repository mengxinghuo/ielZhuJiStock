package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail {
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

    private Date createTime;

    private Date updateTime;

    private Integer shippingId;

    private Integer orderDetailStatus;

    private BigDecimal defineRatio;

    private BigDecimal servicesRatio;

    private BigDecimal distanceRatio;

    private BigDecimal distance;

    //

    private Date repairDates;

    private String man;

    private String manContact;

    private String dept;

    private String hourKm;
    //0 进场   1外出
    private Integer repairType;

    private String errorDescs;

    private Integer maintainType;

    private Date reservationDates;

    private String orderNo;

    private String repairDatesStr;

    private String reservationDatesStr;

    public OrderDetail(Integer orderDetailId, Integer userId, Integer orderId, Integer productId, String productNo, String productName, Integer quantity, BigDecimal totalPrice, String productImage, BigDecimal currentUnitPrice, Date createTime, Date updateTime, Integer shippingId, Integer orderDetailStatus,BigDecimal defineRatio,BigDecimal servicesRatio,BigDecimal distanceRatio,BigDecimal distance,
                       Date repairDates,String man,String manContact,String dept,String hourKm,Integer repairType,String errorDescs,Integer maintainType,Date reservationDates,String orderNo) {
        this.orderDetailId = orderDetailId;
        this.userId = userId;
        this.orderId = orderId;
        this.productId = productId;
        this.productNo = productNo;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productImage = productImage;
        this.currentUnitPrice = currentUnitPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shippingId = shippingId;
        this.orderDetailStatus = orderDetailStatus;
        this.defineRatio = defineRatio;
        this.servicesRatio = servicesRatio;
        this.distanceRatio = distanceRatio;
        this.distance = distance;
        this.repairDates = repairDates;
        this.man = man;
        this.manContact = manContact;
        this.dept = dept;
        this.hourKm = hourKm;
        this.repairType = repairType;
        this.errorDescs = errorDescs;
        this.maintainType = maintainType;
        this.reservationDates = reservationDates;
        this.orderNo = orderNo;
    }

    public OrderDetail() {
        super();
    }

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
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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
        this.productImage = productImage == null ? null : productImage.trim();
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
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

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(Integer orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
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

    public Date getRepairDates() {
        return repairDates;
    }

    public void setRepairDates(Date repairDates) {
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

    public Date getReservationDates() {
        return reservationDates;
    }

    public void setReservationDates(Date reservationDates) {
        this.reservationDates = reservationDates;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRepairDatesStr() {
        return repairDatesStr;
    }

    public void setRepairDatesStr(String repairDatesStr) {
        this.repairDatesStr = repairDatesStr;
    }

    public String getReservationDatesStr() {
        return reservationDatesStr;
    }

    public void setReservationDatesStr(String reservationDatesStr) {
        this.reservationDatesStr = reservationDatesStr;
    }
}