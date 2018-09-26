package com.truck.dao;

import com.truck.pojo.Repertory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepertoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Repertory record);

    int insertSelective(Repertory record);

    Repertory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repertory record);

    int updateByPrimaryKey(Repertory record);

    List<Repertory> selectRepertoryChildrenByParentId(Integer parentId);

    int deleteByPrimaryKeyByIdList(@Param("integers") List<Integer> integers);

    Repertory selectByName(@Param("name") String name);
}