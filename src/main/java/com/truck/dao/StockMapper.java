package com.truck.dao;

import com.truck.pojo.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    List selectByEntryId(@Param("entryId") Integer entryId);

    List<Stock> selectByStockSelective(@Param("stock") Stock stock);

    List<Stock> selectByStockSelectiveLike(@Param("stock") Stock stock);

    int batchInsert(@Param("stockList")List<Stock> stockList);
}