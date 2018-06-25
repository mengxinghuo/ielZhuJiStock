package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.ICartService;
import com.truck.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    ICartService iCartService;


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }

        return iCartService.list(admin.getAdminId());
    }


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse<CartVo> add(HttpSession session, Integer count, Integer stockId){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }

        return iCartService.add(admin.getAdminId(),stockId,count);
    }


    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse<CartVo> update(HttpSession session, Integer count, Integer stockId, BigDecimal cartPrice){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }

        return iCartService.update(admin.getAdminId(),count,stockId,cartPrice);
    }

    @RequestMapping("deleteProduct.do")
    @ResponseBody
    public ServerResponse<CartVo> deleteProduct(HttpSession session, String stockIds){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCartService.deleteProduct(admin.getAdminId(),stockIds);
    }


    @RequestMapping("selectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(admin.getAdminId(), Const.Cart.CHECKED,null);
    }

    @RequestMapping("unSelectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(admin.getAdminId(), Const.Cart.UN_CHECKED,null);

    }

    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<CartVo> select(HttpSession session, Integer stockId){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(admin.getAdminId(), Const.Cart.CHECKED,stockId);

    }

    @RequestMapping("unSelect.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(HttpSession session, Integer stockId){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(admin.getAdminId(), Const.Cart.UN_CHECKED,stockId);

    }

    @RequestMapping("getcartCount.do")
    @ResponseBody
    public ServerResponse<Integer> getcartCount(HttpSession session){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null)
            return ServerResponse.createBySuccess(0);
        return iCartService.getcartCount(admin.getAdminId());
    }


    //一键清空购物车
    @RequestMapping("cleanCart.do")
    @ResponseBody
    public ServerResponse cleanCart(HttpSession session){
      Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iCartService.cleanCart(admin.getAdminId());
    }
}
