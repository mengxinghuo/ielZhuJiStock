package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.CustomerAddress;

public interface ICustomerAddressService {

    ServerResponse addCustomerAddress(CustomerAddress customerAddress);

    ServerResponse updateCustomerAddress(CustomerAddress customerAddress);

    ServerResponse getCustomerAddress(Integer customerId);
}
