package com.truck.dao;

import com.truck.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByAdminIdStockId(@Param("adminId") Integer adminId, @Param("stockId") Integer stockId);

    int deleteByUserIdProductIds(@Param("adminId")Integer adminId,@Param("stockIdList")List<String> stockIdList);

    int checkedOrUncheckedProduct(@Param("adminId")Integer adminId,@Param("stockId")Integer stockId,@Param("checked") Integer checked);

    Integer selectCartProductCount(Integer adminId);

    int deleteByAdminId(@Param("adminId")Integer adminId);

    List<Cart> selectCartByAdminId(Integer adminId);

    int selectCartProductCheckedStatusByUserId(Integer adminId);

}