package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Customer;

public interface ICustomerService {

    ServerResponse addCustomer(Customer customer);

    ServerResponse updateCustomer(Customer customer);

    ServerResponse getCustomerList(String customerNo,String ptName,Integer status, int pageNum,int pageSize);

    ServerResponse searchLikeCustomerList(Customer customer, int pageNum,int pageSize);

    ServerResponse getCustomerListOrder(int pageNum,int pageSize);

    ServerResponse disableCustomer(Integer customerId,Integer status);

    ServerResponse getEnableCustomer();

    ServerResponse getCustomerDetail(Integer customerId);

    ServerResponse updateIntroduction(Integer customerId,String introduction);
}
