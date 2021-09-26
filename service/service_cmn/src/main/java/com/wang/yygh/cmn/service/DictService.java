package com.wang.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.yygh.model.cmn.Dict;

import java.util.List;

/**
 * @author wangyu
 * @date 2021/9/26 16:48
 */
public interface DictService extends IService<Dict> {
    List<Dict> findChildData(Long id);
}
