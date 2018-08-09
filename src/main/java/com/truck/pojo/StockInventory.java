package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StockInventory {
    private Integer id;

    private Integer quantity;

//    private Integer pandian;

//    private String errorDescs;

    private String partsName;
    //errorImg
    private String partsEnName;

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

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsEnName() {
        return partsEnName;
    }

    public void setPartsEnName(String partsEnName) {
        this.partsEnName = partsEnName;
    }
}