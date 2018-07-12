package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IOutService;
import com.truck.service.IRepertoryService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.OutVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    @Autowired
    private IRepertoryService iRepertoryService;
    @Autowired
    private RepertoryMapper repertoryMapper;

    public ServerResponse outStock(Integer adminId,Out out){
        if(StringUtils.isBlank(out.getPjbContractNo())){
            return ServerResponse.createByErrorMessage("请输入PJB合同号");
        }
        if(StringUtils.isBlank(out.getCustomerName())){
            return ServerResponse.createByErrorMessage("请输入客户名称");
        }
        List<Cart> cartList = cartMapper.selectCartByAdminId(adminId);
        if(cartList.size() == 0){
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        for(Cart cartItem : cartList){
            if(StringUtils.isBlank(cartItem.getDefineModelNo())){
                return ServerResponse.createByErrorMessage("请填写客户主机编号");
            }
            if(cartItem.getCartPrice().compareTo(new BigDecimal(0))==0){
                return ServerResponse.createByErrorMessage("请填写合同中的销售价格");
            }
        }
        String outNo = String.valueOf(generateOutNo());
        out.setOutNo(outNo);
        out.setStatus(Const.OutStatusEnum.UN_OUT.getCode());
        out.setOperatorId(adminId);
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
            outDetail.setSalesPrice(cartItem.getCartPrice());
            outDetail.setDeviceType(stock.getDeviceType());
            outDetail.setStockPosition(stock.getPosition());
            outDetail.setOutNum(cartItem.getAmount());

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
                outDetail.setAddress(stock.getCustomsClearance()+stringBuilder.toString());
            }

            if (cartItem.getDefineSn()!= null) {
                outDetail.setDefineSn(cartItem.getDefineSn());
            }
            outDetail.setEntryTime(stock.getCreateTime());
            outDetail.setDestination(stock.getDestination());
            outDetail.setBuyContractNo(stock.getBuyContractNo());
            outDetail.setModel(stock.getModel());
            outDetail.setSn(stock.getSn());
            outDetail.setEngineNo(stock.getEngineNo());
            outDetail.setXxNo(stock.getXxNo());
            outDetail.setBrand(stock.getBrand());
            outDetail.setModelAlias(stock.getModelAlias());
            outDetail.setConfiguration(stock.getConfiguration());
            outDetail.setDefineModelNo(cartItem.getDefineModelNo());
            if (cartItem.getDefineStr()!= null) {
                outDetail.setDefineStr(cartItem.getDefineStr());
            }
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
        for (OutDetail outDetail : outDetailList) {
            outDetail.setEntryTimeStr(DateTimeUtil.dateToStr(outDetail.getEntryTime()));
        }
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

        outVo.setPjbContractNo(out.getPjbContractNo());
        outVo.setCustomerName(out.getCustomerName());
        outVo.setAddress(out.getAddress());
        return outVo;
    }

}
