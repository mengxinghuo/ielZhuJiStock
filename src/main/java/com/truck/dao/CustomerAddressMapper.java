package com.truck.dao;

import com.truck.pojo.CustomerAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerAddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(CustomerAddress record);

    int insertSelective(CustomerAddress record);

    CustomerAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(CustomerAddress record);

    int updateByPrimaryKey(CustomerAddress record);

    List<CustomerAddress> selectByCustomerId(@Param("customerId") Integer customerId);
}