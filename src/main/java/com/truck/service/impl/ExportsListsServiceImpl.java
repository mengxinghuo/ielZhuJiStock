package com.truck.service.impl;

import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.pojo.EntryDetail;
import com.truck.service.FileService;
import com.truck.service.IExportsListsService;
import com.truck.util.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service("iExportsListsService")
public class ExportsListsServiceImpl implements IExportsListsService {

    @Autowired
    private EntryDetailMapper entryDetailMapper;
    @Autowired
    private FileService fileService;


    public ServerResponse bachInsertExports (Integer entryId, String path) {
//        String path =  "/Users/jianhe/Desktop/服务费new.xls";

        //路径地址转化
        List<EntryDetail> list;
        list = null;
        int result=0;
        try {
            if (path != null) {
                list = Excel.loadExportsLists(entryId,path);
                if (list != null) {
                    result = entryDetailMapper.bachInsertExports(list);
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
