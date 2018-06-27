package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.StockInventory;

import java.util.List;

public interface IInventoryService {

    ServerResponse createInventory(List<StockInventory> stockInventoryList);

    ServerResponse getInventoryList(int pageNum,int pageSize);

    ServerResponse getInventoryDetail(Integer inventoryId,Integer status,int pageNum,int pageSize);
}
