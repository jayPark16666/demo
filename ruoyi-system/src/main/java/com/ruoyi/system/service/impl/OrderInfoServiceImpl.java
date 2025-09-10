package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.OrderInfo;
import com.ruoyi.system.domain.Schedule;
import com.ruoyi.system.mapper.OrderInfoMapper;
import com.ruoyi.system.mapper.ScheduleMapper;
import com.ruoyi.system.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Name OrderInfoServiceImpl
 * @Author 蔡俊杰
 * @Date 2025-07-30 14:53
 */
@Service
public class OrderInfoServiceImpl  implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public List<OrderInfo> selectList(OrderInfo orderInfo) {
        return orderInfoMapper.selectList(orderInfo);

    }
    @Override
    public int createOrder(OrderInfo orderInfo) {
        System.out.println("创建订单");
        Schedule schedule = scheduleMapper.selectById(orderInfo.getScheduleId());
        if (schedule == null) {
            throw new RuntimeException("未找到对应的排片信息");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int random=(int)((Math.random()*9+1)*1000);
        long a= Long.parseLong(df.format(new Date())+random);
        orderInfo.setOrderTime(df1.format(new Date()));
        orderInfo.setOrderId(a);
        orderInfo.setOrderPrice((int) schedule.getSchedulePrice().multiply(new BigDecimal(orderInfo.getOrderPosition().split(",").length)).longValue());
        orderInfo.setOrderState("1");
        return orderInfoMapper.createOrder(orderInfo);
    }
    @Override
    public List<OrderInfo> findSoldSeat(int scheduleId) {
        return orderInfoMapper.findSoldSeat(scheduleId);
    }
}
