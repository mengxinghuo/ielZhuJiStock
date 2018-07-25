package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.OutDetailMapper;
import com.truck.dao.ProjectMapper;
import com.truck.dao.ProjectOutMapper;
import com.truck.pojo.OutDetail;
import com.truck.pojo.ProjectOut;
import com.truck.service.IProjectOutService;
import com.truck.service.IProjectOutService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.ProjectOutVo;
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
@Service("projectOutOutService")
public class IProjectOutServiceImpl implements IProjectOutService {

    private Logger logger = LoggerFactory.getLogger(IProjectOutServiceImpl.class);

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectOutMapper projectOutMapper;
    @Autowired
    private OutDetailMapper outDetailMapper;

    private ProjectOutVo assembleProductVo(ProjectOut projectOut){
        ProjectOutVo projectOutVo = new ProjectOutVo();
        projectOutVo.setId(projectOut.getId());
        projectOutVo.setOutDetailId(projectOut.getOutDetailId());
        projectOutVo.setProjectId(projectOut.getProjectId());
        projectOutVo.setStatus(projectOut.getStatus());
        projectOutVo.setCreateTime(DateTimeUtil.dateToStr(projectOut.getCreateTime()));
        projectOutVo.setUpdateTime(DateTimeUtil.dateToStr(projectOut.getUpdateTime()));
        return projectOutVo;
    }

    public ServerResponse<PageInfo> listByProjectId(Integer projectId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectOut> projectOuts = projectOutMapper.list(projectId);
        List<ProjectOutVo> projectOutVos = Lists.newArrayList();
        for (ProjectOut projectOut : projectOuts) {
            ProjectOutVo projectOutVo = this.assembleProductVo(projectOut);
            projectOutVos.add(projectOutVo);
        }
        PageInfo pageResult = new PageInfo(projectOuts);
        pageResult.setList(projectOutVos);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse add(ProjectOut projectOut) {
        if(projectOut.getOutDetailId() == null){
            return ServerResponse.createByErrorMessage("请选择设备");
        }
        if(projectOut.getProjectId() == null){
            return ServerResponse.createByErrorMessage("请选择工程");
        }
        //status 0 不用，1用
        List<ProjectOut> projectOuts = projectOutMapper.selectByOutIdProIdStatus(projectOut.getOutDetailId(),projectOut.getProjectId(), Const.ProjectOutStatusEnum.IN_WORKING.getCode());
        if(projectOuts.size()>0){
            return ServerResponse.createByErrorMessage("该设备已经在该工程下工作");
        }
        List<ProjectOut> projectOuts2 = projectOutMapper.selectByOutIdProIdStatus(projectOut.getOutDetailId(),null, Const.ProjectOutStatusEnum.IN_WORKING.getCode());
        if(projectOuts2.size()==1){
            ProjectOut projectOut2 = projectOuts2.get(0);
            projectOut2.setStatus(Const.ProjectOutStatusEnum.NON_WORKING.getCode());
            projectOutMapper.updateByPrimaryKeySelective(projectOut2);
        }
        projectOut.setStatus(Const.ProjectOutStatusEnum.IN_WORKING.getCode());
        int rowCount = projectOutMapper.insertSelective(projectOut);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", projectOut.getId());
            return ServerResponse.createBySuccess("新建工程设备信息成功", result);
        }
        return ServerResponse.createByErrorMessage("新建工程设备信息成功");
    }

    public ServerResponse<String> del(Integer id) {
        int resultCount = projectOutMapper.deleteByPrimaryKey( id);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除工程设备信息成功");
        }
        return ServerResponse.createByErrorMessage("删除工程设备信息失败");
    }


    public ServerResponse update(ProjectOut projectOut) {
        if(projectOut.getId() == null ){
            return ServerResponse.createByErrorMessage("请选择工程");
        }
        return ServerResponse.createByErrorMessage("不用update");
    }
}


