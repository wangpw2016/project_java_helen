package com.atguigu.gulixueyuan.statistics.service;

import com.atguigu.gulixueyuan.statistics.entity.Day;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Helen
 * @since 2018-12-27
 */
public interface DayService extends IService<Day> {

    void createStatisticsByDay(String day);

    Map<String, List> getChartData(String begin, String end, String type) throws Exception;
}
