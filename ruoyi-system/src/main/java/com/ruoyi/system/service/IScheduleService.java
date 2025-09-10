package com.ruoyi.system.service;

import com.ruoyi.system.domain.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Name IScheduleService
 * @Author 蔡俊杰
 * @Date 2025-07-28 17:32
 */
public interface IScheduleService {
    public List<Schedule> selectList(Schedule schedule);

    public int insertSchedule(Schedule schedule);

    public int updateSchedule(Schedule schedule);

    public int updateScheduleState(Integer scheduleId, Integer scheduleState);

    public Schedule selectById(Integer scheduleId);

    public List<Schedule> frontScheduleList( Long movieId, Integer cinemaId);
}
