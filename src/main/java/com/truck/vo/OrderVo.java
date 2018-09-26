package com.truck.vo;

import com.truck.pojo.OutDetail;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderVo {

    private Integer orderId;

    private String orderNo;

    private Integer userId;

    private Integer shopId;

    private BigDecimal orderPrice;
    private BigDecimal paymentPrice;

    private Integer paymentType;
    private String paymentTypeDesc;

    private BigDecimal freight;

    private Integer orderStatus;
    private String statusDesc;

    private String paymentTime;

    private String createTime;

    private String updateTime;

    private List<OrderDetailVo> orderDetailVoList;

    private Integer serviceType;

    private String serviceTypeDesc;

    private Integer serviceProgress;

    private Set serviceProgressList;

    private Integer productId;

    private String orderName;

    private OutDetail outDetail;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Set getServiceProgressList() {
        return serviceProgressList;
    }

    public void setServiceProgressList(Set serviceProgressList) {
        this.serviceProgressList = serviceProgressList;
    }

    public Integer getServiceProgress() {
        return serviceProgress;
    }

    public void setServiceProgress(Integer serviceProgress) {
        this.serviceProgress = serviceProgress;
    }

    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        this.serviceTypeDesc = serviceTypeDesc;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(BigDecimal paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public List<OrderDetailVo> getOrderDetailVoList() {
        return orderDetailVoList;
    }

    public void setOrderDetailVoList(List<OrderDetailVo> orderDetailVoList) {
        this.orderDetailVoList = orderDetailVoList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public OutDetail getOutDetail() {
        return outDetail;
    }

    public void setOutDetail(OutDetail outDetail) {
        this.outDetail = outDetail;
    }
}
