package com.truck.dao;

import com.truck.pojo.SalesContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalesContractMapper {
    int deleteByPrimaryKey(Integer salesContractId);

    int insert(SalesContract record);

    int insertSelective(SalesContract record);

    SalesContract selectByPrimaryKey(Integer salesContractId);

    int updateByPrimaryKeySelective(SalesContract record);

    int updateByPrimaryKey(SalesContract record);

    SalesContract checkOutSalesContract(@Param("bpkNo") String bpkNo,@Param("outNo") String outNo);

    List<SalesContract> selectByCustomer(@Param("customerId") Integer customerId);

    List<SalesContract> selectSalesContractList();

    SalesContract selectByOutId(@Param("outId") Integer outId);
}