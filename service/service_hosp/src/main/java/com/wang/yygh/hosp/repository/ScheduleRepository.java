package com.wang.yygh.hosp.repository;

import com.wang.yygh.model.hosp.Department;
import com.wang.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author wangyu
 * @date 2021/11/15 17:37
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);

}
