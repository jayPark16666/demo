package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Hall;

import java.util.List;

/**
 * @Name HallMapper
 * @Author 蔡俊杰
 * @Date 2025-07-27 20:37
 */
public interface HallMapper {
  public   int insertHall(Hall hall);

    public int updateHall(Hall hall);

    public  int deleteHall(long hallId);

    public Hall selectHall(long hallId);

    public List<Hall> selectList(Hall hall);

    public List<Hall> selectHallByCinemaId(Integer cinemaId);
}
