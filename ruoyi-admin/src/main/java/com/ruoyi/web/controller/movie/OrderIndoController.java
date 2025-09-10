package com.ruoyi.web.controller.movie;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.OrderInfo;
import com.ruoyi.system.service.OrderInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Name OrderIndoController
 * @Author 蔡俊杰
 * @Date 2025-07-30 14:54
 */
@Controller
@RequestMapping("/orderinfo")
public class OrderIndoController extends BaseController {
    @Resource
    private OrderInfoService orderInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo selectList(OrderInfo orderInfo) {
        startPage();
        return getDataTable(orderInfoService.selectList(orderInfo));
    }

    @RequestMapping("/create")
    @ResponseBody
    public AjaxResult createOrder(@RequestBody OrderInfo orderInfo){
        orderInfo.setUserId(getUserId());
        if (orderInfoService.createOrder(orderInfo)>0){
            return AjaxResult.success();
        }else{
             return AjaxResult.error();
        }
    }
    @RequestMapping("/find")
    @ResponseBody
    public  AjaxResult findSoldSeat(int scheduleId){
        List<OrderInfo> orderInfoList= orderInfoService.findSoldSeat(scheduleId);
        List<String> seat=new ArrayList<>();
        if(orderInfoList.size()>0){
            for ( OrderInfo orderInfo : orderInfoList){
                for ( String s : orderInfo.getOrderPosition().split(" ")){
                    seat.add(s);
                }
            }
        }
        return AjaxResult.success(seat);
    }

}
