package com.truck.service;

import com.truck.common.ServerResponse;

public interface IOutService {

    ServerResponse outStock(Integer adminId,String repairNo);

    ServerResponse getOutList(Integer adminId,int pageNum,int pageSize);

    ServerResponse getOutDetail(Integer outId,int pageNum,int pageSize);
}
