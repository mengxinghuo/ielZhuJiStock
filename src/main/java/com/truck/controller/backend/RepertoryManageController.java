package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.IAdminService;
import com.truck.service.IRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/manage/repertory")
public class RepertoryManageController {

    @Autowired
    private IRepertoryService iRepertoryService;
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse addRepertory(HttpSession session, String name, @RequestParam(value = "parentId",defaultValue = "0") int parentId, String code){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iRepertoryService.addRepertory(admin.getAdminId(),name,parentId,code);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    @RequestMapping("setName.do")
    @ResponseBody
    public ServerResponse setRepertoryName(HttpSession session, Integer id,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "code", required = false) String code){
       Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iRepertoryService.updateRepertoryName(id,name,code);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    @RequestMapping("get.do")
    @ResponseBody
    public ServerResponse getChildrenParallelRepertory(HttpSession session, @RequestParam(value = "id" ,defaultValue = "0") Integer id){
       Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            //查询子节点的repertory信息,并且不递归,保持平级
            return iRepertoryService.getChildrenParallelRepertory(id);
    }

    /**
     * 得到当前节点的id和递归子节点的id
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("getDeep.do")
    @ResponseBody
    public ServerResponse getRepertoryAndDeepChildrenRepertory(HttpSession session, @RequestParam(value = "id" ,defaultValue = "0") Integer id){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        //查询当前节点的id和递归子节点的id
        return iRepertoryService.selectRepertoryAndChildrenById(id);
    }

    /**
     * 得到当前节点的id和递归子节点的对象
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("getDeepRepertory.do")
    @ResponseBody
    public ServerResponse getRepertoryAndDeepChildrenRepertoryObject(HttpSession session, @RequestParam(value = "id" ,defaultValue = "0") Integer id){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        //查询当前节点的id和递归子节点的id
        return iRepertoryService.selectRepertoryObjectAndChildrenById(id);
    }

    @RequestMapping("del.do")
    @ResponseBody
    //删除分类
    public ServerResponse addRepertory(HttpSession session, Integer id) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
            return iRepertoryService.deleteById(id);
    }

}
