package com.truck.dao;

import com.truck.pojo.Entry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Entry record);

    int insertSelective(Entry record);

    Entry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Entry record);

    int updateByPrimaryKey(Entry record);

    int checkoutDeclare(@Param("declareNum")Integer declareNum);

    List selectAllList(@Param("status") Integer status,@Param("declareNum") Integer declareNum);
}