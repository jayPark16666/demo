package com.ruoyi.system.service;

import com.ruoyi.system.domain.OrderInfo;

import java.util.List;

/**
 * @Name OrderInfoService
 * @Author 蔡俊杰
 * @Date 2025-07-30 14:52
 */

public interface OrderInfoService {
    List<OrderInfo> selectList(OrderInfo orderInfo);
   public int createOrder(OrderInfo orderInfo);
    List<OrderInfo> findSoldSeat(int scheduleId);
}
