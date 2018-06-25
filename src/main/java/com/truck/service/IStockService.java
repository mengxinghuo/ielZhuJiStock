package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Stock;

import java.util.List;

public interface IStockService {

    ServerResponse getStockList(Integer adminId,Integer entryId,int pageNum, int pageSize);

  /*  ServerResponse getStockDetail(Integer entryId, int pageNum, int pageSize);

    ServerResponse updateStockDetailStatus(Integer entryDetailId, Integer inspectStatus);

    ServerResponse updateStockDetailNum(Integer entryDetailId, Integer entryNum);*/
    ServerResponse batchStockIn(Integer entryId);
}
