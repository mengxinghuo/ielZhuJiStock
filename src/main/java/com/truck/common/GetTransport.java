package com.truck.common;

import com.truck.pojo.Entry;
import com.truck.pojo.Transport;
import com.truck.util.JsonUtil;
import com.truck.util.Post4;
import net.sf.json.JSONObject;

public class GetTransport {

    public static ServerResponse<Transport> getTranport(Entry entry){
            String url = "http://39.104.139.229:8085/manage/transport/get_trans_by_entry.do";
            StringBuffer sb = new StringBuffer();
            JSONObject json = JSONObject.fromObject(entry);
            sb.append("entry=").append(json.toString());
            String str = Post4.connectionUrl(url, sb,null);
            if (str.equals("error")) {
                return ServerResponse.createByErrorMessage("iel配件系统异常，查询进出口信息失败！！！");
            }
            JSONObject jsonObject = JSONObject.fromObject(str);
            String statuss = jsonObject.get("status").toString();
            if (statuss.equals("1")) {
                String errMsg = jsonObject.get("msg").toString();
                return ServerResponse.createByErrorMessage(errMsg);
            }
            String Str = jsonObject.get("data").toString();
            Transport transport = JsonUtil.string2Obj(Str,Transport.class);
            return ServerResponse.createBySuccess(transport);
    }


//    Transport transport = (Transport)GetTransport.getTranport(entry).getData();
//        if(transport==null){
//        entryVo.setShipNum(entry.getShipNum());
//        entryVo.setDeclareNum(entry.getDeclareNum());
//        entryVo.setDestination(entry.getDestination());
//    }else{
//        entryVo.setShipNum(transport.getShipNum());
//        entryVo.setDeclareNum(transport.getDeclareNum());
//        entryVo.setDestination(transport.getDestination());
//    }

}
