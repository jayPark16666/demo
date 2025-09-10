package com.ruoyi.web.controller.movie;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Hall;
import com.ruoyi.system.domain.Schedule;
import com.ruoyi.system.service.IHallService;
import com.ruoyi.system.service.IScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Name ScheduleController
 * @Author 蔡俊杰
 * @Date 2025-07-28 18:38
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController  extends BaseController {
    @Resource
    private IScheduleService scheduleService;
    @Resource
    private IHallService hallService;
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(Schedule schedule){
        startPage();
        return getDataTable(scheduleService.selectList(schedule));
    }
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addSchedule(@RequestBody Schedule schedule){
        schedule.setScheduleState(1);
        Hall hall = hallService.selectHall(schedule.getHallId());
        if(hall.getHallCol()!=null && hall.getHallRow()!=null){
            schedule.setScheduleRemain(hall.getHallCol()*hall.getHallRow());
        }else {
            return AjaxResult.error("请先添加影厅信息");
        }

        if ( scheduleService.insertSchedule(schedule) > 0){
            return AjaxResult.success();

        }else{
            return  AjaxResult.error();
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult updateSchedule(@RequestBody Schedule schedule){
        if ( scheduleService.updateSchedule(schedule) > 0){
            return  AjaxResult.success();

        }else{
            return  AjaxResult.error();
        }
    }
    @RequestMapping("/updateScheduleState")
    @ResponseBody
    public AjaxResult updateScheduleState( Integer scheduleId, Integer scheduleState){
        if (scheduleId == null || scheduleState == null) {
            return AjaxResult.error("参数不能为空");
        }
        if ( scheduleService.updateScheduleState(scheduleId, scheduleState) > 0){
            return  AjaxResult.success("修改成功");

        }else{
            return  AjaxResult.error("修改失败");
        }
    }
    @RequestMapping("/selectById")
    @ResponseBody
    public AjaxResult selectById(Integer scheduleId){
        return AjaxResult.success(scheduleService.selectById(scheduleId));
    }

    @RequestMapping("/frontScheduleList")
    @ResponseBody
    public AjaxResult frontScheduleList(Long movieId, Integer cinemaId) {
        return AjaxResult.success(scheduleService.frontScheduleList( movieId, cinemaId));
    }

}
