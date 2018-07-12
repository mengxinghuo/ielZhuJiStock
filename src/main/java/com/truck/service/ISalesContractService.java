package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.SalesContract;

public interface ISalesContractService {

    ServerResponse addSalesContract(SalesContract salesContract);

    ServerResponse getSalesContractDetail(Integer salesContractId);

    ServerResponse getCustomerSalesContract(Integer customerId,int pageNum,int pageSize);

    ServerResponse getSalesContractList(int pageNum,int pageSize);
}
