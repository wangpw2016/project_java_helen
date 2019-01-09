package com.atguigu.gulixueyuan.statistics.service.impl;

import com.atguigu.gulixueyuan.statistics.client.UcenterClient;
import com.atguigu.gulixueyuan.statistics.entity.Day;
import com.atguigu.gulixueyuan.statistics.mapper.DayMapper;
import com.atguigu.gulixueyuan.statistics.service.DayService;
import com.atguigu.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2018-12-27
 */
@Service
public class DayServiceImpl extends ServiceImpl<DayMapper, Day> implements DayService {

    @Autowired
    private UcenterClient ucenterClient;


    @Override
    public void createStatisticsByDay(String day) {


        //删除已存在的通缉对象
        QueryWrapper<Day> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("DATE_FORMAT(STATISTICS_TIME,'%Y-%m-%d')", day);
        baseMapper.delete(dayQueryWrapper);


        //获取统计信息
        Integer loginCount = (Integer) ucenterClient.loginCount(day).getData();
        Integer registerCount = (Integer) ucenterClient.registerCount(day).getData();
        Integer dailyUserNumber = RandomUtils.nextInt(100, 200);//TODO
        Integer dailyCourseNumber = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewingNum = RandomUtils.nextInt(100, 200);//TODO

        //创建统计对象
        Day statisticeDay = new Day();
        statisticeDay.setLoginNum(loginCount);
        statisticeDay.setRegisteredNum(registerCount);
        statisticeDay.setDailyUserNumber(dailyUserNumber);
        statisticeDay.setDailyCourseNumber(dailyCourseNumber);
        statisticeDay.setVideoViewingNum(videoViewingNum);
        statisticeDay.setStatisticsTime(DateUtil.str2Date(day, "yyyy-MM-dd"));

        baseMapper.insert(statisticeDay);
    }

    @Override
    public Map<String, List> getChartData(String begin, String end, String type) throws Exception {

        QueryWrapper<Day> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.select(type, "STATISTICS_TIME");
        dayQueryWrapper.between("STATISTICS_TIME", begin, end);

        List<Day> dayList = baseMapper.selectList(dayQueryWrapper);

        Map<String, List> map = new HashMap<String, List>();
        List<Integer> countList = new ArrayList<Integer>();
        List<String> dateList = new ArrayList<String>();
        map.put("countList", countList);
        map.put("dateList", dateList);


        for (int i = 0; i < dayList.size() - 1; i++) {
            Day day = dayList.get(i);
            switch (type) {
                case "LOGIN_NUM":
                    countList.add(day.getLoginNum());
                    break;
                case "REGISTERED_NUM":
                    countList.add(day.getRegisteredNum());
                    break;
                case "DAILY_USER_NUMBER":
                    countList.add(day.getDailyUserNumber());
                    break;
                case "DAILY_COURSE_NUMBER":
                    countList.add(day.getDailyCourseNumber());
                    break;
                case "VIDEO_VIEWING_NUM":
                    countList.add(day.getVideoViewingNum());
                    break;
                default:
                    break;
            }

            dateList.add(DateUtil.date2Str(day.getStatisticsTime(), "yyyy-MM-dd"));
        }

        return map;
    }
}
