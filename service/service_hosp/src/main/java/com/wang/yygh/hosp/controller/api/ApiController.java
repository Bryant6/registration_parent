package com.wang.yygh.hosp.controller.api;

import com.wang.yygh.hosp.service.ScheduleService;
import com.wang.yygh.model.hosp.Schedule;
import com.wang.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;
import com.wang.yygh.common.exception.HospitalException;
import com.wang.yygh.common.helper.HttpRequestHelper;
import com.wang.yygh.common.result.Result;
import com.wang.yygh.common.result.ResultCodeEnum;
import com.wang.yygh.common.util.MD5;
import com.wang.yygh.hosp.service.DepartmentService;
import com.wang.yygh.hosp.service.HospitalService;
import com.wang.yygh.hosp.service.HospitalSetService;
import com.wang.yygh.model.hosp.Department;
import com.wang.yygh.model.hosp.Hospital;
import com.wang.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangyu
 * @date 2021/11/15 15:33
 */
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("schedule/remove")
    public Result removeSchedule(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
//必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
//必填
        String hosScheduleId = (String)paramMap.get("hosScheduleId");
//        if(StringUtils.isEmpty(hoscode)) {
//            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
//        }
////签名校验
//        if(!HttpRequestHelper.isSignEquals(paramMap, hospitalSetService.getSignKey(hoscode))) {
//            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
//        }

        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();
    }


    @PostMapping("schedule/list")
    public Result schedule(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
//必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
//非必填
        String depcode = (String)paramMap.get("depcode");
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

//        if(StringUtils.isEmpty(hoscode)) {
//            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
//        }
////签名校验
//        if(!HttpRequestHelper.isSignEquals(paramMap, hospitalSetService.getSignKey(hoscode))) {
//            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
//        }

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageModel = scheduleService.selectPage(page , limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }


    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
//        if(StringUtils.isEmpty(hoscode)) {
//            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
//        }
////签名校验
//        if(!HttpRequestHelper.isSignEquals(paramMap, hospitalSetService.getSignKey(hoscode))) {
//            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
//        }

        scheduleService.save(paramMap);
        return Result.ok();
    }


    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
        //必填
        String depcode = (String)paramMap.get("depcode");
//        if(StringUtils.isEmpty(hoscode)) {
//            throw new HospitalException(ResultCodeEnum.PARAM_ERROR);
//        }
//        //签名校验
//        if(!HttpRequestHelper.isSignEquals(paramMap, hospitalSetService.getSignKey(hoscode))) {
//            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
//        }

        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }


    @PostMapping("department/list")
    public Result department(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
        //非必填
        String depcode = (String)paramMap.get("depcode");
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

//        if(StringUtils.isEmpty(hoscode)) {
//            throw new HospitalException(ResultCodeEnum.PARAM_ERROR);
//        }
//        //签名校验
//        if(!HttpRequestHelper.isSignEquals(paramMap, hospitalSetService.getSignKey(hoscode))) {
//            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
//        }

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        departmentQueryVo.setDepcode(depcode);
        Page<Department> pageModel = departmentService.selectPage(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }


    //上传科室
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        departmentService.save(paramMap);
        return Result.ok();
    }

    //查询医院
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramMap.get("hoscode");
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    //上传医院接口
    @RequestMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

//        String hospSign = (String)paramMap.get("sign");
//        String hoscode = (String) paramMap.get("hoscode");
//        String signKey = hospitalSetService.getSignKey(hoscode);
//        String signKeyMD5 = MD5.encrypt(signKey);
//
//        if(!hospSign.equals(signKeyMD5)){
//            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
//        }

        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);

        hospitalService.save(paramMap);

        return Result.ok();
    }

}
