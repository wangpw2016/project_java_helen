package com.atguigu.gulixueyuan.statistics.controller.admin;


import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.statistics.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2018-12-27
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/statistics/day")
public class DayController {

    @Autowired
    private DayService dayService;

    @PostMapping("create/{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        dayService.createStatisticsByDay(day);
        return R.ok();
    }


    @GetMapping("show-chart/{bengin}/{end}/{type}")
    public R showChart(@PathVariable String bengin,@PathVariable String end,@PathVariable String type) throws Exception {
        Map<String, List> map = dayService.getChartData(bengin, end, type);
        return R.ok().data(map);
    }
}

