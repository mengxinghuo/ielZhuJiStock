package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Out;
import com.truck.pojo.OutDetail;
import com.truck.pojo.SalesContract;
import com.truck.vo.OutVo;

public interface IOutService {

    ServerResponse outStock(Integer adminId, Out out, SalesContract salesContract);

    ServerResponse getOutList(Integer adminId,int pageNum,int pageSize);

    ServerResponse getOutDetail(Integer outId,int pageNum,int pageSize);

    ServerResponse getOutDetailById(Integer outDetailId);

    OutVo assembleOut(Out out);
}
