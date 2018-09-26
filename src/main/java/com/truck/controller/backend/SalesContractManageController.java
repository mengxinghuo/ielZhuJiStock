package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.SalesContract;
import com.truck.service.ISalesContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/salesContract/")
public class SalesContractManageController {

    @Autowired
    private ISalesContractService iSalesContractService;

    /**
     * 新增销售合同
     * @param session
     * @param salesContract
     * @return
     */
    @RequestMapping("add_sales_contract.do")
    @ResponseBody
    public ServerResponse addSalesContract(HttpSession session, SalesContract salesContract){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iSalesContractService.addSalesContract(salesContract);
    }

    /**
     * 获取销售合同详情
     * @param session
     * @param salesContractId
     * @return
     */
    @RequestMapping("get_sales_contract_detail.do")
    @ResponseBody
    public ServerResponse getSalesContractDetail(HttpSession session, Integer salesContractId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iSalesContractService.getSalesContractDetail(salesContractId);
    }

    /**
     * 获取某一客户的销售合同信息
     * @param session
     * @param customerId
     * @return
     */
    @RequestMapping("get_customer_sales_contract.do")
    @ResponseBody
    public ServerResponse getCustomerSalesContract(HttpSession session,Integer customerId,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iSalesContractService.getCustomerSalesContract(customerId,pageNum,pageSize);
    }

    /**
     * 查询所有合同列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_sales_contract_list.do")
    @ResponseBody
    public ServerResponse getSalesContractList(HttpSession session,
                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
      /*  Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }*/
        return iSalesContractService.getSalesContractList(pageNum,pageSize);
    }

    /**
     * 查询所有已售设备
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_sales_contract_sold.do")
    @ResponseBody
    public ServerResponse getSalesContractSold(HttpSession session,
                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iSalesContractService.getSalesContractSold(pageNum,pageSize);
    }

    /**
     * 根据出库单查询该合同信息
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("get_out_sales_contract.do")
    @ResponseBody
    public ServerResponse getOutSalesContract(HttpSession session,Integer id){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iSalesContractService.getOutSalesContract(id);
    }

    /**
     * 查询所有已售设备列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_sales_device_list.do")
    @ResponseBody
    public ServerResponse getSalesDeviceList(HttpSession session,
                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iSalesContractService.getSalesDeviceList(pageNum,pageSize);
    }
}
