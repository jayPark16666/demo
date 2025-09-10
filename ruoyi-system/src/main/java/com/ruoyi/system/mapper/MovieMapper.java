package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Movie;

import java.util.List;

/**
 * @Name MovieMapper
 * @Author 蔡俊杰
 * @Date 2025-07-22 20:54
 */
public interface MovieMapper {
    public List<Movie> movieList(Movie movie);
    public int addMovie(Movie movie);

    public Movie getMovieInfo(Integer movieId);

    public int updateMovie(Movie movie);
}
