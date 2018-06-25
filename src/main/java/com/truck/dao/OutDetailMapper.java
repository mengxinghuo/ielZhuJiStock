package com.truck.dao;

import com.truck.pojo.OutDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OutDetail record);

    int insertSelective(OutDetail record);

    OutDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutDetail record);

    int updateByPrimaryKey(OutDetail record);

    int batchInsert(@Param("outDetailList") List<OutDetail> outDetailList);

    List selectByOutId(@Param("outId") Integer outId);
}