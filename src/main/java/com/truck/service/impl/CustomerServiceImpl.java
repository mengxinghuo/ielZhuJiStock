package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.CustomerMapper;
import com.truck.pojo.Customer;
import com.truck.service.ICustomerService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iCustomerService")
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public ServerResponse addCustomer(Customer customer){
        if(StringUtils.isEmpty(customer.getCustomerNo()) || StringUtils.isEmpty(customer.getPtName()) || StringUtils.isEmpty(customer.getPhoneNumber()) || StringUtils.isEmpty(customer.getDutyNo())){
            return ServerResponse.createByErrorMessage("信息不完善");
        }
        Customer searchPt = customerMapper.checkOutCustomer(customer.getPtName(),null,null,null);
        if(searchPt != null){
            return ServerResponse.createByErrorMessage("公司名已存在");
        }
        Customer searchNo = customerMapper.checkOutCustomer(null,customer.getCustomerNo(),null,null);
        if(searchNo != null){
            return ServerResponse.createByErrorMessage("编号已存在");
        }
        Customer searchPhone = customerMapper.checkOutCustomer(null,null,customer.getPhoneNumber(),null);
        if(searchPhone != null){
            return ServerResponse.createByErrorMessage("电话已存在");
        }
        customer.setStatus(Const.CustomerStatusEnum.ENABLE.getCode());
        int resultCount = customerMapper.insertSelective(customer);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("创建成功");
        }else{
            return ServerResponse.createByErrorMessage("创建失败");
        }
    }

    public ServerResponse updateCustomer(Customer customer){
        if(StringUtils.isEmpty(customer.getCustomerId())){
            return ServerResponse.createByErrorMessage("请选择要修改的记录");
        }
        if(!StringUtils.isEmpty(customer.getCustomerNo())){
            Customer searchNo = customerMapper.checkOutCustomer(null,customer.getCustomerNo(),null,customer.getCustomerId());
            if(searchNo != null){
                return ServerResponse.createByErrorMessage("编号已存在");
            }
        }
        if(!StringUtils.isEmpty(customer.getPtName())){
            Customer searchPt = customerMapper.checkOutCustomer(customer.getPtName(),null,null,customer.getCustomerId());
            if(searchPt != null){
                return ServerResponse.createByErrorMessage("公司名已存在");
            }
        }
        if(!StringUtils.isEmpty(customer.getPhoneNumber())){
            Customer searchPhone = customerMapper.checkOutCustomer(null,null,customer.getPhoneNumber(),customer.getCustomerId());
            if(searchPhone != null){
                return ServerResponse.createByErrorMessage("电话已存在");
            }
        }
        int resultCount = customerMapper.updateByPrimaryKeySelective(customer);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }else{
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    public ServerResponse getCustomerList(String customerNo,String ptName,Integer status,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if(!StringUtils.isEmpty(customerNo)){
            customerNo = new StringBuilder().append("%").append(customerNo).append("%").toString();
        }
        if(!StringUtils.isEmpty(ptName)){
            ptName = new StringBuilder().append("%").append(ptName).append("%").toString();
        }
        List<Customer> customerList = customerMapper.selectByNoAndName(customerNo,ptName,status);
        if(customerList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到数据");
        }
        List<CustomerVo> customerVoList = Lists.newArrayList();
        for(Customer customerItem : customerList){
            CustomerVo customerVo = this.assembleCustomer(customerItem);
            customerVoList.add(customerVo);
        }
        PageInfo pageInfo = new PageInfo(customerList);
        pageInfo.setList(customerVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getCustomerListOrder(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);

        List<Customer> customerList = customerMapper.selectOrderByName();
        if(customerList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到数据");
        }
        List<CustomerVo> customerVoList = Lists.newArrayList();
        for(Customer customerItem : customerList){
            CustomerVo customerVo = this.assembleCustomer(customerItem);
            customerVoList.add(customerVo);
        }
        PageInfo pageInfo = new PageInfo(customerList);
        pageInfo.setList(customerVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getEnableCustomer(){
        List<Customer> customerList = customerMapper.selectByNoAndName(null,null,0);
        if(customerList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到数据");
        }
        List<CustomerVo> customerVoList = Lists.newArrayList();
        for(Customer customerItem : customerList){
            CustomerVo customerVo = this.assembleCustomer(customerItem);
            customerVoList.add(customerVo);
        }
        return ServerResponse.createBySuccess(customerVoList);
    }

    public ServerResponse getCustomerDetail(Integer customerId){
        if(StringUtils.isEmpty(customerId)){
            return ServerResponse.createByErrorMessage("请选择要查看的客户");
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null){
            return ServerResponse.createByErrorMessage("未查到该客户");
        }else{
            return ServerResponse.createBySuccess(customer);
        }
    }

    public ServerResponse updateIntroduction(Integer customerId,String introduction){
        if(StringUtils.isEmpty(customerId)){
            return ServerResponse.createByErrorMessage("请选择要查看的客户");
        }
        Customer search = customerMapper.selectByPrimaryKey(customerId);
        if(search == null){
            return ServerResponse.createByErrorMessage("未查到该客户");
        }
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setIntroduction(introduction);
        int resultCount = customerMapper.updateByPrimaryKeySelective(customer);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }else{
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    public ServerResponse disableCustomer(Integer customerId,Integer status){
        Customer search = customerMapper.selectByPrimaryKey(customerId);
        if(search == null){
            return ServerResponse.createByErrorMessage("请选择要变动的记录");
        }
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setStatus(status);
        int resultCount = customerMapper.updateByPrimaryKeySelective(customer);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }else {
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    /**
     * 封装customer数据
     * @param customer
     * @return
     */
    public CustomerVo assembleCustomer(Customer customer){
        CustomerVo customerVo = new CustomerVo();
        customerVo.setCustomerId(customer.getCustomerId());
        customerVo.setCustomerNo(customer.getCustomerNo());
        customerVo.setPtName(customer.getPtName());
        if(!StringUtils.isEmpty(customer.getCustomerLevel())){
            customerVo.setCustomerLevel(customer.getCustomerLevel());
        }
        if(!StringUtils.isEmpty(customer.getBusiness())){
            customerVo.setBusiness(customer.getBusiness());
        }
        if(!StringUtils.isEmpty(customer.getArea())){
            customerVo.setArea(customer.getArea());
        }
        if(!StringUtils.isEmpty(customer.getCity())){
            customerVo.setCity(customer.getCity());
        }
        customerVo.setPhoneNumber(customer.getPhoneNumber());
        customerVo.setStatus(customer.getStatus());
        customerVo.setStatusDesc(Const.CustomerStatusEnum.codeOf(customer.getStatus()).getValue());
        customerVo.setCreateTime(DateTimeUtil.dateToStr(customer.getCreateTime()));
        customerVo.setUpdateTime(DateTimeUtil.dateToStr(customer.getUpdateTime()));
        customerVo.setIntroduction(customer.getIntroduction());
        customerVo.setDutyNo(customer.getDutyNo());
        return customerVo;
    }
}
