package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StockInventory {
    private Integer id;

    private Integer quantity;

    private Integer pandian;

    private String errorDescs;

    public StockInventory(Integer id, Integer quantity, Integer pandian,String errorDescs) {
        this.id = id;
        this.quantity = quantity;
        this.pandian = pandian;
        this.errorDescs = errorDescs;
    }

    public StockInventory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPandian() {
        return pandian;
    }

    public void setPandian(Integer pandian) {
        this.pandian = pandian;
    }

    public String getErrorDescs() {
        return errorDescs;
    }

    public void setErrorDescs(String errorDescs) {
        this.errorDescs = errorDescs;
    }
}