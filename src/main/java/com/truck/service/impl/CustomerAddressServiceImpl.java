package com.truck.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.CustomerAddressMapper;
import com.truck.dao.CustomerMapper;
import com.truck.pojo.Customer;
import com.truck.pojo.CustomerAddress;
import com.truck.pojo.Repertory;
import com.truck.pojo.RepertoryPei;
import com.truck.service.ICustomerAddressService;
import com.truck.util.BigDecimalUtil;
import com.truck.util.JsonUtil;
import com.truck.util.Point2;
import com.truck.util.Post4;
import com.truck.vo.ProjectOutVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        ServerResponse serverResponse = this.getRepertory();
        List<RepertoryPei> repertoryList = Lists.newArrayList();
        if(serverResponse.isSuccess()){
            repertoryList = (List<RepertoryPei>) serverResponse.getData();
        }
        if(repertoryList.size()>0){
            for (CustomerAddress customerAddress : customerAddressList) {
                    Map map = Maps.newHashMap();
                    for (RepertoryPei repertoryPei : repertoryList) {
                    BigDecimal jing1 = repertoryPei.getPositionLatitude();
                    BigDecimal wei1 = repertoryPei.getPositionLongitude();
                    BigDecimal jing2 = customerAddress.getPositionLatitude();
                    BigDecimal wei2 = customerAddress.getPositionLongitude();
                    Double distance = Point2.getDistance(jing1.doubleValue(),wei1.doubleValue(),jing2.doubleValue(),wei2.doubleValue());
                    BigDecimal totalDistance=BigDecimalUtil.div(distance,1000);
                    map.put(repertoryPei.getName(),totalDistance);
                }
                    customerAddress.setDistanceMap(map);
            }
        }
        return ServerResponse.createBySuccess(customerAddressList);
    }

    public ServerResponse getRepertory(){
        String url = "http://47.100.240.34:8085/manage/repertory/get.do";
        StringBuffer sb = new StringBuffer();
//        sb.append("outDetailId=").append(outDetailId);
        String str = Post4.connectionUrl(url, sb,null);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("iel配件系统异常，查询仓库信息失败");
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        String statuss = jsonObject.get("status").toString();
        if (statuss.equals("1")) {
            String errMsg = jsonObject.get("msg").toString();
            return ServerResponse.createByErrorMessage(errMsg);
        }
        String Str = jsonObject.get("data").toString();
        List<RepertoryPei> repertoryList = JsonUtil.string2Obj(Str,List.class,RepertoryPei.class);
        return ServerResponse.createBySuccess(repertoryList);
    }

}
