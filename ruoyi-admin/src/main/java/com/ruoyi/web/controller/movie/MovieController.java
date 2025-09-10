package com.ruoyi.web.controller.movie;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.domain.Movie;
import com.ruoyi.system.service.IMovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Name MovieController
 * @Author 蔡俊杰
 * @Date 2025-07-22 20:48
 */
@Controller
@RequestMapping("/movie")
public class MovieController extends BaseController {

    @Resource
    private IMovieService movieService;

    @RequestMapping("/list")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('system:movie:list')")
    public TableDataInfo list(Movie movie)
        {
        startPage();
        List<Movie> list = movieService.movieList(movie);
        return getDataTable(list);
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('system:movie:add')")
    public AjaxResult addMovie(@RequestBody Movie movie)
    {
        int a=movieService.addMovie(movie);
        if(a>0){
            return AjaxResult.success("新增成功");
        }else{
            return AjaxResult.error("新增失败");
        }
    }

    @PostMapping("/getMovie")
    @ResponseBody
    public  AjaxResult getMovieInfo(Integer movieId){
        return AjaxResult.success(movieService.getMovieInfo(movieId));
    }

    @PostMapping("/update")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('system:movie:update')")
    public  AjaxResult updateMovie( @RequestBody Movie movie){
        int a=movieService.updateMovie(movie);
        if(a>0){
            return AjaxResult.success("修改成功");
        }else{
            return AjaxResult.error("修改失败");
        }
    }

    @PostMapping("/change")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('system:movie:change')")
    public  AjaxResult changeState( Integer movieId){
       Movie movie=movieService.getMovieInfo(movieId);
       movie.setMovieState(movie.getMovieState()==1?0:1);
       int a=movieService.updateMovie(movie);
       if(a>0){
           return AjaxResult.success("修改成功");
       }else{
           return AjaxResult.error("修改失败");
       }
    }
    @RequestMapping("/options")
    @ResponseBody
    public AjaxResult options(Movie  movie) {
        return  AjaxResult.success(movieService.movieList(movie));
    }

}
