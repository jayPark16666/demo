package com.ruoyi.system.domain;

/**
 * @Name Movie
 * @Author 蔡俊杰
 * @Date 2025-07-22 20:44
 */

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @TableName movie
 */

public class Movie implements Serializable {
    /**
     * 电影编号
     */

    private Long movieId;

    /**
     * 电影名称（中文）
     */

    private String movieCnName;

    /**
     * 电影演职人员
     */

    private String movieActor;

    /**
     * 电影导演
     */

    private String movieDirector;

    /**
     * 电影详情
     */

    private String movieDetail;

    /**
     * 电影时长
     */

    private String movieDuration;

    /**
     * 电影类型
     */

    private String movieType;

    /**
     * 电影上映时间
     */

    private Date movieReleasedate;

    /**
     * 电影制片地区
     */

    private String movieCountry;

    /**
     * 电影海报
     */

    private String moviePicture;

    /**
     * 电影状态 默认为1  1：在线 0：下架
     */

    private Integer movieState;


    private static final long serialVersionUID = 1L;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieCnName() {
        return movieCnName;
    }

    public void setMovieCnName(String movieCnName) {
        this.movieCnName = movieCnName;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieDetail() {
        return movieDetail;
    }

    public void setMovieDetail(String movieDetail) {
        this.movieDetail = movieDetail;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public Date getMovieReleasedate() {
        return movieReleasedate;
    }

    public void setMovieReleasedate(Date movieReleasedate) {
        this.movieReleasedate = movieReleasedate;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMoviePicture() {
        return moviePicture;
    }

    public void setMoviePicture(String moviePicture) {
        this.moviePicture = moviePicture;
    }

    public Integer getMovieState() {
        return movieState;
    }

    public void setMovieState(Integer movieState) {
        this.movieState = movieState;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Movie other = (Movie) that;
        return (this.getMovieId() == null ? other.getMovieId() == null : this.getMovieId().equals(other.getMovieId()))
                && (this.getMovieCnName() == null ? other.getMovieCnName() == null : this.getMovieCnName().equals(other.getMovieCnName()))
                && (this.getMovieActor() == null ? other.getMovieActor() == null : this.getMovieActor().equals(other.getMovieActor()))
                && (this.getMovieDirector() == null ? other.getMovieDirector() == null : this.getMovieDirector().equals(other.getMovieDirector()))
                && (this.getMovieDetail() == null ? other.getMovieDetail() == null : this.getMovieDetail().equals(other.getMovieDetail()))
                && (this.getMovieDuration() == null ? other.getMovieDuration() == null : this.getMovieDuration().equals(other.getMovieDuration()))
                && (this.getMovieType() == null ? other.getMovieType() == null : this.getMovieType().equals(other.getMovieType()))
                && (this.getMovieReleasedate() == null ? other.getMovieReleasedate() == null : this.getMovieReleasedate().equals(other.getMovieReleasedate()))
                && (this.getMovieCountry() == null ? other.getMovieCountry() == null : this.getMovieCountry().equals(other.getMovieCountry()))
                && (this.getMoviePicture() == null ? other.getMoviePicture() == null : this.getMoviePicture().equals(other.getMoviePicture()))
                && (this.getMovieState() == null ? other.getMovieState() == null : this.getMovieState().equals(other.getMovieState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMovieId() == null) ? 0 : getMovieId().hashCode());
        result = prime * result + ((getMovieCnName() == null) ? 0 : getMovieCnName().hashCode());
        result = prime * result + ((getMovieActor() == null) ? 0 : getMovieActor().hashCode());
        result = prime * result + ((getMovieDirector() == null) ? 0 : getMovieDirector().hashCode());
        result = prime * result + ((getMovieDetail() == null) ? 0 : getMovieDetail().hashCode());
        result = prime * result + ((getMovieDuration() == null) ? 0 : getMovieDuration().hashCode());
        result = prime * result + ((getMovieType() == null) ? 0 : getMovieType().hashCode());
        result = prime * result + ((getMovieReleasedate() == null) ? 0 : getMovieReleasedate().hashCode());
        result = prime * result + ((getMovieCountry() == null) ? 0 : getMovieCountry().hashCode());
        result = prime * result + ((getMoviePicture() == null) ? 0 : getMoviePicture().hashCode());
        result = prime * result + ((getMovieState() == null) ? 0 : getMovieState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", movieId=").append(movieId);
        sb.append(", movieCnName=").append(movieCnName);
        sb.append(", movieActor=").append(movieActor);
        sb.append(", movieDirector=").append(movieDirector);
        sb.append(", movieDetail=").append(movieDetail);
        sb.append(", movieDuration=").append(movieDuration);
        sb.append(", movieType=").append(movieType);
        sb.append(", movieReleasedate=").append(movieReleasedate);
        sb.append(", movieCountry=").append(movieCountry);
        sb.append(", moviePicture=").append(moviePicture);
        sb.append(", movieState=").append(movieState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
