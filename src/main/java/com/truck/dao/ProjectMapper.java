package com.truck.dao;


import com.truck.pojo.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> selectByCustomerId(@Param("customerId") Integer customerId);

    List<Project> selectByNameCusIdStatus(@Param("name") String name, @Param("customerId") Integer customerId, @Param("status") Integer status);

}