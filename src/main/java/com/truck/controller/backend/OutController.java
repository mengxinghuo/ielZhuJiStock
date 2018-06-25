package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.IOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/out/")
public class OutController {

    @Autowired
    private IOutService iOutService;

    /**
     * 生成出库单
     * @param session
     * @return
     */
    @RequestMapping("out_stock.do")
    @ResponseBody
    public ServerResponse outStock(HttpSession session,String repairNo){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iOutService.outStock(admin.getAdminId(),repairNo);
    }

    /**
     * 获取出库单列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_out_list.do")
    @ResponseBody
    public ServerResponse getOutList(HttpSession session,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iOutService.getOutList(admin.getAdminId(),pageNum,pageSize);
    }

    /**
     * 查询出库详情
     * @param outId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_out_detail.do")
    @ResponseBody
    public ServerResponse getOutDetail(Integer outId,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iOutService.getOutDetail(outId,pageNum,pageSize);
    }
}
