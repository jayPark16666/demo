package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Name ScheduleMapper
 * @Author 蔡俊杰
 * @Date 2025-07-28 17:30
 */
public interface ScheduleMapper {
    public List<Schedule> selectList(Schedule schedule);

    public Schedule selectById(Integer scheduleId);

    public int insertSchedule(Schedule schedule);

    public int updateSchedule(Schedule schedule);

    public int updateScheduleState(@Param( "scheduleId")Integer scheduleId, @Param( "scheduleState")Integer scheduleState);

    public List<Schedule> frontScheduleList(@Param("movieId") Long movieId, @Param("cinemaId") Integer cinemaId);
}
