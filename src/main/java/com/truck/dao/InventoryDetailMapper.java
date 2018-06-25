package com.truck.dao;

import com.truck.pojo.InventoryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryDetail record);

    int insertSelective(InventoryDetail record);

    InventoryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryDetail record);

    int updateByPrimaryKey(InventoryDetail record);

    int batchInsert(@Param("inventoryDetailList") List<InventoryDetail> inventoryDetailList);

    List selectByInventoryId(@Param("inventoryId") Integer inventoryId);
}