package com.wang.yygh.hosp.service;

import org.springframework.data.domain.Page;
import com.wang.yygh.model.hosp.Department;
import com.wang.yygh.model.hosp.Hospital;
import com.wang.yygh.vo.hosp.DepartmentQueryVo;

import java.util.Map;

/**
 * @author wangyu
 * @date 2021/11/15 15:32
 */
public interface DepartmentService {

    void save(Map<String, Object> paramMap);

    Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);
}
