package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.OrderDetail;
import com.truck.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    /**
     * 创建订单
     * @param session
     * @return
     */
    @RequestMapping("create_order.do")
    @ResponseBody
    public ServerResponse createOrder(HttpSession session, Integer outDetailId,
                                      @RequestParam(value = "paymentType",required = false)Integer paymentType,
                                      @RequestParam(value = "serviceType", defaultValue = "1")Integer serviceType,
                                      OrderDetail orderDetail){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iOrderService.createOrder(admin.getAdminId(),outDetailId,paymentType,serviceType,orderDetail);
    }


    /**
     * 根据订单编号查询订单详情
     * @param session
     * @param orderNo
     * @return
     */
    @RequestMapping("get_order_detail.do")
    @ResponseBody
    public ServerResponse getOrderDetail(HttpSession session,
                                         String orderNo,
                                         @RequestParam(value = "userId",required = false)Integer userId){
        if (userId != null) {
            return iOrderService.getOrderDetail(userId,orderNo);
        }else {
            Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
            if(admin == null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
            }
            return iOrderService.getOrderDetail(admin.getAdminId(), orderNo);
        }
    }

    /**
     * 查询所有产品服务，维修订单列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list_by_productId_status.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderListByProductIdStatus(HttpSession session,
                                                               Integer outDetailId,
                                                               Integer serviceType,
                                                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iOrderService.orderListByProductIdStatuss(outDetailId,serviceType,admin.getAdminId(),pageNum, pageSize);
    }


    /**
     * 获取当前用户的所有订单
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_order_list.do")
    @ResponseBody
    public ServerResponse orderList(HttpSession session, Integer status,
                                    @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                    @RequestParam(value = "userId",required = false)Integer userId,
                                    @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        if (userId != null) {
            return iOrderService.getOrderList(userId,status,pageNum,pageSize);
        }else{
            Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
            if(admin == null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
            }
            return iOrderService.getOrderList(admin.getAdminId(),status,pageNum,pageSize);
        }
    }

    /**
     * 修改记录
     * @param session
     * @return
     */
    @RequestMapping("update_progress_status.do")
    @ResponseBody
    public ServerResponse<String> updateProgressStatus(HttpSession session, Integer orderId ,OrderDetail orderDetail){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iOrderService.updateProgressStatus(orderId,orderDetail);
    }

}
