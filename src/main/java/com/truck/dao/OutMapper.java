package com.truck.dao;

import com.truck.pojo.Out;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Out record);

    int insertSelective(Out record);

    Out selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Out record);

    int updateByPrimaryKey(Out record);

    List selectByAdminId(@Param("adminId") Integer adminId);
}