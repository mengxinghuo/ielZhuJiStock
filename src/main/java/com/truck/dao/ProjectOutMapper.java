package com.truck.dao;

import com.truck.pojo.ProjectOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectOut record);

    int insertSelective(ProjectOut record);

    ProjectOut selectByPrimaryKey(Integer id);

    List<ProjectOut> selectByOutIdProIdStatus(@Param("outDetailId") Integer outDetailId, @Param("projectId") Integer projectId, @Param("status") Integer status);

    List<ProjectOut> list(@Param("projectId") Integer projectId, @Param("status") Integer status);

    int updateByPrimaryKeySelective(ProjectOut record);

    int updateByPrimaryKey(ProjectOut record);
}