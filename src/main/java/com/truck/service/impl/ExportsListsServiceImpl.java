package com.truck.service.impl;

import com.google.common.collect.Lists;
import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.dao.EntryMapper;
import com.truck.pojo.Entry;
import com.truck.pojo.EntryDetail;
import com.truck.service.FileService;
import com.truck.service.IExportsListsService;
import com.truck.util.Excel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service("iExportsListsService")
public class ExportsListsServiceImpl implements IExportsListsService {

    private static  final Logger logger = LoggerFactory.getLogger(ExportsListsServiceImpl.class);

    @Autowired
    private EntryDetailMapper entryDetailMapper;
    @Autowired
    private EntryMapper entryMapper;
    @Autowired
    private FileService fileService;


    public ServerResponse bachInsertExports (Integer entryId, String path) {
//        String path =  "/Users/jianhe/Desktop/服务费new.xls";


        logger.info("传过来的path{}:",path);

        //路径地址转化
        List<EntryDetail> list;
        list = null;
        int result=0;
        try {
            if (path != null) {
                list = Excel.loadExportsLists(entryId,path);
                if (list != null) {
                    Entry entry =entryMapper.selectByPrimaryKey(entryId);
                    List<EntryDetail> insertList = Lists.newArrayList();
                    for (EntryDetail entryDetail : list) {
                        if (entryDetail.getModel().equals("SX3255DR384R"))
                            entryDetail.setModelAlias("6X4自卸车");
                        if (entryDetail.getModel().equals("SX1255DR464R"))
                            entryDetail.setModelAlias("6X4载货车");
                        if (entryDetail.getModel().equals("SX1254DR366R"))
                            entryDetail.setModelAlias("8X4载货车");
                        //todo 一个型号两个别名，应该是笔误
               /*         if (entryDetail.getModel().equals("SX4255JV294R"))
                            entryDetail.setModelAlias("420马力牵引+80吨平板");
                        if (entryDetail.getModel().equals("SX4255JV294R"))
                            entryDetail.setModelAlias("420马力重载牵引车");*/
                        if (entryDetail.getModel().equals("SX4254JR294R"))
                            entryDetail.setModelAlias("340马力牵引+40吨平板");
                        if (entryDetail.getModel().equals("SX5634TQYPV304"))
                            entryDetail.setModelAlias("钢包牵引车");
                        //不从excel读，和transport同步
                            entryDetail.setCustomsClearance(entry.getDeclareNum());
                            entryDetail.setShipNum(entry.getShipNum());
                            entryDetail.setDestination(entry.getDestination());
                        insertList.add(entryDetail);

                    }
                    result = entryDetailMapper.bachInsertExports(insertList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result >0){
            return ServerResponse.createBySuccess("导入成功");
        } else{
            return ServerResponse.createByErrorMessage("导入失败");
        }
    }
}
