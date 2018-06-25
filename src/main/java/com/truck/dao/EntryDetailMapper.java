package com.truck.dao;

import com.truck.pojo.EntryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EntryDetail record);

    int insertSelective(EntryDetail record);

    EntryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EntryDetail record);

    int updateByPrimaryKey(EntryDetail record);

    int bachInsertExports(@Param("exportsListsList") List<EntryDetail> entryDetailList);

    List selectEntryDetail(@Param("entryId") Integer entryId);
}