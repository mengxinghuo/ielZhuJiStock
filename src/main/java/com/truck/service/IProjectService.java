package com.truck.service;


import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Project;


/**
 * Created by geely
 */
public interface IProjectService {

    ServerResponse<PageInfo> listByProductId(Integer adminId, int pageNum, int pageSize);
    ServerResponse add(Project project);
    ServerResponse<String> del(Integer id);
    ServerResponse update(Project project);

}
