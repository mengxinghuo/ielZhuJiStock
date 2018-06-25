package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StockInventory {
    private Integer id;

    private Integer quantity;

    private Integer pandian;

    public StockInventory(Integer id, Integer quantity, Integer pandian) {
        this.id = id;
        this.quantity = quantity;
        this.pandian = pandian;
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
}