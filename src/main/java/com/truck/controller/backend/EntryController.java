package com.truck.controller.backend;

import com.truck.common.ServerResponse;
import com.truck.service.IEntryService;
import com.truck.service.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/entry/")
public class EntryController {

    @Autowired
    private IEntryService iEntryService;
    @Autowired
    private ITransportService iTransportService;

    /**
     * 查询入库单列表
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_entry_list.do")
    @ResponseBody
    public ServerResponse getEntryList(@RequestParam(value = "status",required = false) Integer status,
                                       @RequestParam(value = "declareNum",required = false) String declareNum,
                                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iEntryService.getEntryList(status,declareNum,pageNum,pageSize);
    }

    /**
     * 查询入库详情
     * @param entryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_entry_detail.do")
    @ResponseBody
    public ServerResponse getEntryDetail(Integer entryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iEntryService.getEntryDetail(entryId,pageNum,pageSize);
    }

    /**
     * 查询入库详情,带状态
     * @param entryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_entry_detail_status.do")
    @ResponseBody
    public ServerResponse getEntryDetail(Integer entryId,
                                         @RequestParam(value = "status",defaultValue = "0")Integer status,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iEntryService.getEntryDetailByStatus(entryId,status,pageNum,pageSize);
    }

    /**
     * 检验实际入库量,修改状态
     * @return
     */
    @RequestMapping("update_entry_detail_status.do")
    @ResponseBody
    public ServerResponse updateEntryDetailStatus(Integer entryDetailId,
                                              @RequestParam(value = "inspectStatus", required = false) Integer inspectStatus){
        return iEntryService.updateEntryDetailStatus(entryDetailId,inspectStatus);
    }

    /**
     * 检验实际入库量,修改数量
     * @return
     */
    @RequestMapping("update_entry_detail_num.do")
    @ResponseBody
    public ServerResponse updateEntryDetailNum(Integer entryDetailId,
                                              @RequestParam(value = "entryNum", required = false) Integer entryNum){
        return iEntryService.updateEntryDetailNum(entryDetailId,entryNum);
    }

    /**
     * 检验实际入库量,修改位置
     * @return
     */
    @RequestMapping("update_entry_detail_position.do")
    @ResponseBody
    public ServerResponse updateEntryDetailPosition(Integer entryDetailId,
                                              @RequestParam(value = "entryPosition", required = false) Integer entryPosition){
        return iEntryService.updateEntryDetailPosition(entryDetailId,entryPosition);
    }

    /**
     * 检验实际入库量,填写问题描述，或手动添加设备种类id
     * @return
     */
    @RequestMapping("update_entry_detail_idOrDescs.do")
    @ResponseBody
    public ServerResponse updateEntryDetailIdOrDescs(Integer entryDetailId,
                                              @RequestParam(value = "typeCategoryId", required = false) Integer typeCategoryId,
                                              @RequestParam(value = "errorDescs", required = false) String errorDescs){
        return iEntryService.updateEntryDetailIdOrDescs(entryDetailId,typeCategoryId,errorDescs);
    }

    /**
     * 创建订单
     * @param
     * @return
     */
    @RequestMapping("create_entry.do")
    @ResponseBody
    public ServerResponse createEntry(String entryStr){
        return iTransportService.createEntry(entryStr);
    }
}
