package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OrderInfo;

import java.util.List;

/**
 * @Name OrderInfoMapper
 * @Author 蔡俊杰
 * @Date 2025-07-30 14:38
 */
public interface OrderInfoMapper {
    List<OrderInfo> selectList(OrderInfo orderInfo);
    public int createOrder(OrderInfo orderInfo);

    List<OrderInfo> findSoldSeat(int scheduleId);

}
