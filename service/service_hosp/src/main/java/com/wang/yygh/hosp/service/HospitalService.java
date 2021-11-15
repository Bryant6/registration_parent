package com.wang.yygh.hosp.service;

import com.wang.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author wangyu
 * @date 2021/11/15 15:32
 */
public interface HospitalService {
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);
}
