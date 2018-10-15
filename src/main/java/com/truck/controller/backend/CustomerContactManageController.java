package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.CustomerContact;
import com.truck.service.ICustomerContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/contact/")
public class CustomerContactManageController {

    @Autowired
    private ICustomerContactService iCustomerContactService;

    /**
     * 新增联系人
     * @param session
     * @param customerContact
     * @return
     */
    @RequestMapping("add_customer_contact.do")
    @ResponseBody
    public ServerResponse addCustomerContact(HttpSession session, CustomerContact customerContact){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCustomerContactService.addCustomerContact(customerContact);
    }

    /**
     * 修改联系人
     * @param session
     * @param customerContact
     * @return
     */
    @RequestMapping("update_customer_contact.do")
    @ResponseBody
    public ServerResponse updateCustomerContact(HttpSession session,CustomerContact customerContact){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCustomerContactService.updateCustomerContact(customerContact);
    }

    /**
     * 根据客户id查询联系人
     * @param session
     * @param customerId
     * @return
     */
    @RequestMapping("get_customer_contact.do")
    @ResponseBody
    public ServerResponse getCustomerContact(HttpSession session,Integer customerId){
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
//        if(admin == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
//        }
        return iCustomerContactService.getCustomerContact(customerId);
    }
}
