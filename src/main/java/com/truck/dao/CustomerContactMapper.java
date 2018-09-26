package com.truck.dao;

import com.truck.pojo.CustomerContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerContactMapper {
    int deleteByPrimaryKey(Integer contactId);

    int insert(CustomerContact record);

    int insertSelective(CustomerContact record);

    CustomerContact selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(CustomerContact record);

    int updateByPrimaryKey(CustomerContact record);

    CustomerContact checkOutCustomerContact(@Param("email") String email,@Param("phone") String phone,@Param("contactId") Integer contactId);

    List<CustomerContact> selectByCustomerId(@Param("customerId") Integer customerId);
}