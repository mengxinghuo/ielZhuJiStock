package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IOrderService;
import com.truck.util.BigDecimalUtil;
import com.truck.util.DateTimeUtil;
import com.truck.vo.OrderDetailVo;
import com.truck.vo.OrderVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private OutDetailMapper outDetailMapper;

    /**
     * 创建订单
     * @return
     */
    public ServerResponse createOrder(Integer adminId, Integer outDetailId, Integer paymentType, Integer serviceType, OrderDetail orderDetail) {
        OutDetail outDetail = outDetailMapper.selectByPrimaryKey(outDetailId);
        Order order = new Order();
        order.setPaymentType(Const.PaymentTypeEnum.ARRIVE_PAY.getCode());

        //维修日期
        if (StringUtils.isNotBlank(orderDetail.getRepairDatesStr())){
            orderDetail.setRepairDates(DateTimeUtil.strToDate(orderDetail.getRepairDatesStr(),"yyyy-MM-dd"));
        }
        //保养预约日期
        if (StringUtils.isNotBlank(orderDetail.getReservationDatesStr())){
            orderDetail.setReservationDates(DateTimeUtil.strToDate(orderDetail.getReservationDatesStr(),"yyyy-MM-dd"));
        }

        order = this.assembleOrder(adminId, null, outDetailId, order.getPaymentType(), serviceType);
        orderDetail.setOrderNo(order.getOrderNo());
        orderDetail.setUserId(adminId);
        orderDetail.setProductId(order.getProductId());
        orderDetail.setOrderId(order.getOrderId());

        if (order != null) {
            int rowCount = orderDetailMapper.insertSelective(orderDetail);
            if (rowCount > 0) {
                OrderVo orderVo=this.assembleOrderVo(order);
                return ServerResponse.createBySuccess("生成订单成功",orderVo);
            }else{
                orderMapper.deleteByPrimaryKey(order.getOrderId());
                return ServerResponse.createByErrorMessage("数据异常，单子建立失败");
            }
        }
        return ServerResponse.createByErrorMessage("订单添加失败");
    }

    private OrderVo assembleOrderVo(Order order) {
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(order.getOrderId());
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setUserId(order.getUserId());
        orderVo.setShopId(order.getShopId());
        orderVo.setOrderPrice(order.getOrderPrice());
        orderVo.setPaymentPrice(order.getPaymentPrice());
        orderVo.setPaymentType(order.getPaymentType());
        orderVo.setPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getPaymentType()).getValue());
        orderVo.setFreight(order.getFreight());
        orderVo.setOrderStatus(order.getOrderStatus());
        orderVo.setStatusDesc(Const.OrderStatusEnum.codeOf(order.getOrderStatus()).getValue());
        orderVo.setPaymentTime(DateTimeUtil.dateToStr(order.getPaymentTime()));
        orderVo.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
        orderVo.setUpdateTime(DateTimeUtil.dateToStr(order.getUpdateTime()));

        orderVo.setServiceType(order.getServiceType());
        orderVo.setServiceTypeDesc(Const.ServiceStatusEnum.codeOf(order.getServiceType()).getValue());

        orderVo.setServiceProgress(order.getServiceProgress());
        orderVo.setProductId(order.getProductId());

        OutDetail outDetail = outDetailMapper.selectByPrimaryKey(order.getProductId());
        if (outDetail != null) {
            orderVo.setOrderName(outDetail.getModel()+orderVo.getServiceTypeDesc());
            orderVo.setOutDetail(outDetail);
        }
        //todo
        //orderDetailList 订单详情列表
        List<OrderDetail> orderDetailList = orderDetailMapper.selectListByOrderId(order.getOrderId());
        List<OrderDetailVo> orderDetailVoList = Lists.newArrayList();
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailVo orderDetailVo = assembleOrderDetailVo(orderDetail);
            orderDetailVoList.add(orderDetailVo);
        }
        orderVo.setOrderDetailVoList(orderDetailVoList);
        return orderVo;
    }

    private OrderDetailVo assembleOrderDetailVo(OrderDetail orderDetail) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrderDetailId(orderDetail.getOrderDetailId());
        orderDetailVo.setUserId(orderDetail.getUserId());
        orderDetailVo.setOrderId(orderDetail.getOrderId());
        orderDetailVo.setProductId(orderDetail.getProductId());
        orderDetailVo.setProductNo(orderDetail.getProductNo());
        orderDetailVo.setProductName(orderDetail.getProductName());
        orderDetailVo.setProductImage(orderDetail.getProductImage());
        orderDetailVo.setCurrentUnitPrice(orderDetail.getCurrentUnitPrice());
        orderDetailVo.setQuantity(orderDetail.getQuantity());
        orderDetailVo.setTotalPrice(orderDetail.getTotalPrice());
        orderDetailVo.setCreateTime(DateTimeUtil.dateToStr(orderDetail.getCreateTime()));
        orderDetailVo.setOrderDetailStatus(orderDetail.getOrderDetailStatus());

        orderDetailVo.setDefineRatio(orderDetail.getDefineRatio());
        orderDetailVo.setDistanceRatio(orderDetail.getDistanceRatio());
        orderDetailVo.setServicesRatio(orderDetail.getServicesRatio());
        orderDetailVo.setDistance(orderDetail.getDistance());

        orderDetailVo.setRepairDates(DateTimeUtil.dateToStr(orderDetail.getRepairDates(),"yyyy-MM-dd"));
        orderDetailVo.setReservationDates(DateTimeUtil.dateToStr(orderDetail.getReservationDates(),"yyyy-MM-dd"));
        orderDetailVo.setMan(orderDetail.getMan());
        orderDetailVo.setManContact(orderDetail.getManContact());
        orderDetailVo.setDept(orderDetail.getDept());
        orderDetailVo.setHourKm(orderDetail.getHourKm());
        orderDetailVo.setRepairType(orderDetail.getRepairType());
        orderDetailVo.setErrorDescs(orderDetail.getErrorDescs());
        orderDetailVo.setMaintainType(orderDetail.getMaintainType());
        orderDetailVo.setOrderNo(orderDetail.getOrderNo());

