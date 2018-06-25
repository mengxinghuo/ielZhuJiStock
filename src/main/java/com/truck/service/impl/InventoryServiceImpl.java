package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.InventoryDetailMapper;
import com.truck.dao.InventoryMapper;
import com.truck.dao.StockMapper;
import com.truck.pojo.Inventory;
import com.truck.pojo.InventoryDetail;
import com.truck.pojo.Stock;
import com.truck.pojo.StockInventory;
import com.truck.service.IInventoryService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.InventoryDetailVo;
import com.truck.vo.InventoryVo;
import com.truck.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service("iInventoryService")
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private InventoryDetailMapper inventoryDetailMapper;
    @Autowired
    private StockMapper stockMapper;

    public ServerResponse createInventory(List<StockInventory> stockInventoryList){
        Inventory inventory = new Inventory();
        inventory.setInventoryStatus(Const.InventoryStatusEnum.OVER_INVENTORY.getCode());
        int resultCount = inventoryMapper.insertSelective(inventory);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("盘点记录生成失败");
        }
        Date date = new Date();
        String no = DateTimeUtil.dateToStr(date,"yyyyMMdd");
        String inventoryNo = no + inventory.getId().toString();
        inventory.setInventoryNo(inventoryNo);
        inventoryMapper.updateByPrimaryKeySelective(inventory);
        List<InventoryDetail> inventoryDetailList = getInventoryDetailList(stockInventoryList,inventory.getId());
        resultCount = inventoryDetailMapper.batchInsert(inventoryDetailList);
        if(resultCount > 0){
            for(StockInventory stockInventoryItme : stockInventoryList){
                Stock stock = new Stock();
                stock.setId(stockInventoryItme.getId());
                stock.setQuantity(stockInventoryItme.getPandian());
                stockMapper.updateByPrimaryKeySelective(stock);
            }
            return ServerResponse.createBySuccess("生成成功");
        }
        return ServerResponse.createByErrorMessage("数据异常，记录生成失败");
    }

    public List<InventoryDetail> getInventoryDetailList(List<StockInventory> stockInventoryList,Integer inventoryId){
        List<InventoryDetail> inventoryDetailList = Lists.newArrayList();
        for(StockInventory stockInventoryItem : stockInventoryList){
            InventoryDetail inventoryDetail = new InventoryDetail();
            inventoryDetail.setInventoryId(inventoryId);
            inventoryDetail.setStockId(stockInventoryItem.getId());
            inventoryDetail.setStockNum(stockInventoryItem.getQuantity());
            inventoryDetail.setInventoryNum(stockInventoryItem.getPandian());
            inventoryDetailList.add(inventoryDetail);
        }
        return inventoryDetailList;
    }

    public ServerResponse getInventoryList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Inventory> inventoryList = inventoryMapper.selectAllList();
        if(inventoryList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<InventoryVo> inventoryVoList = Lists.newArrayList();
        for(Inventory inventoryItem : inventoryList){
            InventoryVo inventoryVo = this.assembleInventory(inventoryItem);
            inventoryVoList.add(inventoryVo);
        }
        PageInfo pageInfo = new PageInfo(inventoryList);
        pageInfo.setList(inventoryVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getInventoryDetail(Integer inventoryId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if(StringUtils.isEmpty(inventoryId)){
            return ServerResponse.createByErrorMessage("请选择要查询的记录");
        }
        List<InventoryDetail> inventoryDetailList = inventoryDetailMapper.selectByInventoryId(inventoryId);
        List<InventoryDetailVo> inventoryDetailVoList = Lists.newArrayList();
        for(InventoryDetail inventoryDetailItem : inventoryDetailList){
            InventoryDetailVo inventoryDetailVo = this.assembleInventoryDetail(inventoryDetailItem);
            inventoryDetailVoList.add(inventoryDetailVo);
        }
        PageInfo pageInfo = new PageInfo(inventoryDetailList);
        pageInfo.setList(inventoryDetailVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public InventoryVo assembleInventory(Inventory inventory){
        InventoryVo inventoryVo = new InventoryVo();
        inventoryVo.setId(inventory.getId());
        inventoryVo.setInventoryNo(inventory.getInventoryNo());
        inventoryVo.setInventoryStatus(inventory.getInventoryStatus());
        inventoryVo.setStatusDesc(Const.InventoryStatusEnum.codeOf(inventory.getInventoryStatus()).getValue());
        inventoryVo.setCreateTime(DateTimeUtil.dateToStr(inventory.getCreateTime()));
        inventoryVo.setUpdateTime(DateTimeUtil.dateToStr(inventory.getUpdateTime()));
        return inventoryVo;
    }

    public InventoryDetailVo assembleInventoryDetail(InventoryDetail inventoryDetail){
        InventoryDetailVo inventoryDetailVo = new InventoryDetailVo();
        inventoryDetailVo.setId(inventoryDetail.getId());
        inventoryDetailVo.setInventoryId(inventoryDetail.getInventoryId());
        inventoryDetailVo.setInventoryNum(inventoryDetail.getInventoryNum());
        inventoryDetailVo.setStockId(inventoryDetail.getStockId());
        inventoryDetailVo.setStockNum(inventoryDetail.getStockNum());
        Stock stock = stockMapper.selectByPrimaryKey(inventoryDetail.getStockId());
        StockVo stockVo = assembleStockVo(stock);
        inventoryDetailVo.setStockVo(stockVo);
        inventoryDetailVo.setCreateTime(DateTimeUtil.dateToStr(inventoryDetail.getCreateTime()));
        inventoryDetailVo.setUpdateTime(DateTimeUtil.dateToStr(inventoryDetail.getUpdateTime()));
        return inventoryDetailVo;
    }

    public StockVo assembleStockVo(Stock stock){
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
        stockVo.setCreateTime(DateTimeUtil.dateToStr(stock.getCreateTime()));
        stockVo.setUpdateTime(DateTimeUtil.dateToStr(stock.getUpdateTime()));
        return stockVo;
    }
}
