package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Cart {
    private Integer cartId;

    private Integer adminId;

    private Integer stockId;

    private Integer amount;

    private Integer checked;

    private Date createTime;

    private Date updateTime;

    private BigDecimal cartPrice;

    private String defineSn;

    private String defineStr;

    private String defineModelNo;

    public Cart(Integer cartId, Integer adminId, Integer stockId, Integer amount, Integer checked, Date createTime, Date updateTime, BigDecimal cartPrice, String defineSn,String defineStr,String defineModelNo) {
        this.cartId = cartId;
        this.adminId = adminId;
        this.stockId = stockId;
        this.amount = amount;
        this.checked = checked;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.cartPrice = cartPrice;
        this.defineSn = defineSn;
        this.defineStr = defineStr;
        this.defineModelNo = defineModelNo;
    }

    public Cart() {
        super();
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
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

    public BigDecimal getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(BigDecimal cartPrice) {
        this.cartPrice = cartPrice;
    }

    public String getDefineSn() {
        return defineSn;
    }

    public void setDefineSn(String defineSn) {
        this.defineSn = defineSn;
    }

    public String getDefineStr() {
        return defineStr;
    }

    public void setDefineStr(String defineStr) {
        this.defineStr = defineStr;
    }

    public String getDefineModelNo() {
        return defineModelNo;
    }

    public void setDefineModelNo(String defineModelNo) {
        this.defineModelNo = defineModelNo;
    }
}