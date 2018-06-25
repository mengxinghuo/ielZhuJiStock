package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Repertory;

import java.util.List;

/**
 * Created by geely
 */
public interface IRepertoryService {
    ServerResponse addRepertory(Integer adminId, String categoryName, Integer parentId, String code);
    ServerResponse updateRepertoryName(Integer categoryId, String categoryName, String code);
    ServerResponse<List<Repertory>> getChildrenParallelRepertory(Integer categoryId);
    ServerResponse<List<Integer>> selectRepertoryAndChildrenById(Integer categoryId);

    ServerResponse selectRepertoryObjectAndChildrenById(Integer categoryId);

    ServerResponse deleteById(Integer categoryId);

    List<Integer> findDeepParentId(List<Integer> idList , Integer id);

}
