package com.ruoyi.web.controller.movie;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.service.ICinemaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Name CinemaController
 * @Author 蔡俊杰
 * @Date 2025-07-25 12:09
 */
@Controller
@RequestMapping("/cinema")
public class CinemaController extends BaseController {
    @Resource
    private ICinemaService cinemaService;

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(Cinema cinema) {
        startPage();
        return getDataTable(cinemaService.selectList(cinema));
    }
    @RequestMapping("/options")
    @ResponseBody
    public AjaxResult options(Cinema cinema) {
       return  AjaxResult.success(cinemaService.selectList(cinema));
    }

    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addCinema(@RequestBody Cinema cinema) {
        int a = cinemaService.insertCinema(cinema);
        if (a > 0) {
            return AjaxResult.success("新增成功");
        } else {
            return AjaxResult.error("新增失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult updateCinema(@RequestBody Cinema cinema) {
        int a = cinemaService.updateCinema(cinema);
        if (a > 0) {
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult deleteCinema(Integer cinemaId) {
        int a = cinemaService.deleteCinema(cinemaId);
        if (a > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public AjaxResult selectById(Integer cinemaId) {
        Cinema cinema = cinemaService.selectCinema(cinemaId);
        return AjaxResult.success(cinema);
    }
    @RequestMapping("/frontList")
    @ResponseBody
    public AjaxResult frontCinemaList(Long movieId) {
        return AjaxResult.success(cinemaService.frontCinemaList(movieId));
    }

}
