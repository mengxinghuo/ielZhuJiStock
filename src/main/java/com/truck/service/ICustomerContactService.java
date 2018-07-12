package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.CustomerContact;

public interface ICustomerContactService {

    ServerResponse addCustomerContact(CustomerContact customerContact);

    ServerResponse updateCustomerContact(CustomerContact customerContact);

    ServerResponse getCustomerContact(Integer customerId);
}
