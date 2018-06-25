package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;


    /**
     * 管理员登陆
     * @param adminName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("login.do")
    @ResponseBody
    public ServerResponse<Admin> login(String adminName, String password, HttpSession session){
        ServerResponse<Admin> response=iAdminService.login(adminName,password);
        if (response.isSuccess()){
            Admin admin=response.getData();
                session.setAttribute(Const.CURRENT_ADMIN,admin);
                return response;

        }
            return response;
    }


    /**
     * 退出登陆
     * @param session
     * @return
     */
    @RequestMapping("logout.do")
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_ADMIN);
        return ServerResponse.createBySuccess();
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping("register.do")
    @ResponseBody
    public ServerResponse<String> register(HttpSession session,Admin admin){
            return  iAdminService.register(admin);
    }

    /**
     * 校验
     * @param str
     * @param type
     * @return
     */
    @RequestMapping("check_valid.do")
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iAdminService.checkValid(str,type);
    }

    /**
     * 获取管理员登陆信息
     * @param session
     * @return
     */
    @RequestMapping("get_admin_info.do")
    @ResponseBody
    public ServerResponse<Admin> getUserInfo(HttpSession session){
        Admin admin =(Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin!=null)
            return ServerResponse.createBySuccess(admin);
        return ServerResponse.createByErrorMessage("用户没登陆，无法获取当前用户的信息");
    }

    /**
     *获取管理员忘记密码提示问题
     * @param adminName
     * @return
     */
    @RequestMapping("forget_get_question.do")
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String adminName){
        return iAdminService.selectQuestion(adminName);
    }

    /**
     * 获取管理员忘记密码提示问题的答案
     * @param adminName
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping("forget_get_answer.do")
    @ResponseBody
    public ServerResponse<String> forgetGetAnswer(String adminName,String question,String answer){
        return iAdminService.checkAnswer(adminName,question,answer);
    }

    /**
     * 重置密码
     * @param adminName
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping("forget_reset_password.do")
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String adminName, String passwordNew, String forgetToken){
        return iAdminService.forgetResetPassword(adminName,passwordNew,forgetToken);
    }

    /**
     * 修改密码
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping("reset_password.do")
    @ResponseBody
    ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        Admin admin=(Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin==null)
            return ServerResponse.createByErrorMessage("用户未登陆");
        return iAdminService.resetPassword(passwordOld,passwordNew,admin);
    }


    /**
     * 更新管理员信息
     * @param session
     * @param admin
     * @return
     */
    @RequestMapping("update_information.do")
    @ResponseBody
    public ServerResponse<Admin> updateInformation(HttpSession session, Admin admin){
        Admin currentUser=(Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (currentUser==null)
            return ServerResponse.createByErrorMessage("用户未登陆");
        admin.setAdminId(currentUser.getAdminId());
        ServerResponse<Admin> response=iAdminService.updateInformation(admin);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_ADMIN,response.getData());
        }
        return response;

    }

    /**
     * 获取管理员详细信息
     * @param session
     * @return
     */
    @RequestMapping("get_information.do")
    @ResponseBody
    public ServerResponse<Admin>getInformation(HttpSession session) {
        Admin currentUser = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (currentUser == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"为登陆，需要强制登陆status=10");
        return iAdminService.getInfomartion(currentUser.getAdminId());
    }
}
