package com.wang.yygh.hosp.service;

import com.wang.yygh.model.hosp.Schedule;
import com.wang.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author wangyu
 * @date 2021/11/15 20:16
 */
public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    Page<Schedule> selectPage(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);

    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
}
