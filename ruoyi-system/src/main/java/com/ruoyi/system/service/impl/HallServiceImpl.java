package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Hall;
import com.ruoyi.system.mapper.HallMapper;
import com.ruoyi.system.service.IHallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Name HallServiceImpl
 * @Author 蔡俊杰
 * @Date 2025-07-27 20:56
 */
@Service
public class HallServiceImpl implements IHallService {
    @Resource
    private HallMapper hallMapper;

    @Override
    public List<Hall> selectList(Hall hall) {
        return hallMapper.selectList(hall);
    }

    @Override
    public int insertHall(Hall hall) {
        return hallMapper.insertHall( hall);
    }

    @Override
    public int updateHall(Hall hall) {
        return hallMapper.updateHall(hall);
    }

    @Override
    public int deleteHall(long hallId) {
        return hallMapper.deleteHall(hallId);
    }

    @Override
    public Hall selectHall(long hallId) {
        return hallMapper.selectHall(hallId);
    }

    @Override
    public List<Hall> selectHallByCinemaId(Integer cinemaId) {
        return hallMapper.selectHallByCinemaId(cinemaId);
    }
}
