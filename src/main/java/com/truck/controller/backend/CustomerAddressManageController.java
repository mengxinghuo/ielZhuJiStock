package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.CustomerAddress;
import com.truck.service.ICustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/address/")
public class CustomerAddressManageController {

    @Autowired
    private ICustomerAddressService iCustomerAddressService;

    /**
     * 新增地址信息
     * @param session
     * @param customerAddress
     * @return
     */
    @RequestMapping("add_customer_address.do")
    @ResponseBody
    public ServerResponse addCustomerAddress(HttpSession session, CustomerAddress customerAddress){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCustomerAddressService.addCustomerAddress(customerAddress);
    }

    /**
     * 修改地址信息
     * @param session
     * @param customerAddress
     * @return
     */
    @RequestMapping("update_customer_address.do")
    @ResponseBody
    public ServerResponse updateCustomerAddress(HttpSession session,CustomerAddress customerAddress){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCustomerAddressService.updateCustomerAddress(customerAddress);
    }

    /**
     * 根据客户id查询该客户的地址
     * @param session
     * @param customerId
     * @return
     */
    @RequestMapping("get_customer_address.do")
    @ResponseBody
    public ServerResponse getCustomerAddress(HttpSession session,Integer customerId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCustomerAddressService.getCustomerAddress(customerId);
    }
}
