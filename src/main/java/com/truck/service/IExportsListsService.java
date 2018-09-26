package com.truck.service;

import com.truck.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface IExportsListsService {
    ServerResponse bachInsertExports(Integer entryId,String path);
}
