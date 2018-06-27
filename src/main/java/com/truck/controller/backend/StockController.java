package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Stock;
import com.truck.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manage/stock/")
public class StockController {

    @Autowired
    private IStockService iStockService;


    /**
     * 批量入库
     *
     * @param entryId
     * @return
     */
    @RequestMapping("batch_stock_in.do")
    @ResponseBody
    public ServerResponse batchStockIn(Integer entryId) {
        return iStockService.batchStockIn(entryId);
    }

    /**
     * 查询库详情
     *
     * @param entryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_stock_list.do")
    @ResponseBody
    public ServerResponse getStockList(HttpSession session, Integer entryId,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iStockService.getStockList(admin.getAdminId(),entryId, pageNum, pageSize);
    }

/*    @RequestMapping("search_stock_list.do")
    @ResponseBody
    public ServerResponse searchStockList(HttpSession session,
                                          @RequestParam(value = "shipNum", required = false) String shipNum,
                                          @RequestParam(value = "customsClearance", required = false) String customsClearance,
                                          @RequestParam(value = "destination", required = false) String destination,
                                          @RequestParam(value = "buyContractNo", required = false) String buyContractNo,
                                          @RequestParam(value = "deviceType", required = false) String deviceType,
                                          @RequestParam(value = "brand", required = false) String brand,
                                          @RequestParam(value = "model", required = false) String model,
                                          @RequestParam(value = "sn", required = false) String sn,
                                          @RequestParam(value = "engineNo", required = false) String engineNo,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iStockService.searchStockList(admin.getAdminId(),entryId, pageNum, pageSize);
    }*/

    /**
     * 搜索查询在库详情
     * @param stock
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search_stock_list.do")
    @ResponseBody
    public ServerResponse searchStockList(HttpSession session,
                                            Stock stock,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iStockService.searchStockList(admin.getAdminId(),stock, pageNum, pageSize);
    }
}