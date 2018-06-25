package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IStockService;
import com.truck.service.IRepertoryService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iStockService")
public class StockServiceImpl implements IStockService {

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private EntryDetailMapper entryDetailMapper;
    @Autowired
    private IRepertoryService iRepertoryService;
    @Autowired
    private EntryMapper entryMapper;
    @Autowired
    private CartMapper cartMapper;

    public ServerResponse batchStockIn(Integer entryId){
        if (entryId == null ) {
            return ServerResponse.createByErrorMessage("入库参数错误");
        }
        Entry entry = entryMapper.selectByPrimaryKey(entryId);
        if(Const.EntryStatusEnum.FINISH.getCode() == entry.getStatus()){
            return ServerResponse.createByErrorMessage("本记录已入库，无法再次入库");
        }
        if(Const.EntryStatusEnum.STANDBY.getCode() == entry.getStatus()){
            return ServerResponse.createByErrorMessage("未盘点，无法入库");
        }
        List<EntryDetail> entryDetails = entryDetailMapper.selectEntryDetail(entryId);
        for (EntryDetail entryDetail : entryDetails) {
            if (entryDetail.getInspectStatus() == 0 && entryDetail.getEntryNum()==null) {
                return ServerResponse.createByErrorMessage("请确认入库或填写实际数量");
            }
            if (entryDetail.getEntryPosition()==null) {
                return ServerResponse.createByErrorMessage("请选择入库位置");
            }
        }
        List<Stock> stockList = entryDetailToStock(entryDetails);
        int count = stockMapper.batchInsert(stockList);
        if(count == 0){
            entry.setStatus(Const.EntryStatusEnum.FINISH.getCode());
            entryMapper.updateByPrimaryKeySelective(entry);
            return ServerResponse.createByErrorMessage("入库失败");
        }
        return ServerResponse.createBySuccess("入库成功");
    }

    public ServerResponse getStockList(Integer adminId,Integer entryId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Stock> stockList = stockMapper.selectByEntryId(entryId);
        if(stockList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<StockVo> stockVoList = Lists.newArrayList();
        for(Stock stockItem : stockList){
            StockVo stockVo = this.assembleStockVo(adminId,stockItem);
            stockVoList.add(stockVo);
        }
        PageInfo pageInfo = new PageInfo(stockList);
        pageInfo.setList(stockVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public StockVo assembleStockVo(Integer adminId,Stock stock){
        StockVo stockVo = new StockVo();
        stockVo.setId(stock.getId());
        stockVo.setEntryId(stock.getEntryId());
        stockVo.setCustomsClearance(stock.getCustomsClearance());
        stockVo.setDestination(stock.getDestination());
        stockVo.setPartsNo(stock.getPartsNo());
        stockVo.setPartsName(stock.getPartsName());
        stockVo.setPartsEnName(stock.getPartsEnName());
        stockVo.setUnit(stock.getUnit());
        stockVo.setQuantity(stock.getQuantity());
        stockVo.setSalesPrice(stock.getSalesPrice());
        stockVo.setDeviceType(stock.getDeviceType());
        stockVo.setRepertory(stock.getRepertory());
        stockVo.setPosition(stock.getPosition());
        Cart cart = cartMapper.selectCartByAdminIdStockId(adminId,stock.getId());
        if (cart != null) {
            stockVo.setAmount(cart.getAmount());
        }else{
            stockVo.setAmount(0);
        }
        stockVo.setCreateTime(DateTimeUtil.dateToStr(stock.getCreateTime()));
        stockVo.setUpdateTime(DateTimeUtil.dateToStr(stock.getUpdateTime()));
        return stockVo;
    }
    
    public List<Stock> entryDetailToStock(List<EntryDetail> entryDetailList){
        List<Stock> stockList = Lists.newArrayList();
        for(EntryDetail entryDetailItem : entryDetailList){
            Stock stock = new Stock();
            stock.setEntryId(entryDetailItem.getEntryId());
            stock.setCustomsClearance(entryDetailItem.getCustomsClearance());
            stock.setDestination(entryDetailItem.getDestination());
            stock.setPartsNo(entryDetailItem.getPartsNo());
            stock.setPartsName(entryDetailItem.getPartsName());
            stock.setPartsEnName(entryDetailItem.getPartsEnName());
            stock.setUnit(entryDetailItem.getUnit());
            //根据状态判断入库数量
            if(entryDetailItem.getInspectStatus() == 0){
                stock.setQuantity(entryDetailItem.getEntryNum());
            }else{
                stock.setQuantity(entryDetailItem.getPurchaseNum());
            }

            stock.setSalesPrice(entryDetailItem.getSalesPrice());
            stock.setDeviceType(entryDetailItem.getDeviceType());

            List<Integer> idList = Lists.newArrayList();
            iRepertoryService.findDeepParentId(idList,entryDetailItem.getEntryPosition());
            if (idList.size() > 0) {
                stock.setRepertory(idList.get(idList.size()-1));
            }
            stock.setPosition(entryDetailItem.getEntryPosition());
            stockList.add(stock);
        }
        return stockList;
    }

}
