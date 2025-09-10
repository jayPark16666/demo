package com.ruoyi.system.service;

import com.ruoyi.system.domain.Hall;

import java.util.List;

/**
 * @Name IHallService
 * @Author 蔡俊杰
 * @Date 2025-07-27 20:56
 */
public interface IHallService {
    public List<Hall> selectList(Hall hall);
    public int insertHall(Hall hall);
    public int updateHall(Hall hall);
    public int deleteHall(long hallId);
    public Hall selectHall(long hallId);

    public List<Hall> selectHallByCinemaId(Integer cinemaId);
}
