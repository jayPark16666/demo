package com.ruoyi.system.domain;

/**
 * @Name cinema
 * @Author 蔡俊杰
 * @Date 2025-07-25 11:45
 */
public class Cinema {
    private Integer cinemaId;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTel;
    private String cinemaPoster;
    private  Integer price;

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCinemaTel() {
        return cinemaTel;
    }

    public void setCinemaTel(String cinemaTel) {
        this.cinemaTel = cinemaTel;
    }

    public String getCinemaPoster() {
        return cinemaPoster;
    }

    public void setCinemaPoster(String cinemaPoster) {
        this.cinemaPoster = cinemaPoster;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
