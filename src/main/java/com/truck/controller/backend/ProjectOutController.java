package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.ProjectOut;
import com.truck.service.IProjectOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/manage/projectOut/")
public class ProjectOutController {

    @Autowired
    private IProjectOutService iProjectOutService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageNum",defaultValue = "10") int pageSize,
                                         Integer projectId, Integer status){
        return iProjectOutService.listByProjectId(projectId,status,pageNum,pageSize);
    }

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, ProjectOut projectOut) {
        return iProjectOutService.add(projectOut);
    }



    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer id) {
        return iProjectOutService.del(id);
    }

    /**
     * 解绑
     * @param session
     * @param projectOut
     * @return
     */
    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, ProjectOut projectOut) {
        return iProjectOutService.update(projectOut);
    }

    @RequestMapping("list_by_outdetailId.do")
    @ResponseBody
    public ServerResponse<PageInfo> listByOutDetailId(HttpSession session,
                                                      @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                      @RequestParam(value = "pageNum",defaultValue = "10") int pageSize,
                                                      Integer outDetailId){
        return iProjectOutService.listByOutDetailId(outDetailId,pageNum,pageSize);
    }

    @RequestMapping("list_by_outdetailId_ing.do")
    @ResponseBody
    public ServerResponse<PageInfo> listByOutDetailIdIng(HttpSession session,
                                                         Integer outDetailId){
        return iProjectOutService.listByOutDetailIdIng(outDetailId);
    }


}