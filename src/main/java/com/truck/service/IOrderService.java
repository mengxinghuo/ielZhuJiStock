package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.OrderDetail;

public interface IOrderService {

    ServerResponse createOrder(Integer adminId, Integer outDetailId, Integer paymentType, Integer serviceType, OrderDetail orderDetail);

    ServerResponse getOrderDetail(Integer userId, String orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, Integer status, int pageNum, int pageSize);

    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<PageInfo> manageSearch(String orderNo, int pageNum, int pageSize);

    ServerResponse<PageInfo> manageSearchShop(Integer adminId, String orderNo, int pageNum, int pageSize);

    ServerResponse<PageInfo> orderListByProductIdStatuss(Integer outDetailId, Integer serviceType, Integer adminId, int pageNum, int pageSize);

    ServerResponse updateProgressStatus(Integer orderId, OrderDetail orderDetail);

}
