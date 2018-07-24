package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.OutDetailMapper;
import com.truck.dao.ProjectMapper;
import com.truck.pojo.OutDetail;
import com.truck.pojo.Project;
import com.truck.service.IProjectService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.ProjectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("projectService")
public class IProjectServiceImpl implements IProjectService {

    private Logger logger = LoggerFactory.getLogger(IProjectServiceImpl.class);

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private OutDetailMapper outDetailMapper;

    private ProjectVo assembleProductVo(Project project){
        ProjectVo projectVo = new ProjectVo();
        projectVo.setId(project.getId());
        projectVo.setName(project.getName());
        projectVo.setProductId(project.getProductId());
        projectVo.setCreateTime(DateTimeUtil.dateToStr(project.getCreateTime()));
        projectVo.setUpdateTime(DateTimeUtil.dateToStr(project.getUpdateTime()));
        projectVo.setAdminId(project.getAdminId());

        OutDetail outDetail = outDetailMapper.selectByPrimaryKey(project.getProductId());
        return projectVo;
    }

    public ServerResponse<PageInfo> listByProductId(Integer adminId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<String> nameList = projectMapper.listNameByAdminId(adminId);
        List<ProjectVo> projectVos = Lists.newArrayList();
        for (String name : nameList) {
            ProjectVo projectVo = new ProjectVo();
            projectVo.setName(name);
            List<Project> projectList = projectMapper.selectByName(name);
            if(projectList.size()>0){
                projectVo.setId(projectList.get(0).getId());
            }
            List<OutDetail> outDetailList = Lists.newArrayList();
            for (Project project : projectList) {
                OutDetail outDetail = outDetailMapper.selectByPrimaryKey(project.getProductId());
                outDetailList.add(outDetail);
            }
            projectVo.setOutDetails(outDetailList);
            projectVos.add(projectVo);
        }
        PageInfo pageResult = new PageInfo(projectVos);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse add(Project project) {
        if(StringUtils.isEmpty(project.getName())){
            return ServerResponse.createByErrorMessage("请填写工程名字");
        }
        List<Project> project1 = projectMapper.selectByName(project.getName());
        if(project1.size()>0){
            return ServerResponse.createByErrorMessage("工程名重复");
        }
        int rowCount = projectMapper.insertSelective(project);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", project.getId());
            return ServerResponse.createBySuccess("新建工程信息成功", result);
        }
        return ServerResponse.createByErrorMessage("新建工程信息成功");
    }

    public ServerResponse<String> del(Integer componentId) {
        int resultCount = projectMapper.deleteById( componentId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除工程信息成功");
        }
        return ServerResponse.createByErrorMessage("删除工程信息失败");
    }


    public ServerResponse update(Project project) {
        if(project.getId() == null){
            return ServerResponse.createByErrorMessage("请选择工程");
        }
        List<Project> project1 = projectMapper.selectByName(project.getName());
        for (Project project2 : project1) {
            if(StringUtils.isEmpty(project2.getProductId())){
                project2.setProductId(project.getProductId());
                int rowCount = projectMapper.updateByPrimaryKeySelective(project);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新工程信息成功");
                }
                return ServerResponse.createByErrorMessage("更新工程信息失败");
            }
        }
        Project project3 = new Project();
        project3.setName(project.getName());
        project3.setProductId(project.getProductId());
        project3.setAdminId(project1.get(0).getAdminId());
        int rowCount = projectMapper.insertSelective(project3);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新工程信息成功");
        }
        return ServerResponse.createByErrorMessage("更新工程信息失败");
    }
}


