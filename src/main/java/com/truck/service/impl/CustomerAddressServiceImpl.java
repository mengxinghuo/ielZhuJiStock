package com.truck.service.impl;

import com.truck.common.ServerResponse;
import com.truck.dao.CustomerAddressMapper;
import com.truck.dao.CustomerMapper;
import com.truck.pojo.Customer;
import com.truck.pojo.CustomerAddress;
import com.truck.service.ICustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iCustomerAddressService")
public class CustomerAddressServiceImpl implements ICustomerAddressService {

    @Autowired
    private CustomerAddressMapper customerAddressMapper;
    @Autowired
    private CustomerMapper customerMapper;

    public ServerResponse addCustomerAddress(CustomerAddress customerAddress){
        if(StringUtils.isEmpty(customerAddress.getCustomerId())){
            return ServerResponse.createByErrorMessage("请选择客户");
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerAddress.getCustomerId());
        if(customer == null){
            return ServerResponse.createByErrorMessage("客户信息不存在");
        }
        if(StringUtils.isEmpty(customerAddress.getAddress())){
            return ServerResponse.createByErrorMessage("请输入地址信息");
        }
        int resultCount = customerAddressMapper.insertSelective(customerAddress);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("新增成功");
        }else{
            return ServerResponse.createByErrorMessage("新增失败");
        }
    }

    public ServerResponse updateCustomerAddress(CustomerAddress customerAddress){
        if(StringUtils.isEmpty(customerAddress.getAddressId())){
            return ServerResponse.createByErrorMessage("请选择要修改的信息");
        }
        if(StringUtils.isEmpty(customerAddress.getAddress())){
            return ServerResponse.createByErrorMessage("地址不可为空");
        }
        int resultCount = customerAddressMapper.updateByPrimaryKeySelective(customerAddress);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }else{
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    public ServerResponse getCustomerAddress(Integer customerId){
        if(StringUtils.isEmpty(customerId)){
            return ServerResponse.createByErrorMessage("请选择客户");
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null){
            return ServerResponse.createByErrorMessage("客户信息不存在");
        }
        List<CustomerAddress> customerAddressList = customerAddressMapper.selectByCustomerId(customerId);
        if(customerAddressList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到信息");
        }
        return ServerResponse.createBySuccess(customerAddressList);
    }

}
