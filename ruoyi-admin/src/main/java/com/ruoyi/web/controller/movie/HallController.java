package com.ruoyi.web.controller.movie;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Hall;
import com.ruoyi.system.service.IHallService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Name HallController
 * @Author 蔡俊杰
 * @Date 2025-07-27 20:59
 */
@Controller
@RequestMapping("/hall")
public class HallController extends BaseController {
    @Resource
    private IHallService hallService;

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(Hall hall) {
        startPage();
        return getDataTable(hallService.selectList(hall));
    }
    @RequestMapping("/options")
    @ResponseBody
    public AjaxResult options(  Integer cinemaId) {

        return AjaxResult.success(hallService.selectHallByCinemaId(cinemaId));
    }

    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult addHall(@RequestBody Hall hall) {
        if (hallService.insertHall(hall) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult updateHall(@RequestBody Hall hall) {
        if (hallService.updateHall(hall) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult deleteHall( long hallId) {
        if (hallService.deleteHall(hallId) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public AjaxResult selectHall(long hallId) {
        return AjaxResult.success(hallService.selectHall(hallId));
    }
}
