package com.wang.yygh.hosp.controller;

import com.wang.yygh.hosp.service.HospitalSetService;
import com.wang.yygh.model.hosp.HospitalSet;
import com.wang.yygh.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangyu
 * @date 2021/9/15 19:44
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    //1. 查询医院设置表中的全部数据
    @ApiOperation(value = "获取所有医院信息")
    @GetMapping("/findAll")
    public Result finAllHospitalSet(){
        //调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        Result<List<HospitalSet>> ok = Result.ok(list);
        return ok;
    }

    //2. 逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置信息")
    @DeleteMapping("{id}")
    public Result removeHospitalSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
}
