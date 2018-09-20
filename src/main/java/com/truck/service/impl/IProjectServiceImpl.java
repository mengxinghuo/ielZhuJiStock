package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ProjectMapper;
import com.truck.pojo.Customer;
import com.truck.pojo.Project;
import com.truck.service.IProjectService;
import com.truck.util.DateTimeUtil;
import com.truck.util.JsonUtil;
import com.truck.util.Post4;
import com.truck.vo.ProjectVo;
import net.sf.json.JSONObject;
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

    public ProjectVo assembleProjectVo(Project project){
        ProjectVo projectVo = new ProjectVo();
        projectVo.setId(project.getId());
        projectVo.setName(project.getName());
        projectVo.setCustomerId(project.getCustomerId());
        projectVo.setStatus(project.getStatus());
        projectVo.setCreateTime(DateTimeUtil.dateToStr(project.getCreateTime()));
        projectVo.setUpdateTime(DateTimeUtil.dateToStr(project.getUpdateTime()));

        return projectVo;
    }

    public ServerResponse<PageInfo> listByCustomerId(Integer customerId, int pageNum, int pageSize){
        String url = "http://localhost:8087/manage/project/list.do";
//        String url = "http://localhost:8090/manage/customer/get_customer_detail.do";
        StringBuffer sb = new StringBuffer();
        sb.append("customerId=").append(customerId).append("&pageNum=").append(pageNum).append("&pageSize=").append(pageSize);
        String str = Post4.connectionUrl(url, sb,null);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("iel配件系统异常，查询工程信息失败");
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        String statuss = jsonObject.get("status").toString();
        if (statuss.equals("1")) {
            String errMsg = jsonObject.get("msg").toString();
            return ServerResponse.createByErrorMessage(errMsg);
        }
        String Str = jsonObject.get("data").toString();
        PageInfo pageResult = JsonUtil.string2Obj(Str,PageInfo.class);
     /*   PageHelper.startPage(pageNum, pageSize);
        List<Project> projectList = projectMapper.selectByCustomerId(customerId);
        List<ProjectVo> projectVos = Lists.newArrayList();
        for (Project project : projectList) {
            ProjectVo projectVo = this.assembleProjectVo(project);
            projectVos.add(projectVo);
        }
        PageInfo pageResult = new PageInfo(projectVos);*/
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse add(Project project) {
        if(StringUtils.isEmpty(project.getName())){
            return ServerResponse.createByErrorMessage("请填写工程名字");
        }
        if(project.getCustomerId()==null){
            return ServerResponse.createByErrorMessage("请选择工程");
        }
        List<Project> projects1 = projectMapper.selectByNameCusIdStatus(project.getName(),project.getCustomerId(),null);
        if(projects1.size()>0){
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

    public ServerResponse<String> del(Integer id) {
        int resultCount = projectMapper.deleteByPrimaryKey( id);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除工程信息成功");
        }
        return ServerResponse.createByErrorMessage("删除工程信息失败");
    }


    public ServerResponse update(Project project) {
        if(project.getId() == null ){
            return ServerResponse.createByErrorMessage("请选择工程");
        }
        if(StringUtils.isEmpty(project.getName())){
            return ServerResponse.createByErrorMessage("请填写工程名字");
        }
        Project project2 = projectMapper.selectByPrimaryKey(project.getId());
        List<Project> projects1 = projectMapper.selectByNameCusIdStatus(project.getName(),project2.getCustomerId(),null);
        if(projects1.size()>0){
            return ServerResponse.createByErrorMessage("工程名重复");
        }
        int rowCount = projectMapper.updateByPrimaryKeySelective(project);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新工程信息成功");
        }
        return ServerResponse.createByErrorMessage("更新工程信息失败");
    }
}