//        orderDetailVo.setStatusDesc(Const.OrderDetailStatusEnum.codeOf(orderDetail.getOrderDetailStatus()).getValue());
        return orderDetailVo;

    }

    /**
     * 清空购物车
     *
     * @param cartList
     */
    private void cleanCart(List<Cart> cartList) {
        for (Cart cart : cartList) {
            cartMapper.deleteByPrimaryKey(cart.getCartId());
        }
    }

    private Order assembleOrder(Integer adminId, Integer shopId, Integer outDetailId, Integer paymentType, Integer serviceType) {
        Order order = new Order();
        String orderNo = String.valueOf(this.generateOrderNo());
        order.setOrderNo(orderNo);
        order.setUserId(adminId);
        order.setShopId(shopId);
        order.setOrderPrice(new BigDecimal(0));
        order.setPaymentPrice(new BigDecimal(0));
        order.setPaymentType(paymentType);
        order.setOrderStatus(Const.OrderStatusEnum.SERVICE_ING.getCode());
        order.setServiceType(serviceType);
        order.setServiceProgress(1);
        order.setProductId(outDetailId);
        //发货时间
        //付款时间
        int rowCount = orderMapper.insertSelective(order);
        if (rowCount > 0) {
            return orderMapper.selectByPrimaryKey(order.getOrderId());
        }
        return null;
    }

    /**
     * 订单编号的生成
     *
     * @return
     */
    private long generateOrderNo() {
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }

    /**
     * 计算金额
     *
     * @param orderDetailList
     * @return
     */
    private BigDecimal getOrderTotalPrice(List<OrderDetail> orderDetailList) {
        BigDecimal payment = new BigDecimal("0");
        for (OrderDetail orderDetail : orderDetailList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderDetail.getTotalPrice().doubleValue());
        }
        return payment;
    }


    /**
     * 顾客根据订单编号获取订单详情
     *
     * @param userId
     * @param orderNo
     * @return
     */
    public ServerResponse getOrderDetail(Integer userId, String orderNo) {
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if (order != null) {
            OrderVo orderVo = assembleOrderVo(order);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByErrorMessage("没有找到该订单");
    }

    /**
     * 获取当前用户所有的订单信息
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getOrderList(Integer userId, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectByUserIdStatus(userId,status);
        List<OrderVo> orderVoList = assembleOrderVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private List<OrderVo> assembleOrderVoList(List<Order> orderList) {
        List<OrderVo> orderVoList = Lists.newArrayList();
        for (Order order : orderList) {
            OrderVo orderVo = assembleOrderVo(order);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }

    //backend

    /**
     * 超级管理员获取所有订单信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAllOrder();
        List<OrderVo> orderVoList = this.assembleOrderVoByList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<PageInfo> orderListByProductIdStatuss(Integer outDetailId, Integer serviceType, Integer adminId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectOrderByProductIdStatus(outDetailId,serviceType);
        List<OrderVo> orderVoList = this.assembleOrderVoByList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     *
     *
     * @return
     */
    public List<OrderVo> assembleOrderVoByList( List<Order> orderList) {
        List<OrderVo> orderVos = Lists.newArrayList();
        for (Order order : orderList) {
            List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailByAdminId(order.getOrderId(),null);
            List<OrderDetailVo> orderDetailVoList = Lists.newArrayList();
            BigDecimal shopTotalPrice = new BigDecimal("0");
            for (OrderDetail orderDetail : orderDetailList) {
                OrderDetailVo orderDetailVo = assembleOrderDetailVo(orderDetail);
                orderDetailVoList.add(orderDetailVo);
            }
            OrderVo orderVo = new OrderVo();
            orderVo.setOrderId(order.getOrderId());
            orderVo.setOrderNo(order.getOrderNo());
            orderVo.setUserId(order.getUserId());
            orderVo.setShopId(order.getShopId());
            orderVo.setPaymentType(order.getPaymentType());
            orderVo.setOrderPrice(order.getOrderPrice());
            orderVo.setPaymentPrice(order.getPaymentPrice());
            orderVo.setPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getPaymentType()).getValue());
            orderVo.setFreight(order.getFreight());
            orderVo.setOrderStatus(order.getOrderStatus());
            orderVo.setStatusDesc(Const.OrderStatusEnum.codeOf(order.getOrderStatus()).getValue());
            orderVo.setPaymentTime(DateTimeUtil.dateToStr(order.getPaymentTime()));
            orderVo.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
            orderVo.setUpdateTime(DateTimeUtil.dateToStr(order.getUpdateTime()));

            orderVo.setServiceType(order.getServiceType());
            orderVo.setServiceTypeDesc(Const.ServiceStatusEnum.codeOf(order.getServiceType()).getValue());
            orderVo.setServiceProgress(order.getServiceProgress());
            orderVo.setProductId(order.getProductId());

            OutDetail outDetail = outDetailMapper.selectByPrimaryKey(order.getProductId());
            orderVo.setOrderName(outDetail.getModel()+orderVo.getServiceTypeDesc());

            orderVo.setOutDetail(outDetail);
            orderVo.setOrderDetailVoList(orderDetailVoList);
            orderVos.add(orderVo);
        }
        return orderVos;
    }

    /**
     * 模糊查询订单（订单编号）
     *
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageSearch(String orderNo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(orderNo)) {
            orderNo = new StringBuilder().append("%").append(orderNo).append("%").toString();
        }
        List<Order> orderList = orderMapper.searchByOrderNo(orderNo);
        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 店家模糊查询订单（订单编号）
     *
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageSearchShop(Integer adminId, String orderNo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(orderNo)) {
            orderNo = new StringBuilder().append("%").append(orderNo).append("%").toString();
        }
        List<Order> orderList = orderMapper.selectOrderByShopIdAndOrderNo(null,orderNo);
        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public  ServerResponse updateProgressStatus(Integer orderId, OrderDetail orderDetail){
        if (orderId == null) {
            return ServerResponse.createByErrorMessage("请选择订单");
        }
        int rowCount = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新订单成功");
        }
        return ServerResponse.createByErrorMessage("更新订单失败");
    }


}
