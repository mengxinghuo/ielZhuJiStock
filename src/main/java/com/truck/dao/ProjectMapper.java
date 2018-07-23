package com.truck.dao;


import com.truck.pojo.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    Project selectByProductId(Integer productId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> listByAdminId(Integer adminId);

    List<Project> selectByName(@Param("name") String name);

    List<String> listNameByAdminId(Integer adminId);

    int deleteById(@Param("id") Integer id);

    int updateByProject(Project project);
}