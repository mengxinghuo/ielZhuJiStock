package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Stock;
import com.truck.vo.StockVo;

import java.util.List;

public interface IStockService {

    ServerResponse getStockList(Integer adminId,Integer entryId,int pageNum, int pageSize);

    ServerResponse searchStockList(Integer adminId,Stock stock,int pageNum, int pageSize);

    ServerResponse searchLikeStockList(Integer adminId,Stock stock,int pageNum, int pageSize);

  /*  ServerResponse getStockDetail(Integer entryId, int pageNum, int pageSize);

    ServerResponse updateStockDetailStatus(Integer entryDetailId, Integer inspectStatus);

    ServerResponse updateStockDetailNum(Integer entryDetailId, Integer entryNum);*/
    ServerResponse batchStockIn(Integer entryId);

    StockVo assembleStockVo(Integer adminId, Stock stock);

    ServerResponse updateStockConfiguration(Integer stockId,String configuration);

    ServerResponse updateStockUnit(Integer stockId,String unit);

    ServerResponse updateStockUnitStatus(Integer stockId,Integer bookStatus);

    ServerResponse updateStockError(Integer stockId,String partsName,String partsEnName);
}
