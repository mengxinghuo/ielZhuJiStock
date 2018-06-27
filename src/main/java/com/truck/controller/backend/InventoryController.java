package com.truck.controller.backend;

import com.truck.common.ServerResponse;
import com.truck.pojo.StockInventory;
import com.truck.service.IEntryService;
import com.truck.service.IInventoryService;
import com.truck.util.DateTimeUtil;
import com.truck.util.FTPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage/inventory/")
public class InventoryController {

    @Autowired
    private IInventoryService iInventoryService;

    private static  final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    /**
     * 生成盘点
     * @return
     */
    @RequestMapping("gen_inventory_order.do")
    @ResponseBody
    public ServerResponse getEntryList( String stockInventoryList){

        logger.info("传过来的参数:{}",stockInventoryList);
        /*for (StockInventory stockInventory : stockInventoryList) {
            logger.info("id===:{}",stockInventory.getId());
            logger.info("quantity===:{}",stockInventory.getQuantity());
            logger.info("pandian===:{}",stockInventory.getPandian());
        }*/
        JSONArray json = JSONArray.fromObject(stockInventoryList);
        List<StockInventory> list = JSONArray.toList(json,StockInventory.class);
        return iInventoryService.createInventory(list);
    }

    /**
     * 查询盘点列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_inventory_list.do")
    @ResponseBody
    public ServerResponse getInventoryList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iInventoryService.getInventoryList(pageNum,pageSize);
    }

    /**
     * 查询盘点详情
     * @param inventoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_inventory_detail.do")
    @ResponseBody
    public ServerResponse getInventoryDetail(Integer inventoryId,
                                             Integer status,
                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iInventoryService.getInventoryDetail(inventoryId,status,pageNum,pageSize);
    }

}
