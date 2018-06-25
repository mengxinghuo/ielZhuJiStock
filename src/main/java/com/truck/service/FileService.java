package com.truck.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by geely
 */
public interface FileService {

    String upload(MultipartFile file, String path);

    String uploadCDN(MultipartFile file, String path) throws IOException;

    String downloadCDN(String path) throws IOException;

    String uploadReturnCDN(MultipartFile file, String path) throws IOException;

    Map<String,String> delCDN(String urls) throws IOException;
}
