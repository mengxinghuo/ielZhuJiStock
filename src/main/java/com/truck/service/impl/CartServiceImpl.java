package com.truck.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.CartMapper;
import com.truck.dao.StockMapper;
import com.truck.pojo.Cart;
import com.truck.pojo.Stock;
import com.truck.service.ICartService;
import com.truck.util.BigDecimalUtil;
import com.truck.util.DateTimeUtil;
import com.truck.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    StockMapper stockMapper;

    public ServerResponse list(Integer adminId) {
        List<CartVo> cartVoList = this.getCartVoLimit(adminId);
        return ServerResponse.createBySuccess(cartVoList);
    }


    public ServerResponse<CartVo> add(Integer adminId, Integer stockId, Integer count) {
        if (count == null || stockId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        Cart cart = cartMapper.selectCartByAdminIdStockId(adminId, stockId);
        if (cart == null) {
            //这个产品不在这个购物车里，需要新增一个产品记录
            Cart cartItem = new Cart();
            cartItem.setAdminId(adminId);
            cartItem.setAmount(count);
            cartItem.setStockId(stockId);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setCartPrice(stock.getSalesPrice());
            cartMapper.insertSelective(cartItem);
        } else {
            //这个产品已经在购物车中相加产品数量
            count = cart.getAmount() + count;
            cart.setAmount(count);
            if(count <=0){
                cartMapper.deleteByPrimaryKey(cart.getCartId());
            }else {
                cartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return this.list(adminId);
    }

    public ServerResponse<CartVo> update(Integer adminId, Integer count, Integer stockId, BigDecimal cartPrice) {
        if (count == null || stockId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        if (count < 1)
            return ServerResponse.createByErrorMessage("");
        Cart cart = cartMapper.selectCartByAdminIdStockId(adminId, stockId);
        if (cart != null){
            if (cartPrice !=null  ) {
                if (cartPrice.compareTo(new BigDecimal(0))<0) {
                    return  ServerResponse.createByErrorMessage("单价不可小于0");
                }
                cart.setCartPrice(cartPrice);
            }
            Integer real= cart.getAmount()+count;
            if(real <=0){
                cartMapper.deleteByPrimaryKey(cart.getCartId());
            }else{
                cart.setAmount(count);
                cartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return this.list(adminId);
    }


    public ServerResponse<CartVo> selectOrUnSelect(Integer adminId, Integer checked, Integer stockId) {
        cartMapper.checkedOrUncheckedProduct(adminId, stockId, checked);
        return this.list(adminId);
    }


    public ServerResponse<CartVo> deleteProduct(Integer adminId, String stockIds) {
        List<String> stockList = Splitter.on(",").splitToList(stockIds);
        if (CollectionUtils.isEmpty(stockList))
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        cartMapper.deleteByUserIdProductIds(adminId, stockList);

        return this.list(adminId);
    }


    public ServerResponse<Integer> getcartCount(Integer adminId) {
       Integer count = cartMapper.selectCartProductCount(adminId);
        if (count == null) {
            count =0;
        }
        return ServerResponse.createBySuccess(count);
    }

    public ServerResponse cleanCart(Integer adminId) {
        if (adminId == null)
            return ServerResponse.createByErrorMessage("请登录");
           int result =  cartMapper.deleteByAdminId(adminId);
        if (result >0) {
            return ServerResponse.createBySuccess("清空购物车成功");
        }
        return ServerResponse.createBySuccess("清空购物车失败");

    }

    private List<CartVo> getCartVoLimit(Integer adminId) {
        List<CartVo> cartVoList = Lists.newArrayList();
        List<Cart> cartLists = cartMapper.selectCartByAdminId(adminId);
        for (Cart cartList : cartLists) {
            CartVo cartVo = this.assembleCartVo(cartList);
            cartVoList.add(cartVo);

        }
        return cartVoList;
    }

    private CartVo assembleCartVo(Cart cart){
        CartVo cartVo = new CartVo();
        cartVo.setCartId(cart.getCartId());
        cartVo.setAdminId(cart.getAdminId());
        cartVo.setStockId(cart.getStockId());
        cartVo.setAmount(cart.getAmount());
        cartVo.setChecked(cart.getChecked());
        Stock stock = stockMapper.selectByPrimaryKey(cart.getStockId());
        cartVo.setStock(stock);
        cartVo.setCreateTime(DateTimeUtil.dateToStr(cart.getCreateTime()));
        cartVo.setUpdateTime(DateTimeUtil.dateToStr(cart.getUpdateTime()));
        return cartVo;
    }



    private boolean getAllCheckedStatus(Integer adminId) {
        if (adminId == null)
            return false;
        return cartMapper.selectCartProductCheckedStatusByUserId(adminId) == 0;
    }
}
