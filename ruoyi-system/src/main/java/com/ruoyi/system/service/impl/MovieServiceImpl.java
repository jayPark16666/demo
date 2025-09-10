package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Movie;
import com.ruoyi.system.mapper.MovieMapper;
import com.ruoyi.system.service.IMovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Name MovieServiceImpl
 * @Author 蔡俊杰
 * @Date 2025-07-22 20:59
 */
@Service
public class MovieServiceImpl implements IMovieService {

    @Resource
    private MovieMapper movieMapper;
    @Override
    public List<Movie> movieList(Movie movie) {
        return movieMapper.movieList(movie);
    }
    @Override
    public int addMovie(Movie movie) {
        movie.setMovieState(0);
        return movieMapper.addMovie(movie);
    }

    @Override
    public Movie getMovieInfo(Integer movieId) {
        return movieMapper.getMovieInfo(movieId);
    }

    @Override
    public int updateMovie(Movie movie) {
        return movieMapper.updateMovie(movie);
    }
}
