package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.mapper.CinemaMapper;
import com.ruoyi.system.service.ICinemaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Name CinemaServiceImpl
 * @Author 蔡俊杰
 * @Date 2025-07-25 12:03
 */
@Service
public class CinemaServiceImpl implements ICinemaService {

    @Resource
    private CinemaMapper cinemaMapper;


    @Override
    public List<Cinema> selectList(Cinema cinema) {
        return cinemaMapper.selectList(cinema);
    }

    @Override
    public int insertCinema(Cinema cinema) {
        return cinemaMapper.insertCinema(cinema);
    }

    @Override
    public int updateCinema(Cinema cinema) {
        return cinemaMapper.updateCinema(cinema);
    }

    @Override
    public int deleteCinema(Integer cinemaId) {
        return cinemaMapper.deleteCinema(cinemaId);
    }

    @Override
    public Cinema selectCinema(Integer cinemaId) {
        return cinemaMapper.selectCinema(cinemaId);
    }
    @Override
    public List<Cinema> frontCinemaList(Long movieId) {
        return cinemaMapper.frontCinemaList(movieId);
    }
}