package com.truck.controller.backend;

import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.TransportMapper;
import com.truck.pojo.Admin;
import com.truck.pojo.Entry;
import com.truck.pojo.Transport;
import com.truck.service.FileService;
import com.truck.service.IExportsListsService;
import com.truck.service.ITransportService;
import com.truck.util.JsonUtil;
import com.truck.util.PropertiesUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/manage/transport/")
public class TransportController {

    private static  final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private ITransportService iTransportService;
    @Autowired
    private FileService fileService;
    @Autowired
    private IExportsListsService iExportsListsService;
    @Autowired
    private TransportMapper transportMapper;

    /**
     * 出口录入信息
     * @param session
     * @param transport
     * @return
     */
    @RequestMapping("add_transport.do")
    @ResponseBody
    public ServerResponse addTransport(HttpSession session, String transport){
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
//        if(admin == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
//        }
        Transport transport2 = JsonUtil.string2Obj(transport,Transport.class);
        return iTransportService.addTransport(2,transport2);
    }

    /**
     * 修改运输信息
     * @param session
     * @param transport
     * @return
     */
    @RequestMapping("update_transport.do")
    @ResponseBody
    public ServerResponse updateTransport(HttpSession session,String transport){
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
//        if(admin == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
//        }
        Transport transport2 = JsonUtil.string2Obj(transport,Transport.class);
        return iTransportService.updateTransport(2,transport2);
    }

    /**
     * 删除运输信息
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("del_transport.do")
    @ResponseBody
    public ServerResponse updateTransport(HttpSession session,Integer id){
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
//        if(admin == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
//        }
        return iTransportService.delTransport(2,id);
    }

    /**
     * 批量插入
     * @param entryId
     * @param path
     * @return
     */
    @RequestMapping("batch_insert_exports.do")
    @ResponseBody
    public ServerResponse bachInsertExports(Integer entryId,String path){
        return iExportsListsService.bachInsertExports(entryId, path);
    }

    /**
     * 重导 检验重复 删除
     * @param declareNum
     * @return
     */
    @RequestMapping("check_entry_by_declare_num.do")
    @ResponseBody
    public ServerResponse checkEntryByDeclareNum(String declareNum){
        return iTransportService.checkEntryByDeclareNum(declareNum);
    }

    /**
     * 同步报关船次
     * @param oldDeclareNum
     * @param declareNum
     * @param shipNum
     * @return
     */
    @RequestMapping("update_entry.do")
    @ResponseBody
    public ServerResponse updateEntry(String oldDeclareNum, String declareNum, String shipNum){
        return iTransportService.updateEntry(oldDeclareNum, declareNum, shipNum);
    }


    /**
     * 查询所有列表，带分页 可根据状态查询
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_all_list.do")
    @ResponseBody
    public ServerResponse getAllList(@RequestParam(value = "status",required = false) Integer status,
                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iTransportService.getAllList(status,pageNum,pageSize);
    }

    public Map uploadFileCDNExcel(MultipartFile[] files, HttpServletRequest request,ServerResponse serverResponse) {
        Map resultMap = Maps.newHashMap();
        MultipartFile file = null;
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = null;
        int success = 0;
        String[] urlS = new String[files.length];
        try {
            for (int i = 0; i < files.length; i++) {
                targetFileName = fileService.uploadReturnCDN(files[i], path);
                if (StringUtils.isNotBlank(targetFileName)) {
                    urlS[i] = PropertiesUtil.getProperty("field") + PropertiesUtil.getProperty("uploadUrl") +targetFileName;
                    if(serverResponse.isSuccess()){
                        iExportsListsService.bachInsertExports(Integer.parseInt(serverResponse.getData().toString()),path+"/"+targetFileName);
                        File targetFile = new File(path, targetFileName);
                        Boolean results = targetFile.delete();
                        logger.info("删除结果:{}",results);
                    }
                }
                success++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        resultMap.put("success", true);
        resultMap.put("msg", "成功导入" + success + "文件");
        resultMap.put("file_path", urlS);

        return resultMap;
    }

    /**
     * 直接上传导入
     * @param files
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("upload_excel.do")
    @ResponseBody
    public Map uploadFileCDNExcels(@RequestParam(value = "upload_file", required = false) MultipartFile[] files, HttpServletRequest request,Integer id) {
        Map resultMap = Maps.newHashMap();
        MultipartFile file = null;
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = null;
        int success = 0;
        String[] urlS = new String[files.length];
        try {
            for (int i = 0; i < files.length; i++) {
                targetFileName = fileService.uploadReturnCDN(files[i], path);
                if (StringUtils.isNotBlank(targetFileName)) {

                    urlS[i] = PropertiesUtil.getProperty("field") +targetFileName;
                    targetFileName = targetFileName.substring(targetFileName.lastIndexOf("/")+1);
                    if(id ==null){
                        id=new Random().nextInt(1000);
                    }
                        iExportsListsService.bachInsertExports(id,path+"/"+targetFileName);
                        File targetFile = new File(path, targetFileName);
                        Boolean results = targetFile.delete();
                        logger.info("删除结果:{}",results);
                }
                success++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        resultMap.put("success", true);
        resultMap.put("msg", "成功导入" + success + "文件");
        resultMap.put("file_path", urlS);
        resultMap.put("entryId", id);

        return resultMap;
    }
}
