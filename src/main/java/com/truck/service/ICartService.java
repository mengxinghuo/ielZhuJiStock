package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.vo.CartVo;

import java.math.BigDecimal;

public interface ICartService {

    ServerResponse<CartVo> list(Integer adminId);

    ServerResponse<CartVo> add(Integer adminId, Integer stockId, Integer count);

    ServerResponse<CartVo> update(Integer adminId, Integer count, Integer stockId, BigDecimal cartPrice);

    ServerResponse<CartVo> deleteProduct(Integer adminId, String stockIds);

    ServerResponse<CartVo> selectOrUnSelect(Integer adminId, Integer checked, Integer stockId);

    ServerResponse<Integer> getcartCount(Integer adminId);

    ServerResponse cleanCart(Integer adminId);
}
