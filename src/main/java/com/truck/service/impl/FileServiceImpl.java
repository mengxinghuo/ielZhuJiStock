package com.truck.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.service.FileService;
import com.truck.util.DateTimeUtil;
import com.truck.util.FTPUtil;
import com.truck.util.PropertiesUtil;
import main.java.com.UpYun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by geely
 */
@Service("iFileService")
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private String bucketName = PropertiesUtil.getProperty("bucketName"),
            userName = PropertiesUtil.getProperty("userName"),
            passWord = PropertiesUtil.getProperty("passWord"),
            uploadUrl = PropertiesUtil.getProperty("uploadUrl");


    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
           /* fileDir.setWritable(true);*/
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }

    public String uploadCDN(MultipartFile file, String path) throws IOException {
        UpYun upyun = new UpYun(bucketName, userName, passWord);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
//        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        Date date = new Date();
        String dateStr = DateTimeUtil.dateToStr(date,"yyyy-MM-dd");
        String uploadFileName = dateStr+fileName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        file.transferTo(targetFile);
        boolean result = upyun.writeFile(uploadUrl + uploadFileName,
                targetFile, true);
        targetFile.delete();
        if (result) {
            return uploadUrl + uploadFileName;
        }
        return null;
    }

    public String downloadCDN(String path) throws IOException {
        UpYun upyun = new UpYun(bucketName, userName, passWord);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        File fileDir = new File("/usr/");
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        boolean result = upyun.readFile(path,fileDir);
        if (result) {
            return path.replace("http://cdn.ayotrust.com/upload/","/usr/");
        }
        return null;
    }

    public String uploadReturnCDN(MultipartFile file, String path) throws IOException {
        UpYun upyun = new UpYun(bucketName, userName, passWord);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        Date date = new Date();
        String dateStr = DateTimeUtil.dateToStr(date,"yyyy-MM-dd");
        String uploadFileName = dateStr+fileName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        file.transferTo(targetFile);
        boolean result = upyun.writeFile(uploadUrl + uploadFileName,
                targetFile, true);
//        targetFile.delete();
        if (result) {
            String remoteFilePath = PropertiesUtil.getProperty("field") +uploadUrl+uploadFileName;
            File file2 = new File(path); // 创建一个本地临时文件
            boolean result2= upyun.readFile(remoteFilePath, file2);
            return uploadFileName;
        }
        return null;
    }

    public Map<String,String> delCDN( String urls) throws IOException {
        Map fileMap = Maps.newHashMap();
        UpYun upyun = new UpYun(bucketName, userName, passWord);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);

        List<String> urlList = Splitter.on(",").splitToList(urls);
        for (String url : urlList) {
            String path = url.substring(url.indexOf("com")+3);

            boolean result = upyun.deleteFile(path);
            fileMap.put(url,result);
        }
        return fileMap;
    }
}
