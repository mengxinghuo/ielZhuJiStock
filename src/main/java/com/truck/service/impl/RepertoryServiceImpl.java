package com.truck.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.RepertoryMapper;
import com.truck.pojo.Repertory;
import com.truck.service.IRepertoryService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.RepertoryVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by geely
 */
@Service("iRepertoryService")
public class RepertoryServiceImpl implements IRepertoryService {

    private Logger logger = LoggerFactory.getLogger(RepertoryServiceImpl.class);

    @Autowired
    private RepertoryMapper repertoryMapper;

    public ServerResponse addRepertory(Integer adminId, String repertoryName, Integer parentId, String code) {
        if (StringUtils.isBlank(repertoryName) || StringUtils.isBlank(code)) {
            return ServerResponse.createByErrorMessage("添加仓库参数错误");
        }

        Repertory repertory = new Repertory();
        repertory.setAdminId(adminId);
        repertory.setParentId(parentId);
        repertory.setStatus(true);//这个分类是可用的
        List<Repertory> repertories = repertoryMapper.selectRepertoryChildrenByParentId(repertory.getParentId());
        for (Repertory repertory1 : repertories) {
            if (repertory1.getName() == repertoryName)
                return ServerResponse.createByErrorMessage("名字重复");
            if (repertory1.getCode() == code)
                return ServerResponse.createByErrorMessage("编码重复");
        }
        repertory.setName(repertoryName);
        repertory.setCode(code);

        int rowCount = repertoryMapper.insertSelective(repertory);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", repertory.getId());
            return ServerResponse.createBySuccess("添加仓库成功", result);
        }
        return ServerResponse.createByErrorMessage("添加仓库失败");
    }

    public ServerResponse updateRepertoryName(Integer repertoryId, String repertoryName, String code) {
        if (repertoryId == null) {
            return ServerResponse.createByErrorMessage("更新仓库参数错误");
        }
        if (StringUtils.isBlank(repertoryName) && StringUtils.isBlank(code)) {
            return ServerResponse.createByErrorMessage("更新仓库参数错误");
        }
        Repertory repertory = repertoryMapper.selectByPrimaryKey(repertoryId);
        List<Repertory> repertories = repertoryMapper.selectRepertoryChildrenByParentId(repertory.getParentId());
        for (Repertory repertory1 : repertories) {
            if (StringUtils.isNotBlank(repertoryName)) {
                if (repertory1.getName() == repertoryName)
                    return ServerResponse.createByErrorMessage("名字重复");
                repertory.setName(repertoryName);
            }
            if (StringUtils.isNotBlank(code)) {
                if (repertory1.getCode() == code)
                    return ServerResponse.createByErrorMessage("编码重复");
                repertory.setCode(code);
            }
        }

        int rowCount = repertoryMapper.updateByPrimaryKeySelective(repertory);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新仓库参数成功");
        }
        return ServerResponse.createByErrorMessage("更新仓库参数失败");
    }

    public ServerResponse<List<Repertory>> getChildrenParallelRepertory(Integer repertoryId){
        List<Repertory> repertoryList = repertoryMapper.selectRepertoryChildrenByParentId(repertoryId);
        if(CollectionUtils.isEmpty(repertoryList)){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(repertoryList);
    }


    /**
     * 递归查询本节点的id及孩子节点的id
     * @param repertoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectRepertoryAndChildrenById(Integer repertoryId){
        Set<Repertory> repertorySet = Sets.newHashSet();
        findChildRepertory(repertorySet,repertoryId);

        List<Integer> repertoryIdList = Lists.newArrayList();
        if(repertoryId != null){
            for(Repertory repertoryItem : repertorySet){
                repertoryIdList.add(repertoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(repertoryIdList);
    }

    /**
     * 递归查询本节点及孩子节点的对象
     * @param repertoryId
     * @return
     */
    public ServerResponse selectRepertoryObjectAndChildrenById(Integer repertoryId){
        List<RepertoryVo> list = Lists.newArrayList();
        list = findChildRepertorys(repertoryId);
        return ServerResponse.createBySuccess(list);
    }


    //递归算法,算出子节点
    private List<RepertoryVo> findChildRepertorys(Integer repertoryId){
        List<RepertoryVo> repertoryVoList = new ArrayList();
        //查找子节点,递归算法一定要有一个退出的条件
        List<Repertory> repertoryList = repertoryMapper.selectRepertoryChildrenByParentId(repertoryId);
        if(repertoryList.size() > 0){
            for(Repertory repertoryItem : repertoryList){
                RepertoryVo repertoryVo = this.assembleRepertory(repertoryItem);
                List<RepertoryVo> search = findChildRepertorys(repertoryItem.getId());
                repertoryVo.setRepertoryVoList(search);
                repertoryVoList.add(repertoryVo);
            }
        }
        return repertoryVoList;
    }


    //递归算法,算出子节点
    private Set<Repertory> findChildRepertory(Set<Repertory> repertorySet , Integer repertoryId){
        Repertory repertory = repertoryMapper.selectByPrimaryKey(repertoryId);
        if(repertory != null){
            repertorySet.add(repertory);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Repertory> repertoryList = repertoryMapper.selectRepertoryChildrenByParentId(repertoryId);
        for(Repertory repertoryItem : repertoryList){
            findChildRepertory(repertorySet,repertoryItem.getId());
        }
        return repertorySet;
    }

    public ServerResponse deleteById(Integer categoryId) {
        if (StringUtils.isBlank(String.valueOf(categoryId))) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> integers = this.selectRepertoryAndChildrenById(categoryId).getData();
        if (CollectionUtils.isNotEmpty(integers)) {
            int i = repertoryMapper.deleteByPrimaryKeyByIdList(integers);
            if (i > 0) {
                return ServerResponse.createBySuccessMessage("删除节点成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("没有此节点");
        }
        return ServerResponse.createByErrorMessage("删除节点失败");
    }

    public List<Integer> findDeepParentId(List<Integer> idList , Integer id){
        Repertory repertory = repertoryMapper.selectByPrimaryKey(id);
        if(repertory != null){
            idList.add(repertory.getId());
            //查找父节点,递归算法一定要有一个退出的条件
            if(repertory.getParentId() >0){
                findDeepParentId(idList,repertory.getParentId());
            }
        }
        return idList;
    }

    public RepertoryVo assembleRepertory(Repertory repertory){
        RepertoryVo repertoryVo = new RepertoryVo();
        repertoryVo.setId(repertory.getId());
        repertoryVo.setAdminId(repertory.getAdminId());
        repertoryVo.setParentId(repertory.getParentId());
        repertoryVo.setName(repertory.getName());
        repertoryVo.setCode(repertory.getCode());
        //repertoryVo.setStatus(repertory.getStatus());
        repertoryVo.setCreateTime(DateTimeUtil.dateToStr(repertory.getCreateTime()));
        repertoryVo.setUpdateTime(DateTimeUtil.dateToStr(repertory.getUpdateTime()));
        return repertoryVo;
    }

}
