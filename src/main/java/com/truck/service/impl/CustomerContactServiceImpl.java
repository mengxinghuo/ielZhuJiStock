package com.truck.service.impl;

import com.truck.common.ServerResponse;
import com.truck.dao.CustomerContactMapper;
import com.truck.dao.CustomerMapper;
import com.truck.pojo.Customer;
import com.truck.pojo.CustomerContact;
import com.truck.service.ICustomerContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iCustomerContactService")
public class CustomerContactServiceImpl implements ICustomerContactService {

    @Autowired
    private CustomerContactMapper customerContactMapper;
    @Autowired
    private CustomerMapper customerMapper;

    public ServerResponse addCustomerContact(CustomerContact customerContact){
        if(StringUtils.isEmpty(customerContact.getCustomerId())){
            return ServerResponse.createByErrorMessage("请选择客户");
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerContact.getCustomerId());
        if(customer == null){
            return ServerResponse.createByErrorMessage("客户信息不存在");
        }
        if(StringUtils.isEmpty(customerContact.getContactName()) || StringUtils.isEmpty(customerContact.getEmail()) || StringUtils.isEmpty(customerContact.getJobPosition()) || StringUtils.isEmpty(customerContact.getPhone())){
            return ServerResponse.createByErrorMessage("信息不完整");
        }
        CustomerContact searchEmail = customerContactMapper.checkOutCustomerContact(customerContact.getEmail(),null,null);
        if(searchEmail != null){
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }
        CustomerContact searchPhone = customerContactMapper.checkOutCustomerContact(null,customerContact.getPhone(),null);
        if(searchPhone != null){
            return ServerResponse.createByErrorMessage("电话已存在");
        }
        int resultCount = customerContactMapper.insertSelective(customerContact);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("新增成功");
        }else{
            return ServerResponse.createByErrorMessage("新增失败");
        }
    }

    public ServerResponse updateCustomerContact(CustomerContact customerContact){
        if(StringUtils.isEmpty(customerContact.getContactId())){
            return ServerResponse.createByErrorMessage("请选择要修改的信息");
        }
        if(!StringUtils.isEmpty(customerContact.getEmail())){
            CustomerContact searchEmail = customerContactMapper.checkOutCustomerContact(customerContact.getEmail(),null,customerContact.getContactId());
            if(searchEmail != null){
                return ServerResponse.createByErrorMessage("邮箱已存在");
            }
        }
        if(!StringUtils.isEmpty(customerContact.getPhone())){
            CustomerContact searchPhone = customerContactMapper.checkOutCustomerContact(null,customerContact.getPhone(),customerContact.getContactId());
            if(searchPhone != null){
                return ServerResponse.createByErrorMessage("电话已存在");
            }
        }
        int resultCount = customerContactMapper.updateByPrimaryKeySelective(customerContact);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("新增成功");
        }else{
            return ServerResponse.createByErrorMessage("新增失败");
        }
    }

    public ServerResponse getCustomerContact(Integer customerId){
        if(StringUtils.isEmpty(customerId)){
            return ServerResponse.createByErrorMessage("请选择客户");
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null){
            return ServerResponse.createByErrorMessage("客户不存在");
        }
        List<CustomerContact> customerContactList = customerContactMapper.selectByCustomerId(customerId);
        if(customerContactList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到信息");
        }
        return ServerResponse.createBySuccess(customerContactList);
    }
}
