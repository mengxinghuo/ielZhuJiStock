package com.truck.service;


import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.ProjectOut;


/**
 * Created by geely
 */
public interface IProjectOutService {

    ServerResponse<PageInfo> listByProjectId(Integer projectId, Integer status, int pageNum, int pageSize);
    ServerResponse<PageInfo> listByOutDetailId(Integer outDetailId, int pageNum, int pageSize);
    ServerResponse listByOutDetailIdIng(Integer outDetailId);
    ServerResponse add(ProjectOut projectOut);
    ServerResponse<String> del(Integer id);
    ServerResponse update(ProjectOut projectOut);

}
