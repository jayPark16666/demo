package com.ruoyi.system.service;

import com.ruoyi.system.domain.Cinema;

import java.util.List;

/**
 * @Name ICinemaService
 * @Author 蔡俊杰
 * @Date 2025-07-25 12:02
 */
public interface ICinemaService {
    /**
     * 查询所有影院信息
     * @param cinema
     * @return
     */
    public List<Cinema> selectList(Cinema  cinema);
    /**
     * 添加影院信息
     * @param cinema
     * @return
     */
    public int insertCinema(Cinema cinema);
    /**
     * 修改影院信息
     * @param cinema
     * @return
     */
    public int updateCinema(Cinema cinema);
    /**
     * 删除影院信息
     * @param cinemaId
     * @return
     */
    public int deleteCinema(Integer cinemaId);
    /**
     * 根据id查询影院信息
     * @param cinemaId
     * @return
     */
    public Cinema selectCinema(Integer cinemaId);
    /**
     * 查询所有影院信息
     * @param movieId
     * @return
     */
    public List<Cinema> frontCinemaList(Long movieId);
}
