package com.ruoyi.system.service;

import com.ruoyi.system.domain.Movie;

import java.util.List;

/**
 * @Name IMovieService
 * @Author 蔡俊杰
 * @Date 2025-07-22 20:57
 */
public interface IMovieService {
    List<Movie> movieList(Movie movie);

    public int addMovie(Movie movie);

    public Movie getMovieInfo(Integer movieId);

    public int updateMovie(Movie movie);
}
