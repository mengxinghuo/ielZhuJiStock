package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IOutService;
import com.truck.service.ISalesContractService;
import com.truck.util.DateTimeUtil;
import com.truck.util.JsonUtil;
import com.truck.util.Post4;
import com.truck.vo.OutVo;
import com.truck.vo.ProjectOutVo;
import com.truck.vo.SalesContractVo;
import com.truck.vo.SoldVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iSalesContractService")
public class SalesContractServiceImpl implements ISalesContractService {

    @Autowired
    private SalesContractMapper salesContractMapper;
    @Autowired
    private OutMapper outMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerAddressMapper customerAddressMapper;
    @Autowired
    private CustomerContactMapper customerContactMapper;
    @Autowired
    private OutDetailMapper outDetailMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private IOutService iOutService;

    public ServerResponse addSalesContract(SalesContract salesContract){
        if(StringUtils.isEmpty(salesContract.getCustomerId()) || StringUtils.isEmpty(salesContract.getAddressId()) || StringUtils.isEmpty(salesContract.getContactId()) ||
                StringUtils.isEmpty(salesContract.getBpkNo()) || StringUtils.isEmpty(salesContract.getOutNo()) || StringUtils.isEmpty(salesContract.getSalesContractNo()) ||
                StringUtils.isEmpty(salesContract.getSalesDate())){
            return ServerResponse.createByErrorMessage("信息不完整");
        }
        Out out = outMapper.selectByOutNo(salesContract.getOutNo());
        if(out == null){
            return ServerResponse.createByErrorMessage("出库单不存在");
        }
        SalesContract searchBpk = salesContractMapper.checkOutSalesContract(salesContract.getBpkNo(),null);
        if(searchBpk != null){
            return ServerResponse.createByErrorMessage("BPK单号已存在");
        }
        SalesContract searchOut = salesContractMapper.checkOutSalesContract(null,salesContract.getOutNo());
        if(searchOut != null){
            return ServerResponse.createByErrorMessage("出库单号重复");
        }
        salesContract.setOutId(out.getId());
        salesContract.setDate(DateTimeUtil.strToDate(salesContract.getSalesDate(),"yyyy-MM-dd"));
        salesContract.setStatus(Const.SalesContractStatusEnum.NORMAL.getCode());
        //预留type类型字段
        int resultCount = salesContractMapper.insertSelective(salesContract);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("新增成功");
        }else{
            return ServerResponse.createByErrorMessage("新增失败");
        }
    }

    public ServerResponse getSalesContractDetail(Integer salesContractId){
        if(StringUtils.isEmpty(salesContractId)){
            return ServerResponse.createByErrorMessage("请选择要查看的记录");
        }
        SalesContract salesContract = salesContractMapper.selectByPrimaryKey(salesContractId);
        if(salesContract == null){
            return ServerResponse.createByErrorMessage("未查到该记录信息");
        }
        SalesContractVo salesContractVo = this.assembleSalesContract(salesContract);
        return ServerResponse.createBySuccess(salesContractVo);
    }

    public ServerResponse getCustomerSalesContract(Integer customerId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if(StringUtils.isEmpty(customerId)){
            return ServerResponse.createByErrorMessage("请选择要查询的客户");
        }
        List<SalesContract> salesContractList = salesContractMapper.selectByCustomer(customerId);
        if(salesContractList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到结果");
        }
        List<SalesContractVo> salesContractVoList = Lists.newArrayList();
        for(SalesContract salesContractItem : salesContractList){
            SalesContractVo salesContractVo = this.assembleSalesContract(salesContractItem);
            salesContractVoList.add(salesContractVo);
        }
        PageInfo pageInfo = new PageInfo(salesContractList);
        pageInfo.setList(salesContractVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getSalesContractList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SalesContract> salesContractList = salesContractMapper.selectSalesContractList();
        if(salesContractList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到结果");
        }
        List<SalesContractVo> salesContractVoList = Lists.newArrayList();
        for(SalesContract salesContractItem : salesContractList){
            SalesContractVo salesContractVo = this.assembleSalesContract(salesContractItem);
            if (salesContractVo.getOutVo()!=null) {
                salesContractVoList.add(salesContractVo);
            }
        }
        PageInfo pageInfo = new PageInfo(salesContractList);
        pageInfo.setList(salesContractVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getSalesContractSold(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SalesContract> salesContractList = salesContractMapper.selectSalesContractList();
        if(salesContractList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到结果");
        }
        List<SalesContractVo> salesContractVoList = Lists.newArrayList();
        for(SalesContract salesContractItem : salesContractList){
            SalesContractVo salesContractVo = this.assembleSalesContract(salesContractItem);
            if (salesContractVo.getOutVo()!=null) {
                salesContractVoList.add(salesContractVo);
            }
        }
        List<SoldVo> soldVos = Lists.newArrayList();
        for (SalesContractVo salesContractVo : salesContractVoList) {
            for (OutDetail outDetail : salesContractVo.getOutDetailList()) {
                SoldVo soldVo = new SoldVo();
                soldVo.setOutDetailId(outDetail.getId());
                soldVo.setDeviceType(outDetail.getDeviceType());
                soldVo.setModel(outDetail.getModel());
                soldVo.setSn(outDetail.getSn());
                if(salesContractVo.getCustomer() !=null)
                soldVo.setPtName(salesContractVo.getCustomer().getPtName());
                if(outDetail.getCustomer() !=null)
                soldVo.setPtName(outDetail.getCustomer().getPtName());
                soldVos.add(soldVo);
            }
        }
        PageInfo pageInfo = new PageInfo(soldVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getSalesDeviceList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SalesContract> salesContractList = salesContractMapper.selectSalesContractList();
        if(salesContractList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到结果");
        }
        List<OutDetail> outDetailList = Lists.newArrayList();
        for(SalesContract salesContractItem : salesContractList){
            List<OutDetail> outDetailList2 = outDetailMapper.selectByOutId(salesContractItem.getOutId());
            if (outDetailList2.size()>0) {
                outDetailList.addAll(outDetailList2);
            }
        }
        PageInfo pageInfo = new PageInfo(outDetailList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getOutSalesContract(Integer id){
        SalesContract salesContract = salesContractMapper.selectByOutId(id);
        if(salesContract == null){
            return ServerResponse.createByErrorMessage("未查到信息");
        }
        SalesContractVo salesContractVo = this.assembleSalesContract(salesContract);
        return ServerResponse.createBySuccess(salesContractVo);
    }

    public SalesContractVo assembleSalesContract(SalesContract salesContract){
        SalesContractVo salesContractVo = new SalesContractVo();
        salesContractVo.setSalesContractId(salesContract.getSalesContractId());
        salesContractVo.setCustomerId(salesContract.getCustomerId());
        Customer customer = customerMapper.selectByPrimaryKey(salesContract.getCustomerId());
        salesContractVo.setCustomer(customer);
        salesContractVo.setAddressId(salesContract.getAddressId());
        CustomerAddress customerAddress = customerAddressMapper.selectByPrimaryKey(salesContract.getAddressId());
        salesContractVo.setCustomerAddress(customerAddress);
        salesContractVo.setContactId(salesContract.getContactId());
        CustomerContact customerContact = customerContactMapper.selectByPrimaryKey(salesContract.getContactId());
        salesContractVo.setCustomerContact(customerContact);
        salesContractVo.setDate(DateTimeUtil.dateToStr(salesContract.getDate()));
        salesContractVo.setOutId(salesContract.getOutId());
        Out out = outMapper.selectByPrimaryKey(salesContract.getOutId());
        if (out != null) {
            OutVo outVo = iOutService.assembleOut(out);
            salesContractVo.setOutVo(outVo);
            List<OutDetail> outDetailList = outDetailMapper.selectByOutId(out.getId());
            for (OutDetail outDetail : outDetailList) {
                ServerResponse serverResponse = this.getProjectByOutDetailId(outDetail.getId());
                if(serverResponse.isSuccess()){
                    ProjectOutVo projectOutVo =(ProjectOutVo)serverResponse.getData();
                    if (projectOutVo != null) {
                        if(projectOutVo.getProjectVo()!=null)
                        outDetail.setProjectVo(projectOutVo.getProjectVo());
                    }
                }

            }
            salesContractVo.setOutDetailList(outDetailList);
        }
        salesContractVo.setOutNo(salesContract.getOutNo());
        salesContractVo.setBpkNo(salesContract.getBpkNo());
        salesContractVo.setSalesContractNo(salesContract.getSalesContractNo());
        salesContractVo.setStatus(salesContract.getStatus());
        salesContractVo.setStatusDesc(Const.SalesContractStatusEnum.codeOf(salesContract.getStatus()).getValue());
        salesContractVo.setCreateTime(DateTimeUtil.dateToStr(salesContract.getCreateTime()));
        salesContractVo.setUpdateTime(DateTimeUtil.dateToStr(salesContract.getUpdateTime()));

        salesContractVo.setAccessory1(salesContract.getAccessory1());
        salesContractVo.setAccessory2(salesContract.getAccessory2());
        salesContractVo.setAccessory3(salesContract.getAccessory3());
        return salesContractVo;
    }

    public ServerResponse getProjectByOutDetailId(Integer outDetailId){
//        String url = "http://149.129.220.43:8085/manage/projectOut/list_by_outdetailId_ing.do";
        String url = "http://47.100.240.34:8087/manage/projectOut/list_by_outdetailId_ing.do";
//        String url = "http://localhost:8085/manage/projectOut/list_by_outdetailId_ing.do";
        StringBuffer sb = new StringBuffer();
        sb.append("outDetailId=").append(outDetailId);
        String str = Post4.connectionUrl(url, sb,null);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("iel服务系统异常，查询工程信息失败");
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        String statuss = jsonObject.get("status").toString();
        if (statuss.equals("1")) {
            String errMsg = jsonObject.get("msg").toString();
            return ServerResponse.createByErrorMessage(errMsg);
        }
        String Str = jsonObject.get("data").toString();
        ProjectOutVo projectOutVo = JsonUtil.string2Obj(Str,ProjectOutVo.class);
        return ServerResponse.createBySuccess(projectOutVo);
    }



}
