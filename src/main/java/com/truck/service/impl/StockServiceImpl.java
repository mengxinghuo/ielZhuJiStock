package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
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

import java.lang.reflect.Field;
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
    @Autowired
    private RepertoryMapper repertoryMapper;

    public ServerResponse batchStockIn(Integer entryId){
        if (entryId == null ) {
            return ServerResponse.createByErrorMessage("入库参数错误");
        }
        Entry entry = entryMapper.selectByPrimaryKey(entryId);
        if(Const.EntryStatusEnum.FINISH.getCode() == entry.getStatus()){
            return ServerResponse.createByErrorMessage("本记录已入库，无法再次入库");
        }
        if(Const.EntryStatusEnum.STANDBY.getCode() == entry.getStatus()){
            return ServerResponse.createByErrorMessage("未检验，无法入库");
        }
        List<EntryDetail> entryDetails = entryDetailMapper.selectEntryDetail(entryId);
        for (EntryDetail entryDetail : entryDetails) {
            if (entryDetail.getInspectStatus() == 0 && StringUtils.isEmpty(entryDetail.getErrorDescs())) {
                return ServerResponse.createByErrorMessage("请确认入库或填写问题描述");
            }
            if (entryDetail.getEntryPosition()==null) {
                return ServerResponse.createByErrorMessage("请选择入库位置");
            }
        }
        List<Stock> stockList = entryDetailToStock(entryDetails);
        int count = stockMapper.batchInsert(stockList);
        if(count == 0){
            return ServerResponse.createByErrorMessage("入库失败");
        }
        entry.setStatus(Const.EntryStatusEnum.FINISH.getCode());
        entryMapper.updateByPrimaryKeySelective(entry);
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

    public ServerResponse searchStockList(Integer adminId,Stock stock, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Stock> stockList =stockMapper.selectByStockSelective(stock);
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

    public ServerResponse searchLikeStockList(Integer adminId,Stock stock, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Stock> stockList =stockMapper.selectByStockSelectiveLike(stock);
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

    public ServerResponse updateStockConfiguration(Integer stockId,String configuration){
        Stock search = stockMapper.selectByPrimaryKey(stockId);
        if(search == null){
            return ServerResponse.createByErrorMessage("该条记录不存在");
        }
        if(StringUtils.isEmpty(configuration)){
            return ServerResponse.createByErrorMessage("请填写配置信息");
        }
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setConfiguration(configuration);
        int resultCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更改失败");
        }
        return ServerResponse.createBySuccess("更改成功");
    }

    public ServerResponse updateStockUnit(Integer stockId,String unit){
        Stock search = stockMapper.selectByPrimaryKey(stockId);
        if(search == null){
            return ServerResponse.createByErrorMessage("该条记录不存在");
        }
//        if(StringUtils.isEmpty(unit)){
//            return ServerResponse.createByErrorMessage("请填写预定号码");
//        }
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setUnit(unit);
        //1 Ready  2Not Ready
        if(!StringUtils.isEmpty(unit)){
            stock.setBookStatus(1);
        }else{
            stock.setBookStatus(2);
        }
        int resultCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更改失败");
        }
        return ServerResponse.createBySuccess("更改成功");
    }

    @Override
    public ServerResponse updateStockUnitStatus(Integer stockId, Integer bookStatus) {
        Stock search = stockMapper.selectByPrimaryKey(stockId);
        if(search == null){
            return ServerResponse.createByErrorMessage("该条记录不存在");
        }
        if(bookStatus == null){
            return ServerResponse.createByErrorMessage("请填写预定状态");
        }
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setBookStatus(bookStatus);
        int resultCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更改失败");
        }
        return ServerResponse.createBySuccess("更改成功");
    }

    public ServerResponse updateStockError(Integer stockId,String partsName,String partsEnName){
        Stock search = stockMapper.selectByPrimaryKey(stockId);
        if(search == null){
            return ServerResponse.createByErrorMessage("该条记录不存在");
        }
        Stock stock = new Stock();
        stock.setId(stockId);
        if(org.apache.commons.lang3.StringUtils.isNotBlank(partsName))
        stock.setPartsName(partsName);
        if(org.apache.commons.lang3.StringUtils.isNotBlank(partsEnName))
        stock.setPartsEnName(partsEnName);
        int resultCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更改失败");
        }
        return ServerResponse.createBySuccess("更改成功");
    }


    public StockVo assembleStockVo(Integer adminId,Stock stock){
        StockVo stockVo = new StockVo();
        stockVo.setId(stock.getId());
        stockVo.setEntryId(stock.getEntryId());
        stockVo.setCustomsClearance(stock.getCustomsClearance());
        stockVo.setDestination(stock.getDestination());
        stockVo.setPartsNo(stock.getPartsNo());
        stockVo.setPartsName(stock.getPartsName());
        if(org.apache.commons.lang3.StringUtils.isNotBlank(stock.getPartsEnName()))
        stockVo.setPartsEnName(Splitter.on(",").splitToList(stock.getPartsEnName()));
        stockVo.setUnit(stock.getUnit());
        stockVo.setQuantity(stock.getQuantity());
        stockVo.setSalesPrice(stock.getSalesPrice());
        stockVo.setDeviceType(stock.getDeviceType());
        stockVo.setRepertory(stock.getRepertory());
        //仓库名字
        Repertory repertorys = repertoryMapper.selectByPrimaryKey(stock.getRepertory());
        stockVo.setRepertoryStr(repertorys.getName());
        stockVo.setPosition(stock.getPosition());
        Cart cart = cartMapper.selectCartByAdminIdStockId(adminId,stock.getId());
        if (cart != null) {
            stockVo.setAmount(cart.getAmount());
        }else{
            stockVo.setAmount(0);
        }
        stockVo.setCreateTime(DateTimeUtil.dateToStr(stock.getCreateTime()));
        stockVo.setUpdateTime(DateTimeUtil.dateToStr(stock.getUpdateTime()));

        if(!org.springframework.util.StringUtils.isEmpty(stock.getPosition())){
            //拼接 位置代码
            List<Integer> idList = Lists.newArrayList();
            iRepertoryService.findDeepParentId(idList,stock.getPosition());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = idList.size() - 1; i >= 0; i--) {
                Repertory repertory = repertoryMapper.selectByPrimaryKey(idList.get(i));
                if (repertory != null) {
                    stringBuilder.append("-"+repertory.getCode());
                }
            }
            stockVo.setAddress(stock.getCustomsClearance()+stringBuilder.toString());
        }

        stockVo.setShipNum(stock.getShipNum());
        stockVo.setBuyContractNo(stock.getBuyContractNo());
        stockVo.setModel(stock.getModel());
        stockVo.setSn(stock.getSn());
        stockVo.setEngineNo(stock.getEngineNo());
        stockVo.setXxNo(stock.getXxNo());
        stockVo.setBrand(stock.getBrand());


        stockVo.setTypeCategoryId(stock.getTypeCategoryId());
        stockVo.setModelAlias(stock.getModelAlias());
        stockVo.setConfiguration(stock.getConfiguration());
        stockVo.setBookStatus(stock.getBookStatus());
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

            stock.setShipNum(entryDetailItem.getShipNum());
            stock.setBuyContractNo(entryDetailItem.getBuyContractNo());
            stock.setModel(entryDetailItem.getModel());
            stock.setSn(entryDetailItem.getSn());
            stock.setEngineNo(entryDetailItem.getEngineNo());
            stock.setXxNo(entryDetailItem.getXxNo());
            stock.setBrand(entryDetailItem.getBrand());
            if (entryDetailItem.getModelAlias() != null) {
                stock.setModelAlias(entryDetailItem.getModelAlias());
            }
            stock.setConfiguration(entryDetailItem.getConfiguration());
            stock.setStatus(Const.StockStatusEnum.UN_OUT.getCode());
            stockList.add(stock);
        }
        return stockList;
    }

}
