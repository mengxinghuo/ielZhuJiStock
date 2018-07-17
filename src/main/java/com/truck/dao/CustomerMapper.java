package com.truck.dao;

import com.truck.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Customer checkOutCustomer(@Param("ptName") String ptName,@Param("customerNo") String customerNo,@Param("phoneNumber") String phoneNumber,@Param("customerId") Integer customerId);

    List<Customer> selectByNoAndName(@Param("customerNo") String customerNo,@Param("ptName") String ptName,@Param("status") Integer status);

    List<Customer> selectOrderByName();
}