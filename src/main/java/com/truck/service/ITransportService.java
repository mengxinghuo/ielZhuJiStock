package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Transport;

public interface ITransportService {

    ServerResponse addTransport(Integer adminId, Transport transport);

    ServerResponse updateTransport(Integer adminId, Transport transport);

    ServerResponse delTransport(Integer adminId, Integer id);

    ServerResponse consummateTransport( Integer id, String salesList);

    ServerResponse getAllList(Integer status,int pageNum,int pageSize);

    ServerResponse createEntry(Integer id);
}
