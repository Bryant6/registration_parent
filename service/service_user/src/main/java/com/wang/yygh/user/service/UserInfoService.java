package com.wang.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.yygh.model.user.UserInfo;
import com.wang.yygh.vo.user.LoginVo;

import java.util.Map;

/**
 * @author wangyu
 * @date 2021/11/20 14:18
 */
public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> loginUser(LoginVo loginVo);
}
