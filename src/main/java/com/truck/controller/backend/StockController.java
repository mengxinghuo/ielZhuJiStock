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

    /**
     * 模糊搜索查询在库详情
     * @param stock
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search_like_stock_list.do")
    @ResponseBody
    public ServerResponse searchLikeStockList(HttpSession session,
                                            Stock stock,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iStockService.searchLikeStockList(admin.getAdminId(),stock, pageNum, pageSize);
    }

    /**
     * 修改在库配置信息
     * @param stockId
     * @param configuration
     * @return
     */
    @RequestMapping("update_stock_configuration.do")
    @ResponseBody
    public ServerResponse updateStockConfiguration(Integer stockId,String configuration){
        return iStockService.updateStockConfiguration(stockId,configuration);
    }
}