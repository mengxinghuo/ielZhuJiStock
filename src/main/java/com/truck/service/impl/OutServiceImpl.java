package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IOutService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.OutVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("iOutService")
public class OutServiceImpl implements IOutService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OutMapper outMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private OutDetailMapper outDetailMapper;
    @Autowired
    private AdminMapper adminMapper;

    public ServerResponse outStock(Integer adminId,String repairNo){
        if(StringUtils.isBlank(repairNo)){
            return ServerResponse.createByErrorMessage("请输入维修单号");
        }
        List<Cart> cartList = cartMapper.selectCartByAdminId(adminId);
        if(cartList.size() == 0){
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        String outNo = String.valueOf(generateOutNo());
        Out out = new Out();
        out.setOutNo(outNo);
        out.setStatus(Const.OutStatusEnum.UN_OUT.getCode());
        out.setOperatorId(adminId);
        out.setRepairNo(repairNo);
        int resultCount = outMapper.insertSelective(out);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("数据异常，未生成出库单");
        }
        List<OutDetail> outDetailList = getOutDetailList(cartList,out.getId());
        resultCount = outDetailMapper.batchInsert(outDetailList);
        if(resultCount > 0){
            cartMapper.deleteByAdminId(adminId);
            return ServerResponse.createBySuccess("生成出库单成功");
        }
        return ServerResponse.createByErrorMessage("生成出库单失败");
    }

    private long generateOutNo() {
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }

    public List<OutDetail> getOutDetailList(List<Cart> cartList,Integer outId){
        List<OutDetail> outDetailList = Lists.newArrayList();
        for(Cart cartItem : cartList){
            Stock stock = stockMapper.selectByPrimaryKey(cartItem.getStockId());
            OutDetail outDetail = new OutDetail();
            outDetail.setOutId(outId);
            outDetail.setPartsNo(stock.getPartsNo());
            outDetail.setPartsName(stock.getPartsName());
            outDetail.setPartsEnName(stock.getPartsEnName());
            outDetail.setUnit(stock.getUnit());
            outDetail.setSalesPrice(stock.getSalesPrice());
            outDetail.setDeviceType(stock.getDeviceType());
            outDetail.setStockPosition(stock.getPosition());
            outDetail.setOutNum(cartItem.getAmount());
            outDetailList.add(outDetail);
        }
        return outDetailList;
    }

    public ServerResponse getOutList(Integer adminId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Out> outList = outMapper.selectByAdminId(adminId);
        if(outList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<OutVo> outVoList = Lists.newArrayList();
        for(Out outItem : outList){
            OutVo outVo = this.assembleOut(outItem);
            outVoList.add(outVo);
        }
        PageInfo pageInfo = new PageInfo(outList);
        pageInfo.setList(outVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    public ServerResponse getOutDetail(Integer outId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<OutDetail> outDetailList = outDetailMapper.selectByOutId(outId);


        PageInfo pageInfo = new PageInfo(outDetailList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public OutVo assembleOut(Out out){
        OutVo outVo = new OutVo();
        outVo.setId(out.getId());
        outVo.setOutNo(out.getOutNo());
        outVo.setOperatorId(out.getOperatorId());
        Admin admin = adminMapper.selectByPrimaryKey(out.getOperatorId());
        outVo.setOperatorName(admin.getAdminName());
        outVo.setStatus(out.getStatus());
        outVo.setRepairNo(out.getRepairNo());
        outVo.setStatusDesc(Const.OutStatusEnum.codeOf(out.getStatus()).getValue());
        outVo.setCreateTime(DateTimeUtil.dateToStr(out.getCreateTime()));
        outVo.setUpdateTime(DateTimeUtil.dateToStr(out.getUpdateTime()));
        return outVo;
    }

}
