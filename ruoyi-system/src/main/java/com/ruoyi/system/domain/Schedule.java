package com.ruoyi.system.domain;

import java.math.BigDecimal;

/**
 * @Name Schedule
 * @Author 蔡俊杰
 * @Date 2025-07-28 17:23
 */
public class Schedule {
    private Integer scheduleId;
    private Integer hallId;
    private Integer movieId;
    private Integer cinemaId;
    private String hallName;
    private String cinemaName;
    private String movieName;
    private String scheduleStartTime;
    private BigDecimal schedulePrice;
    private Integer scheduleRemain;
    private Integer scheduleState;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Integer getmovieId() {
        return movieId;
    }

    public void setmovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public BigDecimal getSchedulePrice() {
        return schedulePrice;
    }

    public void setSchedulePrice(BigDecimal schedulePrice) {
        this.schedulePrice = schedulePrice;
    }

    public Integer getScheduleRemain() {
        return scheduleRemain;
    }

    public void setScheduleRemain(Integer scheduleRemain) {
        this.scheduleRemain = scheduleRemain;
    }

    public Integer getScheduleState() {
        return scheduleState;
    }

    public void setScheduleState(Integer scheduleState) {
        this.scheduleState = scheduleState;
    }
}
