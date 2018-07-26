package com.truck.service;


import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Project;
import com.truck.vo.ProjectVo;


/**
 * Created by geely
 */
public interface IProjectService {

    ServerResponse<PageInfo> listByCustomerId(Integer customerId, int pageNum, int pageSize);
    ServerResponse add(Project project);
    ServerResponse<String> del(Integer id);
    ServerResponse update(Project project);
    ProjectVo assembleProjectVo(Project project);

}
