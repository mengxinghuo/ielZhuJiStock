package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.dao.EntryMapper;
import com.truck.dao.TransportMapper;
import com.truck.pojo.Entry;
import com.truck.pojo.EntryDetail;
import com.truck.pojo.Transport;
import com.truck.service.ITransportService;
import com.truck.util.DateTimeUtil;
import com.truck.util.FTPUtil;
import com.truck.util.JsonUtil;
import com.truck.vo.TransportVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("iTransportService")
public class TransportServiceImpl implements ITransportService {

    @Autowired
    private TransportMapper transportMapper;
    @Autowired
    private EntryMapper entryMapper;
    @Autowired
    private EntryDetailMapper entryDetailMapper;

    private static  final Logger logger = LoggerFactory.getLogger(TransportServiceImpl.class);

    /**
     * 出口 录入信息
     * @param adminId
     * @param transport
     * @return
     */
    public ServerResponse addTransport(Integer adminId, Transport transport){
        if(StringUtils.isEmpty(transport.getDeclareNum())){
            return ServerResponse.createByErrorMessage("请填写报关次数");
        }
        int rowCount = transportMapper.selectByDeclareNum(transport.getDeclareNum());
        if(rowCount > 0){
            return ServerResponse.createByErrorMessage("报关次数已存在");
        }
        if(StringUtils.isEmpty(transport.getDestination())){
            return ServerResponse.createByErrorMessage("请填写目的地");
        }
        if(StringUtils.isEmpty(transport.getShipNum())){
            return ServerResponse.createByErrorMessage("请填写船次");
        }
        /*if(StringUtils.isEmpty(transport.getArrivalList()) || StringUtils.isEmpty(transport.getPurchaseList()) || StringUtils.isEmpty(transport.getPurchaseContract())
                || StringUtils.isEmpty(transport.getSalesContract()) || StringUtils.isEmpty(transport.getInvoice()) || StringUtils.isEmpty(transport.getExportCost())){
            return ServerResponse.createByErrorMessage("上传信息不足，请完善");
        }*/
        transport.setStatus(Const.TransportStatusEnum.OVER_EXIT.getCode());
        int resultCount = transportMapper.insertSelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("信息录入成功");
        }
        return ServerResponse.createByErrorMessage("信息录入失败");
    }

    /**
     * 修改出口记录
     * @param adminId
     * @param transport
     * @return
     */
    public ServerResponse updateTransport(Integer adminId, Transport transport){
        if(StringUtils.isEmpty(transport.getId())){
            return ServerResponse.createByErrorMessage("请选择要修改的记录");
        }
 /*       Transport transport1 = transportMapper.selectByPrimaryKey(transport.getId());
        if (transport1 != null) {
            if(org.apache.commons.lang3.StringUtils.isNotBlank(transport1.getSalesList()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(transport1.getEntranceCost())){
                return ServerResponse.createByErrorMessage("进口已经在使用该记录，无法修改");
            }
        }*/
        if(!StringUtils.isEmpty(transport.getDeclareNum())){
            int rowCount = transportMapper.checkoutDeclareNum(transport.getId(),transport.getDeclareNum());
            if(rowCount > 0){
                return ServerResponse.createByErrorMessage("报关次数已存在");
            }
        }
        //待定判断
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    /**
     * 删除出口记录
     * @param adminId
     * @param id
     * @return
     */
    public ServerResponse delTransport(Integer adminId, Integer id){
        if(StringUtils.isEmpty(id)){
            return ServerResponse.createByErrorMessage("请选择要删除的记录");
        }
/*        Transport transport = transportMapper.selectByPrimaryKey(id);
        if (transport != null) {
            if(org.apache.commons.lang3.StringUtils.isNotBlank(transport.getSalesList()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(transport.getEntranceCost())){
                return ServerResponse.createByErrorMessage("进口已经在使用该记录，无法删除");
            }
        }*/
        //待定判断
        int resultCount = transportMapper.deleteByPrimaryKey(id);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 进口 完善信息
     * @param id
     * @param salesList
     * @return
     */
    public ServerResponse consummateTransport( Integer id, String salesList){
        if(StringUtils.isEmpty(id)){
            return ServerResponse.createByErrorMessage("请选择记录");
        }
        if(StringUtils.isEmpty(salesList)){
            return ServerResponse.createByErrorMessage("信息不完整");
        }
        Transport transport = new Transport();
        transport.setId(id);
        transport.setSalesList(salesList);
        transport.setStatus(Const.TransportStatusEnum.CONFIRM.getCode());
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("完善成功");
        }
        return ServerResponse.createByErrorMessage("完善失败");
    }

    /**
     * 查询列表，带分页
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getAllList(Integer status,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Transport> transportList = transportMapper.getAllList(status);
        if(transportList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<TransportVo> transportVoList = Lists.newArrayList();
        for(Transport transportItem : transportList){
            TransportVo transportVo = this.assembleTransport(transportItem);
            transportVoList.add(transportVo);
        }
        PageInfo pageInfo = new PageInfo(transportList);
        pageInfo.setList(transportVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 创建入库单
     * @param
     * @return
     */
    public ServerResponse createEntry(String  entryStr){
        Entry entry = JsonUtil.string2Obj(entryStr,Entry.class);
        int resultCount = entryMapper.insertSelective(entry);
        if(resultCount > 0){
            return ServerResponse.createBySuccess(entry.getId());
        }
        return ServerResponse.createByErrorMessage("创建失败");
    }

    public ServerResponse checkEntryByDeclareNum(String declareNum){
        Entry entry = entryMapper.selectByDeclareNum(declareNum);
        if(entry != null){
            if(Const.EntryStatusEnum.FINISH.getCode() < entry.getStatus()){
                return ServerResponse.createByErrorMessage("本单已入库，无法替换");
            }
            entryDetailMapper.deleteByEntryId(entry.getId());
            List<EntryDetail> entryDetailList = entryDetailMapper.selectEntryDetail(entry.getId());
            if(entryDetailList.size() == 0){
                entryMapper.deleteByPrimaryKey(entry.getId());
                return ServerResponse.createBySuccess();
            }else{
                return ServerResponse.createByErrorMessage("清除失败");
            }
        }
        return ServerResponse.createBySuccess();
    }

    private long generateEntryNo(){
        long currentTime =System.currentTimeMillis();
        return currentTime+new Random().nextInt(100);
    }

    /**
     * 数据转化
     * @param transport
     * @return
     */
    public TransportVo assembleTransport(Transport transport){
        TransportVo transportVo = new TransportVo();
        transportVo.setId(transport.getId());
        transportVo.setShipNum(transport.getShipNum());
        transportVo.setDeclareNum(transport.getDeclareNum());
        transportVo.setDestination(transport.getDestination());
        if(!StringUtils.isEmpty(transport.getArrivalList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getArrivalList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setArrivalList(mapList);
        }
        if(!StringUtils.isEmpty(transport.getPurchaseList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getPurchaseList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setPurchaseList(mapList);
        }
        if(!StringUtils.isEmpty(transport.getSalesContract())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getSalesContract());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setSalesContract(mapList);
        }
        if(!StringUtils.isEmpty(transport.getInvoice())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getInvoice());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setInvoice(mapList);
        }
        if(!StringUtils.isEmpty(transport.getPurchaseContract())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getPurchaseContract());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setPurchaseContract(mapList);
        }
        if(!StringUtils.isEmpty(transport.getExportCost())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getExportCost());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setExportCost(mapList);
        }
        if(!StringUtils.isEmpty(transport.getEntranceCost())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getEntranceCost());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setEntranceCost(mapList);
        }
        if(!StringUtils.isEmpty(transport.getSalesList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getSalesList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setSalesList(mapList);
        }
        transportVo.setStatus(transportVo.getStatus());
        transportVo.setStatusDesc(Const.TransportStatusEnum.codeOf(transport.getStatus()).getValue());
        transportVo.setCreateTime(DateTimeUtil.dateToStr(transport.getCreateTime(),"yyyy-MM-dd"));
        transportVo.setUpdateTime(DateTimeUtil.dateToStr(transport.getUpdateTime()));
        transportVo.setUrlPeiJian("http://cdn.ayotrust.com/upload/配件入库模板.xls");
        transportVo.setUrlZhuJi("http://cdn.ayotrust.com/upload/主机入库模板.xls");

        return transportVo;
    }

    public String getFileName(String url){
        String fileName = url.substring(url.lastIndexOf("/") +1);
        return fileName;
    }
}
