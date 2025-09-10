package com.ruoyi.system.domain;

/**
 * @Name Hall
 * @Author 蔡俊杰
 * @Date 2025-07-27 20:35
 */
public class Hall {
    private Long hallId;
    private String hallName;
    private Long cinemaId;
    private  Integer hallRow;
    private  Integer hallCol;
    private  String cinemaName;

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Integer getHallRow() {
        return hallRow;
    }

    public void setHallRow(Integer hallRow) {
        this.hallRow = hallRow;
    }

    public Integer getHallCol() {
        return hallCol;
    }

    public void setHallCol(Integer hallCol) {
        this.hallCol = hallCol;
    }
    public String getCinemaName() {
        return cinemaName;
    }
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
