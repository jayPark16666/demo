package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Schedule;
import com.ruoyi.system.mapper.ScheduleMapper;
import com.ruoyi.system.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name ScheduleServiceImpl
 * @Author 蔡俊杰
 * @Date 2025-07-28 18:36
 */
@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> selectList(Schedule schedule) {
        return scheduleMapper.selectList(schedule);
    }

    @Override
    public int insertSchedule(Schedule schedule) {
        return scheduleMapper.insertSchedule(schedule);
    }

    @Override
    public int updateSchedule(Schedule schedule) {
        return scheduleMapper.updateSchedule( schedule);
    }

    @Override
    public int updateScheduleState(Integer scheduleId, Integer scheduleState) {
        return scheduleMapper.updateScheduleState(scheduleId, scheduleState);
    }
    @Override
    public Schedule selectById(Integer scheduleId) {
        return scheduleMapper.selectById(scheduleId);
    }

    @Override
    public List<Schedule> frontScheduleList( Long movieId, Integer cinemaId) {
        return scheduleMapper.frontScheduleList(movieId, cinemaId);
    }
}
