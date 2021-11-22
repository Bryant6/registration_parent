package com.wang.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.yygh.common.exception.HospitalException;
import com.wang.yygh.common.helper.JwtHelper;
import com.wang.yygh.common.result.ResultCodeEnum;
import com.wang.yygh.model.user.UserInfo;
import com.wang.yygh.user.mapper.UserInfoMapper;
import com.wang.yygh.user.service.UserInfoService;
import com.wang.yygh.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyu
 * @date 2021/11/20 14:18
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //校验参数
        if(StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(code)) {
            throw new HospitalException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验校验验证码
        String mobleCode = "123456";
//        String mobleCode = (String) redisTemplate.opsForValue().get(phone);
        if(!code.equals(mobleCode)) {
            throw new HospitalException(ResultCodeEnum.CODE_ERROR);
        }

        //手机号已被使用
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        //获取会员
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setPhone(phone);
            userInfo.setStatus(1);
            this.save(userInfo);
        }
        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new HospitalException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //TODO 记录登录

        //返回页面显示名称
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
        }
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }
        //jwt生成token字符串
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("name", name);
        map.put("token", token);
        return map;

    }
}
