package com.truck.dao;

import com.truck.pojo.Transport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transport record);

    int insertSelective(Transport record);

    Transport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transport record);

    int updateByPrimaryKey(Transport record);

    int selectByDeclareNum(@Param("declareNum") String declareNum);

    int checkoutDeclareNum(@Param("id") Integer id,@Param("declareNum") String declareNum);

    List getAllList(@Param("status") Integer status);
}